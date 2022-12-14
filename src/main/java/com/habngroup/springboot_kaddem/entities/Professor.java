package com.habngroup.springboot_kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.habngroup.springboot_kaddem.services.IProfessor;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Professor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="idProfessor")
    private Long idProfessor;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialite speciality;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonIgnore
    private Departement department;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "professor")
    @JsonIgnore
    private Set<Contrat> contrats;

}
