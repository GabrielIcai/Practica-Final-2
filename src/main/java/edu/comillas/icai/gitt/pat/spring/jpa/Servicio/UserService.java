package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;


import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.UserServiceInterface;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Service
public class UserService implements UserServiceInterface {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired Hashing hashing;
    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) return null;
        // En el TODO6,esta línea:  if (!appUser.password.equals(password)) return null;
        if (!hashing.compare(appUser.password,password)) return null; //TODO14

        Token token = tokenRepository.findByAppUser(appUser);
        if (token != null) return token;
        token = new Token();
        token.appUser = appUser;
        tokenRepository.save(token);
        return token;
    }

    public AppUser authentication(String tokenId) {
        Optional<Token> tokenOpt = tokenRepository.findById(tokenId);
        if (tokenOpt.isPresent()){
            Token token = tokenOpt.get();
            return token.appUser;
        }
        else {
            return null;
        }
    }

    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(appUser.name,appUser.email);
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        appUser.name = profile.name();
        // TODO6 : appUser.password = profile.password();
        appUser.password = hashing.hash(profile.password()); //TODO14
        appUserRepository.save(appUser);
        return new ProfileResponse(appUser.name,appUser.email);
    }
    public ProfileResponse profile(RegisterRequest register) {
        AppUser appUser = new AppUser();
        appUser.name = register.name();
        appUser.email = register.email();
        // TODO6 : appUser.password = register.password();
        appUser.password = hashing.hash(register.password()); //TODO14
        appUserRepository.save(appUser);
        return new ProfileResponse(appUser.name,appUser.email);
    }

    public void logout(String tokenId) {
        Token token = tokenRepository.findById(tokenId).get();
        tokenRepository.delete(token);
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser); // No debería haber problema con un token asociado en la table de tokens debido a OnDeleteAction.CASCAD
    }

}
