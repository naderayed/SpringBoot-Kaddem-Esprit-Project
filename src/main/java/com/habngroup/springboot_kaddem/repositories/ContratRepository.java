package com.habngroup.springboot_kaddem.repositories;

import com.habngroup.springboot_kaddem.DTO.ArchivePercentType;
import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    @Query(value = "select new com.habngroup.springboot_kaddem.DTO.ArchivePercentType" +
            "(count(*)/(Select COUNT(*) from Contrat) * 100, archive) from Contrat GROUP BY archive")
    public List <ArchivePercentType> getPercentageGroupByArchiveStatus();

    List<Contrat> findContratsByDateFinContrat(Date date);

    @Query("select c from Contrat c where c.etudiant = ?1")
    List<Contrat> findContratByEtudiant(Etudiant etudiant);
    List<Contrat> findAllByDateDebutContratOrDateFinContratOrSpecialiteOrArchiveOrMontantContrat(Date dateDebut, Date dateFin, Specialite specialite,
                                                                                                 boolean archive, Long montantContrat);
    List <Contrat> findContratByProfessorIdProfessorAndDateDebutContratEqualsAndDateFinContratEquals(Long idProf,Date dateD, Date dateF);

    @Query(value = "SELECT * FROM `contrat` WHERE contrat.date_debut_contrat >= ?1 AND date_fin_contrat <= ?2", nativeQuery = true)
    List<Contrat> getContratsBetween(Date dateDebut, Date dateFin);
    @Query(value = "SELECT COUNT(*) FROM `contrat` WHERE (contrat.archive=true) AND ( contrat.date_debut_contrat>=?1 AND contrat.date_fin_contrat <= ?2)", nativeQuery = true)
    Long nbContratsValides(Date dateDebut, Date dateFin);
    @Query(value = "SELECT contrat.id_contrat FROM contrat ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Long randomIdContrat();

    List<Contrat> findContratBySpecialiteAndDateDebutContratAndDateFinContratAndMontantContrat(Specialite specialite, Date datededebut , Date datedefin,Long montant);

}


