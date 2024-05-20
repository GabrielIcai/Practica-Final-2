package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoPlanta;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ServicioPlantas {
    @Autowired
    RepoPlanta repoPlanta;
    @Autowired
    RepoUsuario repoUsuario;

    public Planta creaPlanta(Planta planta) {
        Planta nuevaplanta = repoPlanta.save(planta);
        return planta;
    }

    public Usuario autentica(String credenciales) {
        Usuario usuario = repoUsuario.findByCredenciales(credenciales);
        if (usuario == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return usuario;
    }
    public void borra(Planta planta){
        repoPlanta.delete(planta);
    }
    public Iterable<Planta> buscaplantas() {
        return repoPlanta.findAll();
    }

    @Scheduled(fixedRate = 60000)  // Ejecutar cada minuto
    public void verificarRiegoPlantas() {
        Iterable<Planta> plantas = repoPlanta.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Planta planta : plantas) {
            Duration duration = Duration.between(planta.temporizadorRiego, now);
            if (duration.compareTo(planta.tipo.tiempoRiego) >= 0) {
                System.out.println("La planta " + planta.tipo.nombre + " necesita ser regada.");
                planta.Regada=FALSE;
                planta.temporizadorRiego=now;
                repoPlanta.save(planta);
            }
        }
    }
    public void regar(Planta planta){
        planta.Regada=TRUE;
        repoPlanta.save(planta);
    }
}

