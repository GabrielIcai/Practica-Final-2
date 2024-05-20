package edu.comillas.icai.gitt.pat.spring.jpa.Servicio;


import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoPlanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ServicioPlantas {
    @Autowired
    public
    RepoPlanta repoPlanta;

    @Autowired
    AppUserRepository appUserRepository;
    public Planta creaPlanta(Planta planta, AppUser appUser) {
        planta.appUser = appUser;
        return repoPlanta.save(planta);
    }

    public void borra(Planta planta){
        repoPlanta.delete(planta);
    }

    public Iterable<Planta> buscaplantas(AppUser appUser) {
        return repoPlanta.findByAppUser(appUser);}

        @Scheduled(fixedRate = 60000)  // Ejecutar cada minuto
    public void verificarRiegoPlantas() {
        Iterable<Planta> plantas = repoPlanta.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Planta planta : plantas) {
            Duration duration = Duration.between(planta.temporizadorRiego, now);
            if (duration.compareTo(planta.tiempoRiego) >= 0) {
                System.out.println("La planta " + planta.nombre + " necesita ser regada.");
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


