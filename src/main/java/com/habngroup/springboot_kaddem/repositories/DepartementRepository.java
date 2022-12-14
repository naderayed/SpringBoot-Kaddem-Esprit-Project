package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Option;
import com.habngroup.springboot_kaddem.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    @Query("select d from Departement d where d.nomDepart = ?1")
    Optional<Departement> findDepartementByNomDepart(String nomDepartement);

    @Query("select d from Departement d order by d.nomDepart asc ")
    List<Departement> departementsorted();


}
