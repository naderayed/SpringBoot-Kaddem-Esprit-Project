package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Etudiant;
import com.habngroup.springboot_kaddem.entities.Evenement;
import com.habngroup.springboot_kaddem.repositories.EtudiantRepository;
import com.habngroup.springboot_kaddem.repositories.EvenementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class EvenementService  implements IEvenementService{

    private final EvenementRepository evenementRepository;
    private final EtudiantRepository etudiantRepository;

    public EvenementService(EvenementRepository evenementRepository, EtudiantRepository etudiantRepository) {
        this.evenementRepository = evenementRepository;
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public void addEvent(Evenement evenement) {
        evenementRepository.save(evenement);
    }

    @Override
    public void updateEvent(Long idEvent, Evenement evenement) {
        Evenement evenementtoupdate = getEventById(idEvent);
        if (evenementtoupdate!=null)
        {
            if (evenement!=null&& !Objects.equals(evenementtoupdate, evenement)) {
                evenementtoupdate.setTitreEvent(evenement.getTitreEvent());
                evenementtoupdate.setDateEvent(evenement.getDateEvent());
                evenementtoupdate.setEmplacementEvent(evenement.getEmplacementEvent());
                evenementRepository.save(evenementtoupdate);
            }
        }
    }

    @Override
    public void deleteEvent(Evenement evenement) {
        evenementRepository.delete(evenement);
    }

    @Override
    public void deleteEventById(Long idEvent) {
        evenementRepository.deleteById(idEvent);

    }

    @Override
    public List<Evenement> getAllEvents() {
        return evenementRepository.findAll();

    }

    @Override
    public Evenement getEventById(Long idEvent) {
        return evenementRepository.findById(idEvent).orElseThrow(() -> new IllegalStateException("Event with id " + idEvent + " does not exist"));

    }


}
