package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Club;
import com.habngroup.springboot_kaddem.repositories.ClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClubService implements IClubService{

    private final ClubRepository clubRepository;

    @Override
    public void addClub(Club club) {
        if (clubRepository.findClubByNomClub(club.getNomClub()).isEmpty())
            this.clubRepository.save(club);
    }

    @Override
    public void updateClub(Long clubId, Club club) {
        Club clubToUpdate = getClubById(clubId);
        if (clubToUpdate != null){
            if (club != null && !Objects.equals(clubToUpdate,club)){
                clubToUpdate.setNomClub(club.getNomClub());
                clubToUpdate.setLogoClub(club.getLogoClub());
                clubToUpdate.setDomaine(club.getDomaine());
                this.clubRepository.save(club);
            }
        }
        else throw new IllegalStateException("Club with id " + clubId + " does not exist");
    }

    @Override
    public void deleteClub(Club club) {
        if (this.clubRepository.findClubByNomClub(club.getNomClub()).isPresent())
            this.clubRepository.delete(club);
    }

    @Override
    public void deleteClubById(Long clubId) {
        if (this.getClubById(clubId) != null)
            this.clubRepository.deleteById(clubId);
    }

    @Override
    public List<Club> getAllClubs() {
        return this.clubRepository.findAll();
    }

    @Override
    public Club getClubById(Long clubId) {
        return this.clubRepository.findById(clubId)
                .orElse(null);
    }

    @Override
    public Club getClubByNomClub(String nomClub) {
        return this.clubRepository.findClubByNomClub(nomClub).orElse(null);
    }
}
