package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.entities.Club;
import com.habngroup.springboot_kaddem.services.IClubService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClubController {
    
    private final IClubService iClubService;
    
    public ClubController(IClubService iClubService) {
        this.iClubService = iClubService;
    }

    @GetMapping("/getClubs")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<Club> getAllClubs(){
        return iClubService.getAllClubs();
    }

    @GetMapping("/getClub/{clubId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    Club getClubById(@PathVariable("clubId") Long clubId){
        return iClubService.getClubById(clubId);
    }

    @PostMapping("/addClub")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    void addClub(@RequestBody Club club){
        iClubService.addClub(club);
    }

    @DeleteMapping("/deleteClub")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteClub(@RequestBody Club club){
        iClubService.deleteClub(club);
    }

    @DeleteMapping("/deleteClub/{clubId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteClubById(@PathVariable("clubId") Long clubId){
        iClubService.deleteClubById(clubId);
    }

    @PutMapping("/updateClub/{clubId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateClub(@PathVariable("clubId") Long clubId, @RequestBody Club club){
        iClubService.updateClub(clubId, club);
    }

    @GetMapping("/getClubByNom/{nomClub}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Club getClubByNom(@PathVariable("nomClub") String nomClub){
        return iClubService.getClubByNomClub(nomClub);
    }

}
