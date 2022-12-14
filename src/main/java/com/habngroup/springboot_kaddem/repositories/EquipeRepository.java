package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {


    List<Equipe> findEquipeByNomEquipe(String nomEqu);
}
