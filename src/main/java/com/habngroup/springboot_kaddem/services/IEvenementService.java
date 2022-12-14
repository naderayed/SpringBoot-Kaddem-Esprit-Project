package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Evenement;

import java.util.List;

public interface IEvenementService {

    void addEvent(Evenement evenement);
    void updateEvent(Long idEvent, Evenement evenement);
    void deleteEvent(Evenement evenement);
    void deleteEventById(Long idEvent);
    List<Evenement> getAllEvents();
    Evenement getEventById(Long idEvent);


}
