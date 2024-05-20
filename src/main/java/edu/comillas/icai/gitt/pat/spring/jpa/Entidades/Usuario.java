package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(nullable = false) public String nombre;
    @Column(nullable = false, unique = true) public String email;
    @Column(nullable = false) public String credenciales;
    @OneToOne(mappedBy = "usuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Vecino vecino;

}
