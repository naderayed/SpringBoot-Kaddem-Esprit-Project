package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Departement;
import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Universite;
import com.habngroup.springboot_kaddem.repositories.DepartementRepository;
import com.habngroup.springboot_kaddem.repositories.EtudiantRepository;
import com.habngroup.springboot_kaddem.repositories.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UniversiteService implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final DepartementRepository departementRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    public void addUniversite(Universite universite) {
        // TODO checking universite !existence before inserting
        universiteRepository.save(universite);
    }

    @Override
    public void updateUniversite(Long universiteId, Universite universite) {
        // TODO checking universite existence before updating
        Universite universiteToUpdate = getUniversiteById(universiteId);
        if (universiteToUpdate != null) {
            if (universite != null && !Objects.equals(universiteToUpdate, universite)) {
                universiteToUpdate.setNomUniv(universite.getNomUniv());
                universiteRepository.save(universiteToUpdate);
            }
        } else throw new IllegalStateException("Univeriste with id " + universiteId + " does not exist");

    }

    @Override
    public void deleteUniversite(Universite universite) {
        // TODO checking universite existence before deleting
        universiteRepository.delete(universite);
    }

    @Override
    public void deleteUniversiteById(Long universiteId) {
        // TODO checking universite existence before deleting
        universiteRepository.deleteById(universiteId);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(Long universiteId) {
        return universiteRepository.findById(universiteId)
                .orElseThrow(() -> new IllegalStateException("Universite with id " + universiteId + " does not exist"));
    }

    @Override
    public void assignUniversiteToDepartement(Long idUniversite, Long idDepartement) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Departement departement = departementRepository.findById(idDepartement).orElse(null);
        universite.getDepartements().add(departement);
        universiteRepository.save(universite);

    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new IllegalStateException("University with id " + idUniversite + "does not exist"));
        return universite.getDepartements().stream().toList();
    }

    @Override
    public void assignUniversiteToEtudiant(Long idUniversite, Long idEtudiant) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        universite.getEtudiants().add(etudiant);
        universiteRepository.save(universite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantByUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new IllegalStateException("University with id " + idUniversite + "does not exist"));
        return universite.getEtudiants().stream().toList();
    }

    @Override
    public TreeSet<Universite> getUniversitiesSorted() {
        List<Universite> all = universiteRepository.findAll();
        TreeSet<Universite> collect = all.stream().collect(Collectors.toCollection(TreeSet::new));
        return  collect;
    }

    @Override
    public Long countUniversites() {
        return getAllUniversites().stream().count();
    }

    @Override
    public Long nbrDepartementsByUniversite(Long idUniversite) {
        return retrieveDepartementsByUniversite(idUniversite).stream().count();
    }

    @Override
    public Universite findUniversiteByName(String nomUniv) {
        return universiteRepository.findUniversiteByNomUniv(nomUniv);
    }


}




