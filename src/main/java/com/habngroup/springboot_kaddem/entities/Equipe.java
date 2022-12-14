package com.habngroup.springboot_kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    private Long idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "equipes")
    @JsonIgnore
    private Set<Etudiant> etudiants;
    @OneToOne(mappedBy = "equipe")
    @JsonIgnore
    private DetailEquipe detailEquipe;

}
