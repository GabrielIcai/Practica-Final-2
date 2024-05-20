package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;

import edu.comillas.icai.gitt.pat.spring.jpa.Model.TipoPlanta;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Embedded public TipoPlanta tipo;
    @Column(nullable = false) public LocalDateTime temporizadorRiego;
    @Column(nullable = false) public boolean Regada;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "vecino_id", nullable = false)
    private Vecino vecino;

}
