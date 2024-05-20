package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column
    public String nombre;
    @Column(nullable = false) public LocalDateTime temporizadorRiego;
    @Column(nullable = false) public Duration tiempoRiego;
    @Column(nullable = false) public boolean Regada;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "app_user_id", nullable = false)
    public AppUser appUser;}