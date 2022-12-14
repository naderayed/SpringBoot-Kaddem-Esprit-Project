package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.Club;
import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findEtudiantByNomEAndPrenomE(String nomEtudiant, String prenomEtudiant);

    @Query("select e from Etudiant e where e.departement = ?1")
    List<Etudiant> findEtudiantByDepartement(Departement departement);

    List<Etudiant> findByNomEOrPrenomE(String nomE,String prenomE);

    Etudiant findByNomE(String nomE);

    List<Etudiant> findByOption(Option option);

    List<Etudiant> findByClubNomClub(String nomClub);


}
