package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  public Long id;

    @Column(nullable = false, unique = true) public String email;
    @Column(nullable = false) public String password;
    @Column(nullable = false) public Integer piso;
    @Column(nullable = false) public String letra;

    @Column(nullable = false) public String name;
}