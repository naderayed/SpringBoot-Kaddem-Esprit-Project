package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.entities.Specialite;
import com.habngroup.springboot_kaddem.services.ContratService;
import com.habngroup.springboot_kaddem.services.DepartementService;
import com.habngroup.springboot_kaddem.services.ProfessorService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ProfessorController {
    private ProfessorService professorService;
    //TODO REMOVE UNUSED ATTRIBUTE LIKE THESE TWO BELLOW
    private ContratService contratService;
    private DepartementService departementService;

    @GetMapping("getProfessors")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @PostMapping("/addProfessor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addProfessor(@RequestBody Professor professor) {
        professorService.addProfessor(professor);
    }

    @PutMapping("/updateProfessor/{profId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateProfessor(@PathVariable("profId") Long professorID, @RequestBody Professor professor) {
        professorService.updateProfessor(professorID, professor);
    }

    @DeleteMapping("deleteProfById/{profId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public void deleteProfessorById(@PathVariable("profId") Long professorId) {
        professorService.deleteProfessorById(professorId);
    }

    @PostMapping("assignProfessorToContrat/{profID}/{contraID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void assignProfessorToContrat(@PathVariable("profID") Long professorId, @PathVariable("contraID") Long CntratId) {
        professorService.assignProfessorToContrat(professorId, CntratId);
    }

    @PostMapping("assignProfessorToDepartment/{profID}/{depID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void assignProfessorToDepartement(@PathVariable("profID") Long professorId, @PathVariable("depID") Long departementId) {
        professorService.assignProfessorToDepartement(professorId, departementId);
}
    @GetMapping("getProfessorsByDepartmentId/{depID}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public List<Professor> getProfessorsByDepartementId(@PathVariable("depID") Long idDepartement) {

        return professorService.getProfessorsByDepartement(idDepartement);
    }

    @GetMapping("getProfessorByAnything")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Professor> findProfessorsByFirstNameOrLastNameOrPhoneOrEmail(@RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName, @RequestParam(required = false)
    String phone, @RequestParam(required = false) String email,@RequestParam(required = false) Specialite spec )
    {
        return professorService.findByFirstNameOrLastNameOrPhoneOrEmailOrSpecialityAndFirstNameIsNotNullAndLastNameIsNotNullAndPhoneIsNotNullAndEmailIsNotNullAndSpecialityIsNotNull (firstName,lastName,phone,email,spec);
    }


    @GetMapping("/getProfessorById/{professorId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public Professor getProfessorById(@PathVariable Long professorId) {
        return professorService.getProfessorById(professorId);
    }

    @GetMapping("getProfessorSumAmount/{profID}/{dateD}/{dateF}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public Float getProfessorSumAmount(@PathVariable("profID") Long idP, @PathVariable("dateD") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateD, @PathVariable("dateF") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateF) {
        return professorService.getProfessorSumAmount(idP, dateD, dateF);
    }

    public Professor addAndAssignProfessorToEquipeAndContract(Professor Professor, Long idContrat, Long idEquipe) {
        return null;
    }

    @DeleteMapping("deleteProfessor")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProfessor(Professor professor) {
        professorService.deleteProfessor(professor);
    }

}

