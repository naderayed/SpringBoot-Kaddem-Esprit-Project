package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Equipe;
import java.util.List;

public interface IEquipeService {
    void addEquipe(Equipe equipe);
    void updateEquipe(Long equipeId, Equipe equipe);
    void deleteEquipe(Equipe equipe);
    void deleteEquipeById(Long equipeId);
    List<Equipe> getAllEquipes();
    Equipe getEquipeById(Long equipeId);


}
