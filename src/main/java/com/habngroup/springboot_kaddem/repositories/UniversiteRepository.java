package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Universite findUniversiteByNomUniv(String nomUniv);
    @Query("select e from Etudiant e where e.universite = ?1")
    List<Universite> findEtudiantByUniversite(Universite universite);
}
