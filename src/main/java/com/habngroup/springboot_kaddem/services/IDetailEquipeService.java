package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {
    void addDetailEquipe(DetailEquipe detailEquipe);
    void updateDetailEquipe(Long detailEquipeId,DetailEquipe detailEquipe);
    void deleteDetailEquipe(DetailEquipe detailEquipe);
    void deleteDetailEquipeById(Long detailEquipeId);
    List<DetailEquipe> getAllDetailEquipes();
    DetailEquipe getDetailEquipeById(Long detailEquipeId);
    void assignEquipeToDetialEquipe(String nomEquipe,String salleDetailEquipe);

    DetailEquipe findDetailEquipeByEquipe(Long idEquipe);
}
