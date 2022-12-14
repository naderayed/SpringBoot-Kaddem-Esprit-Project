package com.habngroup.springboot_kaddem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DetailEquipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailEquipe")
    private Long idDetailEquipe;
    private Long salle;
    private String thematique;
    @OneToOne
    private Equipe equipe;

}
