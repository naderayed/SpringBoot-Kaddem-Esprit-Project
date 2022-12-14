package com.habngroup.springboot_kaddem.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String destinataire;
    private String object;
    private String message;

}
