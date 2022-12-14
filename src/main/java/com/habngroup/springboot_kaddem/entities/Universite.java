package com.habngroup.springboot_kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Universite implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUniv")
    private Long idUniv;
    private String nomUniv;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Departement> departements;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Etudiant>etudiants;

    @Override
    public int compareTo(Object o) {
        Universite uni = (Universite) o;
        return this.nomUniv.compareTo(((Universite) o).nomUniv);
    }
}
