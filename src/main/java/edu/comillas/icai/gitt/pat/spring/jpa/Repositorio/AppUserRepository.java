package edu.comillas.icai.gitt.pat.spring.jpa.Repositorio;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}