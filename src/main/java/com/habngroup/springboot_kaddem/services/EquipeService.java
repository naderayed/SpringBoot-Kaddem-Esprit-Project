package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Equipe;
import com.habngroup.springboot_kaddem.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EquipeService implements IEquipeService{

    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @Override
    public void addEquipe(Equipe equipe) {
        // TODO checking equipe !existence before inserting
        equipeRepository.save(equipe);
    }

    @Override
    public void updateEquipe(Long equipeId, Equipe equipe) {
        // TODO checking equipe existence before updating
        Equipe equipeToUpdate = getEquipeById(equipeId);
        if (equipeToUpdate != null){
            if (equipe != null && !Objects.equals(equipeToUpdate, equipe)){
                equipeToUpdate.setNomEquipe(equipe.getNomEquipe());
                equipeToUpdate.setNiveau(equipe.getNiveau());
                equipeRepository.save(equipeToUpdate);
            }
        }
        else throw new IllegalStateException("Equipe with id " + equipeId + " does not exist");
    }

    @Override
    public void deleteEquipe(Equipe equipe) {
        // TODO checking equipe existence before deleting
        equipeRepository.delete(equipe);
    }

    @Override
    public void deleteEquipeById(Long equipeId) {
        // TODO checking equipe existence before deleting
        equipeRepository.deleteById(equipeId);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipeById(Long equipeId) {
        return equipeRepository.findById(equipeId)
                .orElseThrow(() -> new IllegalStateException("Equipe with id" + equipeId + " does not exist"));
    }


}
