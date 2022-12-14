package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Club;
import java.util.List;

public interface IClubService {
    void addClub(Club club);
    void updateClub(Long clubId, Club club);
    void deleteClub(Club club);
    void deleteClubById(Long clubId);
    List<Club> getAllClubs();
    Club getClubById(Long clubId);
    Club getClubByNomClub(String nomClub);
}
