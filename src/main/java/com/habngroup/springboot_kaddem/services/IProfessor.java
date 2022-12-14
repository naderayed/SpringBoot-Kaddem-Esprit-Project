package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.entities.Specialite;

import java.util.Date;
import java.util.List;

public interface IProfessor {
    void addProfessor(Professor professor);
    void updateProfessor( Long idProf, Professor professor);
    void deleteProfessor(Professor professor);
    void deleteProfessorById(Long professorId);
    List<Professor> getAllProfessors();

    Professor getProfessorById(Long professorId);
    void assignProfessorToDepartement (Long professorId, Long departementId) ;
    void assignProfessorToContrat (Long professorId, Long contratId) ;
    List<Professor> getProfessorsByDepartement (Long idDepartment);

    List <Professor> findByFirstNameOrLastNameOrPhoneOrEmailOrSpecialityAndFirstNameIsNotNullAndLastNameIsNotNullAndPhoneIsNotNullAndEmailIsNotNullAndSpecialityIsNotNull
            (String firstName, String lastName, String phone, String email, Specialite speciality );

    Float getProfessorSumAmount(Long idP, Date dateD, Date dateF);

    Professor addAndAssignProfessorToEquipeAndContract(Professor Professor, Long idContrat, Long idEquipe);

}
