package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.DetailEquipe;
import com.habngroup.springboot_kaddem.entities.Equipe;
import com.habngroup.springboot_kaddem.repositories.DetailEquipeRepository;
import com.habngroup.springboot_kaddem.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DetailEquipeService implements IDetailEquipeService{
    private final DetailEquipeRepository detailEquipeRepository;
    private final EquipeRepository equipeRepository;

    @Autowired
    public DetailEquipeService(DetailEquipeRepository detailEquipeRepository, EquipeRepository equipeRepository) {
        this.detailEquipeRepository = detailEquipeRepository;
        this.equipeRepository = equipeRepository;
    }


    @Override
    public void addDetailEquipe(DetailEquipe detailEquipe) {
        // TODO checking detailEquipe !existence before inserting
        Optional<DetailEquipe> detailEquipeToAdd = detailEquipeRepository.
                findDetailEquipeBySalleAndThematique(detailEquipe.getSalle(), detailEquipe.getThematique());
        if (detailEquipeToAdd.isEmpty()) detailEquipeRepository.save(detailEquipe);
        else throw new IllegalStateException("DetailEquipe with salle " + detailEquipe.getSalle() +
            " and thematique " + detailEquipe.getThematique() + " already exist" );
    }

    @Override
    public void updateDetailEquipe(Long detailEquipeId, DetailEquipe detailEquipe) {
        // TODO checking detailEquipe existence before updating
        DetailEquipe detailEquipeTopUpdate = getDetailEquipeById(detailEquipeId);
        if (detailEquipeTopUpdate != null){
            if (detailEquipe != null && !Objects.equals(detailEquipeTopUpdate, detailEquipe)){
                detailEquipeTopUpdate.setSalle(detailEquipe.getSalle());
                detailEquipeTopUpdate.setThematique(detailEquipe.getThematique());
                detailEquipeRepository.save(detailEquipeTopUpdate);
            }
        }
        else throw new IllegalStateException("DetailEquipe with id " + detailEquipeId + " does not exist");
    }

    @Override
    public void deleteDetailEquipe(DetailEquipe detailEquipe) {
        // TODO checking detailEquipe existence before deleting
        Optional<DetailEquipe> detailEquipeToDelete = detailEquipeRepository.
                findDetailEquipeBySalleAndThematique(detailEquipe.getSalle(), detailEquipe.getThematique());
        if (detailEquipeToDelete.isPresent()) detailEquipeRepository.delete(detailEquipe);
        else throw new IllegalStateException("DetailEquipe with salle " + detailEquipe.getSalle() +
                " and thematique " + detailEquipe.getThematique() + " does not exist" );
    }

    @Override
    public void deleteDetailEquipeById(Long detailEquipeId) {
        // TODO checking detailEquipe existence before deleting
        DetailEquipe detailEquipeToDelete = getDetailEquipeById(detailEquipeId);
        if (detailEquipeToDelete != null) detailEquipeRepository.deleteById(detailEquipeId);
        else throw new IllegalStateException("DetailEquipe with id " + detailEquipeId + " does not exist");
    }

    @Override
    public List<DetailEquipe> getAllDetailEquipes() {
        return detailEquipeRepository.findAll();
    }

    @Override
    public DetailEquipe getDetailEquipeById(Long detailEquipeId) {
        return detailEquipeRepository.findById(detailEquipeId)
                .orElseThrow(() -> new IllegalStateException("DetailEquipe does not exist"));
    }

    @Override
    public void assignEquipeToDetialEquipe(String nomEquipe, String thematique) {
        Equipe equipe = equipeRepository.findEquipeByNomEquipe(nomEquipe).stream().findFirst().orElse(null);
        DetailEquipe detailEquipeByThematique = detailEquipeRepository.findDetailEquipeByThematique(thematique);
        detailEquipeByThematique.setEquipe(equipe);
        detailEquipeRepository.save(detailEquipeByThematique);
    }

    @Override
    public DetailEquipe findDetailEquipeByEquipe(Long idEquipe) {

        return  detailEquipeRepository.findDetailEquipeByEquipeIdEquipe(idEquipe);
    }


}
