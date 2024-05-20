package edu.comillas.icai.gitt.pat.spring.jpa.Controlador;

import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.AppUser;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Repositorio.RepoPlanta;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.ServicioPlantas;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class ControlerPlanta{
    @Autowired
    ServicioPlantas servicioPlantas;
    @Autowired
    UserService userService;
    @Autowired
    RepoPlanta repoPlanta;

    @PostMapping("/api/crearPlanta")
    @ResponseStatus(HttpStatus.CREATED)
    public Planta crea(@RequestBody Planta planta, @CookieValue(value = "session", required = true) String session) {
        AppUser appUser = userService.authentication(session);
        if (appUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return servicioPlantas.creaPlanta(planta, appUser);
    }

    @GetMapping("/api/plantas")
    public Iterable<Planta> leePlantas(@CookieValue(value = "session", required = true) String session) {
        AppUser appUser = userService.authentication(session);
        if (appUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return servicioPlantas.buscaplantas(appUser);
    }

    @DeleteMapping("/api/plantas/{id}")
    public void borra(@PathVariable Long id, @CookieValue(value = "session", required = true) String session) {
        AppUser appUser = userService.authentication(session);
        if (appUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Planta planta = repoPlanta.findById(id).orElse(null);
        if (planta == null || !planta.appUser.equals(appUser)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        servicioPlantas.borra(planta);
    }

    @PutMapping("/api/plantas/{id}")
    public void regar(@PathVariable Long id, @CookieValue(value = "session", required = true) String session) {
        AppUser appUser = userService.authentication(session);
        if (appUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Planta planta = servicioPlantas.repoPlanta.findById(id).orElse(null);
        if (planta == null || !planta.appUser.equals(appUser)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        servicioPlantas.regar(planta);
    }}

