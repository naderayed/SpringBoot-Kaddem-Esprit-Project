package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.*;

import java.util.List;
import java.util.TreeSet;

public interface IUniversiteService {
    void addUniversite(Universite universite);
    void updateUniversite(Long universiteId, Universite universite);
    void deleteUniversite(Universite universite);
    void deleteUniversiteById(Long universiteId);
    List<Universite> getAllUniversites();
    Universite getUniversiteById(Long universiteId);
    void assignUniversiteToDepartement(Long idUniversite, Long idDepartement);
    List<Departement> retrieveDepartementsByUniversite(Long idUniversite);
    void assignUniversiteToEtudiant(Long idUniversite, Long idEtudiant);
    List<Etudiant> retrieveEtudiantByUniversite(Long idUniversite);
    TreeSet<Universite> getUniversitiesSorted();
    Long countUniversites();
    Long nbrDepartementsByUniversite(Long idUniversite);
    Universite findUniversiteByName(String nomUniv);
}
