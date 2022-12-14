package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.DTO.ArchivePercentType;
import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Specialite;
import com.habngroup.springboot_kaddem.services.IContratService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ContratController {

    private final IContratService iContratService;

    @Autowired
    public ContratController(IContratService iContratService) {
        this.iContratService = iContratService;
    }

    @GetMapping("/getContrats")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Contrat> getAllContrats(){
        return iContratService.getAllContrats();
    }

    @GetMapping("/getContrat/{contratId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Contrat getContratById(@PathVariable("contratId") Long contratId){
        return iContratService.getContratById(contratId);
    }

    @PostMapping("/addContrat")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addContrat(@RequestBody Contrat contrat){
        iContratService.addContrat(contrat);
    }

    @DeleteMapping("/deleteContrat")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteContrat(@RequestBody Contrat contrat){
        iContratService.deleteContrat(contrat);
    }

    @DeleteMapping("/deleteContrat/{contratId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteContratById(@PathVariable("contratId") Long contratId){
        iContratService.deleteContratById(contratId);
    }

    @PutMapping("/updateContrat/{contratId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateContrat(@PathVariable("contratId") Long contratId, @RequestBody Contrat contrat){
        iContratService.updateContrat(contratId, contrat);
    }

    @PutMapping("/affectContratToEtudiant/{nomEtudiant}/{prenomEtudiant}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Contrat affectContratToEtudiant(@RequestBody Contrat contrat,
                                    @PathVariable("nomEtudiant") String nomEtudiant,
                                    @PathVariable("prenomEtudiant") String prenomEtudiant
    ){
        return iContratService.affectContratToEtudiant(contrat, nomEtudiant, prenomEtudiant);
    }

    @GetMapping("/vData/percentArchiveStatus")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public List<ArchivePercentType> getPercentageGroupByArchiveStatus(){
        return iContratService.getContratPercentByArchiveStatus();
    }

    @GetMapping("/searchContratByAnyCriteria/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Contrat> findByAnyParam(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin,
            @RequestParam(required = false) Specialite specialite,
            @RequestParam(required = false) boolean archive,
            @RequestParam(required = false) Long montant
            ){
        return iContratService.findAllByDateDebutContratOrDateFinContratOrSpecialiteOrArchiveOrMontantContrat(dateDebut, dateFin, specialite, archive, montant);
    }

    @GetMapping("/getContratsBetween/{dateDebut}/{dateFin}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Contrat> getContratsBetween(@PathVariable("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
                                     @PathVariable("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin){
        return iContratService.getContratsBetween(dateDebut, dateFin);
    }

    @GetMapping("/getNbContratsValides/{dateDebut}/{dateFin}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Long getNbContratsValides(@PathVariable("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
                              @PathVariable("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin){
        return iContratService.nbContratsValides(dateDebut, dateFin);
    }

    @GetMapping("getRandomIdContrat")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Long getRandomIdContrat(){
        return iContratService.getRandomIdContrat();
    }

    @PutMapping("/contratReduction")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void reductionOnRandomContrat(){
       iContratService.reductionOnRandomContrat();
    }
}

