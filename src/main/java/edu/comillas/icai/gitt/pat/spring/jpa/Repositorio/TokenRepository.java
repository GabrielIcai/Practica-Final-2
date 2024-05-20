package edu.comillas.icai.gitt.pat.spring.jpa.Repositorio;


import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    Token findByAppUser(AppUser appUser);
}