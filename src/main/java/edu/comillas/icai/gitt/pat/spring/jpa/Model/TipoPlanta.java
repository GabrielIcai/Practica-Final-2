package edu.comillas.icai.gitt.pat.spring.jpa.Model;

import jakarta.persistence.Embeddable;

import java.time.Duration;
@Embeddable
public class TipoPlanta {
    public String nombre;
    public Duration tiempoRiego;

    public TipoPlanta(String nombre, Duration tiempoRiego) {
        this.nombre = nombre;
        this.tiempoRiego = tiempoRiego;
    }
}
