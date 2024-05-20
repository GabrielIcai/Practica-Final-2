package edu.comillas.icai.gitt.pat.spring.jpa.Entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.UUID) public String id;
    @OneToOne(optional=false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE) public AppUser appUser;
    @OneToMany(mappedBy = "token")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Planta> planta = new ArrayList<>();
}
