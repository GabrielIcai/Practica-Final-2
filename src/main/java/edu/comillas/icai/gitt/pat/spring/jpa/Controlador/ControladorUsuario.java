package edu.comillas.icai.gitt.pat.spring.jpa.Controlador;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.LoginRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Model.RegistroRequest;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoUsuario;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.ServicioVecinos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Usuario;

import java.util.Map;

@RestController
public class ControladorUsuario {
    @Autowired
    RepoUsuario repoUsuario;
    @Autowired
    ServicioVecinos servicioVecinos;


    @GetMapping("/api/usuarios")
    public Usuario login(@RequestHeader("Authorization") String credenciales) {
        Usuario usuario = repoUsuario.findByCredenciales(credenciales);
        if (usuario == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return usuario;
    }
    @PostMapping("/api/login")
    public Usuario login(@RequestBody LoginRequest loginRequest) {
        return servicioVecinos.authenticateUsuario(
                loginRequest.getEmail(),
                loginRequest.getCredenciales()
        );
    }


        @PostMapping("/api/registrarusuario")
        public Usuario createUsuario(@RequestBody RegistroRequest registroRequest) {
            return servicioVecinos.createUsuario(
                    registroRequest.getNombre(),
                    registroRequest.getEmailR(),
                    registroRequest.getCredencialesR(),
                    registroRequest.getPiso(),
                    registroRequest.getLetra()
            );
        }
}
