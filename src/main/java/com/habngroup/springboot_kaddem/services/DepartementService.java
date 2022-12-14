package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Option;
import com.habngroup.springboot_kaddem.entities.Professor;
import com.habngroup.springboot_kaddem.repositories.DepartementRepository;
import com.habngroup.springboot_kaddem.repositories.ProfessorRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartementService implements IDepartementService {

    private final DepartementRepository departementRepository;
    private final ProfessorRepo professorRepo;

    public DepartementService(DepartementRepository departementRepository, ProfessorRepo professorRepo) {
        this.departementRepository = departementRepository;
        this.professorRepo = professorRepo;
    }

    @Override
    public void addDepartement(Departement departement) {
        // TODO checking departement !existence before inserting
        Optional<Departement> departementToAdd = departementRepository.findDepartementByNomDepart(departement.getNomDepart());
        if (departementToAdd.isPresent())
            throw new IllegalStateException("Departement " + departement.getNomDepart() + " already exist");
        departementRepository.save(departement);
    }

    @Override
    @Transactional
    public void updateDepartement(Long departementId, Departement departement) {
        Departement departementToUpdate = getDepartementById(departementId);
        if (departementToUpdate != null) {
            if (departement != null && departement.getNomDepart().length() > 0 && !Objects.equals(departementToUpdate.getNomDepart(), departement.getNomDepart()))
                departementToUpdate.setNomDepart(departement.getNomDepart());
        } else throw new IllegalStateException("Departement with id " + departementId + " does not exist");
    }

    @Override
    public void deleteDepartement(Departement departement) {
        Optional<Departement> departementToAdd = departementRepository.findDepartementByNomDepart(departement.getNomDepart());
        if (departementToAdd.isEmpty())
            throw new IllegalStateException("Departement " + departement.getNomDepart() + " does not exist");
        departementRepository.delete(departement);
    }

    @Override
    public void deleteDepartementById(Long departementId) {
        Departement departementToDelete = getDepartementById(departementId);
        if (departementToDelete != null) departementRepository.deleteById(departementId);
        else throw new IllegalStateException("Departement with id " + departementId + " does not exist");

    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartementById(Long departementId) {
        return departementRepository.findById(departementId)
                .orElseThrow(() -> new IllegalStateException("Departement does not exist"));
    }

    @Override
    public Departement affectChefDepartement(String nomDepartement, Professor professor) {
        Departement departement = departementRepository.findDepartementByNomDepart(nomDepartement).orElse(null);

        Professor p = professorRepo.findById(professor.getIdProfessor()).orElse(null);

        if (departement != null && p != null) {
            departement.setChefdepartement(p);
            return departementRepository.save(departement);
        }

        return departement;
    }

    @Override
    public Set<Option> displayDepartementoptionsbynom(String nomDepartement) {
        Departement departement = departementRepository.findDepartementByNomDepart(nomDepartement).orElse(null);

        if (departement != null) {
            return departement.getEtudiants().stream().map(Etudiant::getOption).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public Map<Option, Long> displaynbretudiantbyoption(String nomDepartement) {
        Departement departement = departementRepository.findDepartementByNomDepart(nomDepartement).orElse(null);
        if (departement != null) {
            return departement.getEtudiants().stream().collect(Collectors.groupingBy(Etudiant::getOption, Collectors.counting()));
        }

        return null;
    }

    public Map<String, List<Etudiant>> alletudiantbyoptiondepartement(String option) {
        return departementRepository.findAll().stream().
                flatMap(departement -> departement.getEtudiants().stream()).
                filter(etudiant -> Objects.equals(etudiant.getOption().toString(), option)).
                collect(Collectors.groupingBy(etudiant -> etudiant.getDepartement().getNomDepart()));

    }

    public long countprofesseurbydepartement(String nomDepart) {
        Departement departement = departementRepository.findDepartementByNomDepart(nomDepart).orElse(null);
        if (departement != null) {
            return departement.getProfessorSet().stream().count();
        }
        return 0;
    }

    @Override
    public List<Departement> getdepartementSorted() {
       return departementRepository.departementsorted();

    }

    @Override
    public Departement getdepartementbynom(String nomDepartement) {
        return  departementRepository.findDepartementByNomDepart(nomDepartement).orElseThrow(() -> new IllegalStateException("Departement does not exist"));
    }

}