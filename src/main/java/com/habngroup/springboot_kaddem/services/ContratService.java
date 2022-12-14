package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.DTO.ArchivePercentType;
import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Specialite;
import com.habngroup.springboot_kaddem.repositories.ContratRepository;
import com.habngroup.springboot_kaddem.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ContratService implements IContratService {
    private final ContratRepository contratRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    public void addContrat(Contrat contrat) {
        // TODO checking contrat !existence before inserting
        contratRepository.save(contrat);
    }

    @Override
    public void updateContrat(Long contratId, Contrat contrat) {
       Contrat contratToUpdate = getContratById(contratId);
       if (contratToUpdate != null){
           if (contrat != null && !Objects.equals(contratToUpdate, contrat)){
               contratToUpdate.setMontantContrat(contrat.getMontantContrat());
               contratToUpdate.setDateDebutContrat(contrat.getDateDebutContrat());
               contratToUpdate.setDateFinContrat(contrat.getDateFinContrat());
               contratToUpdate.setArchive(contrat.isArchive());
               contratToUpdate.setSpecialite(contrat.getSpecialite());
               contratRepository.save(contratToUpdate);
           }
       }
       else throw new IllegalStateException("Contrat with id " + contratId + " does not exist");
    }

    @Override
    public void deleteContrat(Contrat contrat) {
        // TODO checking contrat existence before deleting
        contratRepository.delete(contrat);
    }

    @Override
    public void deleteContratById(Long contratId) {
        // TODO checking contrat existence before deleting
        contratRepository.deleteById(contratId);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long contratId) {
        return contratRepository.findById(contratId)
                .orElseThrow(() -> new IllegalStateException("Contrat does not exist"));
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat contrat, String nomEtudiant, String prenomEtudiant) {
        Etudiant etudiant = etudiantRepository.findEtudiantByNomEAndPrenomE(nomEtudiant, prenomEtudiant)
                .orElse(null);
        if (contrat != null && etudiant.getContrats().size() < 6){
            contrat.setEtudiant(etudiant);
            return contratRepository.save(contrat);
        }
        throw new IllegalStateException("Contrat does not exist or Etudiant has already 5 Contrats ");
    }
    @Override
    public List<ArchivePercentType> getContratPercentByArchiveStatus(){
        return contratRepository.getPercentageGroupByArchiveStatus();
    }

    @Override

    @Scheduled(cron = "* * */13 * * *")
    public String retrieveAndUpdateStatusContrat() throws ParseException {

     /*   LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String format = date.plusDays(15).format(formatter);
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(format);
     contratRepository.findContratsByDateFinContrat(date1);*/

//         LocalDateTime date = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//         String format = date.plusDays(15).format(formatter);
//         Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(format);
//      contratRepository.findContratsByDateFinContrat(date1);

        return null;
    }
    
    public List<Contrat> findAllByDateDebutContratOrDateFinContratOrSpecialiteOrArchiveOrMontantContrat(Date dateDebut, Date dateFin, Specialite specialite, boolean archive, Long montantContrat) {
        return contratRepository.findAllByDateDebutContratOrDateFinContratOrSpecialiteOrArchiveOrMontantContrat(dateDebut, dateFin, specialite, archive, montantContrat);
    }

    @Override
    public List<Contrat> getContratsBetween(Date dateDebut, Date dateFin) {
        return  contratRepository.getContratsBetween(dateDebut, dateFin);
    }

    @Override
    public Long nbContratsValides(Date dateDebut, Date dateFin) {
        return contratRepository.nbContratsValides(dateDebut, dateFin);
    }


    @Override
    public Long getRandomIdContrat() {
        return contratRepository.randomIdContrat();
    }

    @Override
    @Scheduled(cron = "* * * * */29 * ")
    public void reductionOnRandomContrat() {
        Long randomIdContrat = getRandomIdContrat();
        Contrat contratGotReduction = contratRepository.findById(randomIdContrat).orElse(null);
        if (contratGotReduction != null)
            contratGotReduction.setMontantContrat((long) ((contratGotReduction.getMontantContrat()-contratGotReduction.getMontantContrat()*0.15)));
        contratRepository.save(contratGotReduction);
    }


}
