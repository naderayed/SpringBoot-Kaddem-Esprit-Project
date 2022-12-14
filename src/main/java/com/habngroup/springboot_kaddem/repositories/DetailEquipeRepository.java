package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.entities.DetailEquipe;
import com.habngroup.springboot_kaddem.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DetailEquipeRepository extends JpaRepository<DetailEquipe, Long> {

    @Query("select de from DetailEquipe de where de.salle = ?1 and de.thematique = ?2")
    Optional<DetailEquipe> findDetailEquipeBySalleAndThematique(Long salle, String thematique);

    DetailEquipe findDetailEquipeByThematique(String Thema);

    DetailEquipe findDetailEquipeByEquipeIdEquipe(Long idEquipe);
}
