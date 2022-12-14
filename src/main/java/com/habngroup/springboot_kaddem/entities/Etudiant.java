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
public class Etudiant implements Serializable,Comparable<Etudiant> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    private Long idEtudiant;
    private String prenomE;
    private String nomE;
    private String emailE;
    @Enumerated(EnumType.STRING)
    private Option option;
    @ManyToOne
    Departement departement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private Set<Contrat> contrats;
    @ManyToMany
    private Set<Equipe> equipes;
    @ManyToOne
    @JsonIgnore
    private Club club;
    @ManyToOne
    @JsonIgnore
    Universite universite;
    @Override
    public int compareTo(Etudiant o) {
        return 0 ;
        //this.nomE.compareTo(o.nomE)
    }
    @ManyToMany
    private Set<Evenement> evenements;

}
