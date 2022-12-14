package com.habngroup.springboot_kaddem.controllers;

import com.habngroup.springboot_kaddem.entities.DetailEquipe;
import com.habngroup.springboot_kaddem.services.IDetailEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DetailEquipeController {
    private final IDetailEquipeService iDetailEquipeService;

    @Autowired
    public DetailEquipeController(IDetailEquipeService iDetailEquipeService) {
        this.iDetailEquipeService = iDetailEquipeService;
    }

    @GetMapping("/getDetailEquipes")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    List<DetailEquipe> getAllDetailEquipes(){
        return iDetailEquipeService.getAllDetailEquipes();
    }

    @GetMapping("/getDetailEquipe/{detailEquipeId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    DetailEquipe getDetailEquipeById(@PathVariable("detailEquipeId") Long DetailEquipeId){
        return  iDetailEquipeService.getDetailEquipeById(DetailEquipeId);
    }

    @PostMapping("/addDetailEquipe")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addDetailEquipe(@RequestBody DetailEquipe DetailEquipe){
        iDetailEquipeService.addDetailEquipe(DetailEquipe);
    }

    @DeleteMapping("/deleteDetailEquipe")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteDetailEquipe(@RequestBody DetailEquipe DetailEquipe){
        iDetailEquipeService.deleteDetailEquipe(DetailEquipe);
    }

    @DeleteMapping("/deleteDetailEquipe/{detailEquipeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteDetailEquipe(@PathVariable("detailEquipeId") Long DetailEquipeId){
        iDetailEquipeService.deleteDetailEquipeById(DetailEquipeId);
    }

    @PutMapping("/updateDetailEquipe/{detailEquipeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateDetailEquipe(@PathVariable("detailEquipeId") Long detailEquipeId, @RequestBody DetailEquipe DetailEquipe){
        iDetailEquipeService.updateDetailEquipe(detailEquipeId, DetailEquipe);
    }


    @PutMapping("/assignEquipeToDetialEquipe/{nomEquipe}/{thema}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void assignEquipeToDetialEquipe(@PathVariable("nomEquipe") String nomEquipe,
                                    @PathVariable("thema") String thema){
        iDetailEquipeService.assignEquipeToDetialEquipe(nomEquipe,thema);
    }
    @GetMapping("/findDetailEquipeByEquipe/{idEquipe}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    DetailEquipe detailEquipe(@PathVariable("idEquipe") Long idEquipe)
    {
        return iDetailEquipeService.findDetailEquipeByEquipe(idEquipe);
    }
    
}
