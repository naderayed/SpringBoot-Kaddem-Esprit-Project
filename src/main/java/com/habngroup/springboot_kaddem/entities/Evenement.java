package com.habngroup.springboot_kaddem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent")
    private Long idEvent;
    private String TitreEvent;
    private String EmplacementEvent;
    @Temporal(TemporalType.DATE)
    private Date DateEvent;

    @ManyToMany
    private Set<Etudiant> etudiants;

}
