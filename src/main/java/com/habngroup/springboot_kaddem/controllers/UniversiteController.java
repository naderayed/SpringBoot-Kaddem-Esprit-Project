package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Universite;
import com.habngroup.springboot_kaddem.services.IUniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.TreeSet;

@RestController
@CrossOrigin
public class UniversiteController {
    private final IUniversiteService iUniversiteService;

    @Autowired
    public UniversiteController(IUniversiteService iUniversiteService) {
        this.iUniversiteService = iUniversiteService;
    }

    @GetMapping("/getUniversites")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Universite> getAllUniversites(){
        return iUniversiteService.getAllUniversites();
    }

    @GetMapping("/getUniversitiesSorted")
    TreeSet<Universite> getUniversitiesSorted(){
        return iUniversiteService.getUniversitiesSorted();
    }

    @GetMapping("/getUniversite/{universiteId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Universite getUniversiteById(@PathVariable("universiteId") Long universiteId){
        return  iUniversiteService.getUniversiteById(universiteId);
    }

    @PostMapping("/addUniversite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addUniversite(@RequestBody Universite universite){
        iUniversiteService.addUniversite(universite);
    }

    @DeleteMapping("/deleteUniversite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUniversite(@RequestBody Universite universite){
        iUniversiteService.deleteUniversite(universite);
    }

    @DeleteMapping("/deleteUniversite/{universiteId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUniversite(@PathVariable("universiteId") Long universiteId){
        iUniversiteService.deleteUniversiteById(universiteId);
    }

    @PutMapping("/updateUniversite/{universiteId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateUniversite(@PathVariable("universiteId") Long universiteId, @RequestBody Universite universite){
        iUniversiteService.updateUniversite(universiteId, universite);
    }


    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void assignUniversiteToDepartement(@PathVariable("universiteId") Long universiteId,@PathVariable("departementId") Long departementId){
        iUniversiteService.assignUniversiteToDepartement(universiteId, departementId);
    }

    @GetMapping("/retreiveDepartementsByUniversity/{universityId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    List<Departement> retrieveDepartementsByUniversite(@PathVariable("universityId") Long universityId){
        return iUniversiteService.retrieveDepartementsByUniversite(universityId);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/assignUniversiteToEtudiant/{universiteId}/{etudiantId}")
    public void assignUniversiteToEtudiant(@PathVariable("universiteId") Long universiteId,@PathVariable("etudiantId") Long etudiantId){
        iUniversiteService.assignUniversiteToEtudiant(universiteId,etudiantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/retrieveEtudiantsByUniversite/{universiteId}")
    List<Etudiant> retrieveEtudiantByUniversite(@PathVariable("universiteId")Long universityId){
        return iUniversiteService.retrieveEtudiantByUniversite(universityId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countUniversites")
    Long countUniversites(){
        return iUniversiteService.countUniversites();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/nbrDepartementsByUniversite/{universiteId}")
    Long nbrDepartementsByUniversite(@PathVariable("universiteId") Long universiteId){
        return iUniversiteService.nbrDepartementsByUniversite(universiteId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/findUniversiteByName/{nomUniv}")
    Universite findUniversiteByName(@PathVariable("nomUniv") String nomUniv){
        return iUniversiteService.findUniversiteByName(nomUniv);
    }

}
