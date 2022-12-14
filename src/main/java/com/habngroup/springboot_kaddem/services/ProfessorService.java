package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Contrat;
import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.entities.Specialite;
import com.habngroup.springboot_kaddem.repositories.ContratRepository;
import com.habngroup.springboot_kaddem.repositories.DepartementRepository;
import com.habngroup.springboot_kaddem.repositories.ProfessorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ProfessorService implements IProfessor{

    ProfessorRepo professorRepo ;
    ContratRepository contratRepository;
    DepartementRepository departementRepository;
    @Override
    public void addProfessor(@RequestBody Professor professor) {
        professorRepo.save(professor);
    }

    @Override
    public void updateProfessor(Long profID, Professor professor) {
        Professor profToUpdate = getProfessorById(profID);
        if (profToUpdate != null){
            if (professor != null && !Objects.equals(profToUpdate, professor)){
                profToUpdate.setEmail(professor.getEmail());
                profToUpdate.setPhone(professor.getPhone());
                profToUpdate.setSpeciality(professor.getSpeciality());
                profToUpdate.setFirstName(professor.getFirstName());
                profToUpdate.setLastName(professor.getLastName());
                professorRepo.save(profToUpdate);
            }
        }
        else throw new IllegalStateException("Contrat with id " + profID + " does not exist");
    }

    @Override
    public void deleteProfessor(Professor professor) {
        professorRepo.delete(professor);
    }

    @Override
    public void deleteProfessorById(Long professorId) {
        professorRepo.deleteById(professorId);
    }
    @Override
    public List<Professor> getAllProfessors() {
      return   professorRepo.findAll();
    }

    @Override
    public Professor getProfessorById(Long professorId) {
        return professorRepo.findById(professorId).orElse(null);
    }

    @Override
    public void assignProfessorToDepartement(Long professorId, Long departementId) {
        Professor professor = professorRepo.findById(professorId).orElse(null);
        Departement departement = departementRepository.findById(departementId).orElse(null);
        professor.setDepartment(departement);
        professorRepo.save(professor);
    }

    @Override
    public void assignProfessorToContrat(Long professorId, Long contratId) {
        Professor professor = professorRepo.findById(professorId).orElse(null);
        Contrat contrat = contratRepository.findById(contratId).orElse(null);
        contrat.setProfessor(professor);
        contratRepository.save(contrat);
    }



    @Override
    public List<Professor> getProfessorsByDepartement(Long idDepartement) {
        return professorRepo.findProfessorsByDepartmentIdDepart(idDepartement);
    }

    @Override
    public List<Professor> findByFirstNameOrLastNameOrPhoneOrEmailOrSpecialityAndFirstNameIsNotNullAndLastNameIsNotNullAndPhoneIsNotNullAndEmailIsNotNullAndSpecialityIsNotNull(String firstName, String lastName, String phone, String email, Specialite speciality) {
        return professorRepo.findByFirstNameOrLastNameOrPhoneOrEmailOrSpecialityAndFirstNameIsNotNullAndLastNameIsNotNullAndPhoneIsNotNullAndEmailIsNotNullAndSpecialityIsNotNull(firstName,lastName,phone,email, speciality);

    }

    @Override
    public Float getProfessorSumAmount(Long idP, Date dateD, Date dateF) {

       List <Contrat> contrats = contratRepository.findContratByProfessorIdProfessorAndDateDebutContratEqualsAndDateFinContratEquals(idP, dateD, dateF);
        long time= Math.abs(dateF.getTime() - dateD.getTime());
        long days= TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
        long months  = days/30;
        float sum = 0;
        for (Contrat contrat :  contrats) {
            sum += (months * contrat.getMontantContrat());
        }
        return  sum ;
    }

    @Override
    public Professor addAndAssignProfessorToEquipeAndContract(Professor Professor, Long idContrat, Long idEquipe) {
        return null;
    }

}
