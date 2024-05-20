package edu.comillas.icai.gitt.pat.spring.jpa.Controlador;


import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Planta;
import edu.comillas.icai.gitt.pat.spring.jpa.Entidades.Usuario;
import edu.comillas.icai.gitt.pat.spring.jpa.Servicio.ServicioPlantas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controlador {
    @Autowired
    ServicioPlantas servicioPlantas;

    @PostMapping("/api/crearPlanta")
    @ResponseStatus(HttpStatus.CREATED)
    public Planta crea(@RequestBody Planta planta, @RequestHeader("Authorization") String credenciales) {
        Usuario usuario = servicioPlantas.autentica(credenciales);
        return servicioPlantas.creaPlanta(planta);
    }

    @GetMapping("/api/plantas")
    public Iterable<Planta> leePlantas() {
        Iterable<Planta> plantas = servicioPlantas.buscaplantas();
        if (plantas == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return plantas;
    }
    @DeleteMapping("/api/plantas/{planta}")
    public void borra(@PathVariable Planta planta) {
        servicioPlantas.borra(planta);
        if (planta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/plantas/{planta}")
    public void regar(@PathVariable Planta planta) {
        servicioPlantas.regar(planta);
        }
}