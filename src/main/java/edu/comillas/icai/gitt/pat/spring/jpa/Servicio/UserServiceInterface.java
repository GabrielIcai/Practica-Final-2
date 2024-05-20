package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;


import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Token;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {


    Token login(String email, String password);


    AppUser authentication(String tokenId);


    ProfileResponse profile(AppUser appUser);


    ProfileResponse profile(AppUser appUser, ProfileRequest profile);

    ProfileResponse profile(RegisterRequest register);

    void logout(String tokenId);

    void delete(AppUser appUser);

}
