package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Vecino;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoPlanta;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoUsuario;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoVecino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicioVecinos {
    @Autowired
    RepoUsuario repoUsuario;
    @Autowired
    RepoVecino repoVecino;
    public Usuario createUsuario(String nombre, String email, String credenciales, Integer piso, String letra) {
            Usuario usuario = new Usuario();
            usuario.nombre=nombre;
            usuario.email=email;
            usuario.credenciales=credenciales;

            Vecino vecino = new Vecino();
            vecino.piso=piso;
            vecino.letra=letra;
            vecino.usuario=usuario;

            usuario.vecino=vecino;

            try {
                return repoUsuario.save(usuario);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ya registrado");
            }
        }

    public Usuario authenticateUsuario(String email, String credenciales) {
        Usuario usuario = repoUsuario.findByEmailAndCredenciales(email, credenciales);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }
        return usuario;
    }

    public Integer leePiso(Planta planta){
        Vecino vecino=repoVecino.findByPlanta(planta);
        return vecino.piso;
    }
    public String leeLetra(Planta planta){
        Vecino vecino=repoVecino.findByPlanta(planta);
        return vecino.letra;
    }
}
