package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Option;
import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.services.IDepartementService;
import com.habngroup.springboot_kaddem.services.IProfessor;
import com.habngroup.springboot_kaddem.utils.ExportpdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
public class DepartementController {

    private final IDepartementService iDepartementService;
    private final IProfessor iProfessorservice;

    @Autowired
    public DepartementController(IDepartementService iDepartementService, IProfessor iProfessorservice) {
        this.iDepartementService = iDepartementService;
        this.iProfessorservice = iProfessorservice;
    }

    @GetMapping("/getDepartements")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Departement> getAllDepartements() {
        return iDepartementService.getAllDepartements();
    }

    @GetMapping("/getDepartement/{departementId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Departement getDepartementById(@PathVariable("departementId") Long departementId) {
        return iDepartementService.getDepartementById(departementId);
    }

    @PostMapping("/addDepartement")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addDepartement(@RequestBody Departement departement) {
        iDepartementService.addDepartement(departement);
    }

    @DeleteMapping("/deleteDepartement")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteDepartement(@RequestBody Departement departement) {
        iDepartementService.deleteDepartement(departement);
    }

    @DeleteMapping("/deleteDepartement/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteDepartement(@PathVariable("departementId") Long departementId) {
        iDepartementService.deleteDepartementById(departementId);
    }

    @PutMapping("/updateDepartement/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateDepartement(@PathVariable("departementId") Long departementId, @RequestBody Departement departement) {
        iDepartementService.updateDepartement(departementId, departement);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/ajouterchefdepartemnt/{nomDepartement}")
    void affecterChefDepartement(@PathVariable("nomDepartement") String nomDepartement, @RequestBody Professor professor) {
        iDepartementService.affectChefDepartement(nomDepartement, professor);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/ajouterchefdepartemnt/{idDepartement}/{idprof}")
    void affecterChefDepartementparnom(@PathVariable("idDepartement") Long idDepartement, @PathVariable("idprof") Long idProf) {
        Professor professor = iProfessorservice.getProfessorById(idProf);
        Departement d=iDepartementService.getDepartementById(idDepartement);
        iDepartementService.affectChefDepartement(d.getNomDepart(), professor);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/afficheroptiondepartement/{nomDeaprtement}")
    Set<Option> displayDepartementoptionsbynom(@PathVariable("nomDeaprtement") String nomDeaprtement) {
        return iDepartementService.displayDepartementoptionsbynom(nomDeaprtement);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/afficherNbrEtudparOption/{nomDepartement}")
    Map<Option, Long> displaynbretudiantbyoption(@PathVariable("nomDepartement") String nomDepartement) {
        return iDepartementService.displaynbretudiantbyoption(nomDepartement);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/groupetudiant/{option}")
    public Map<String, List<Etudiant>> getEtudiantbyoption(@PathVariable("option") String option) {
        return iDepartementService.alletudiantbyoptiondepartement(option);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/countnbrprofbydep/{nomDepartement}")
    Long countnbrprofbydep(@PathVariable("nomDepartement") String nomdepart) {
        return iDepartementService.countprofesseurbydepartement(nomdepart);

    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/getdepartementsorted")
    List<Departement> getdepartsorted() {
        return iDepartementService.getdepartementSorted();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("exportdepartpdf")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportTermsPdf() {
        List<Departement> departements = iDepartementService.getAllDepartements();
        ByteArrayInputStream bais = ExportpdfService.departementsPDFReport("List des departement:", "Les departements", departements, "getNomDepart");
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-Disposition", "inline;filename=departements.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/findDepartByname/{nomDepartement}")
    Departement getdepartementbynom(@PathVariable("nomDepartement") String nomdepart) {
        return iDepartementService.getdepartementbynom(nomdepart);
    }
}
