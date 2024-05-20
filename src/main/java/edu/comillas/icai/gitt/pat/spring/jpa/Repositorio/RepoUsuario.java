package edu.comillas.icai.gitt.pat.spring.jpa.Repositorio;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
public interface RepoUsuario extends CrudRepository<Usuario, Long> {
    Usuario findByCredenciales(String credenciales);
    Usuario findByEmailAndCredenciales(String email, String credenciales);
}
