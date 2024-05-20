package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Vecino {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(nullable = false) public Integer piso;
    @Column(nullable = false) public String letra;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Usuario usuario;
    @OneToMany(mappedBy = "vecino")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Planta> planta = new ArrayList<>();
}


