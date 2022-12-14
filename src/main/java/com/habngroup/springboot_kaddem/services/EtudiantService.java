package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.*;
import com.habngroup.springboot_kaddem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EtudiantService implements IEtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final EquipeRepository equipeRepository;
    private final ContratRepository contratRepository;
    private final DepartementRepository departementRepository;
    private final ClubRepository clubRepository;
    private final EvenementRepository evenementRepository;

    @Autowired
    public EtudiantService(EtudiantRepository etudiantRepository, EquipeRepository equipeRepository, ContratRepository contratRepository, DepartementRepository departementRepository, ClubRepository clubRepository, EvenementRepository evenementRepository) {
        this.etudiantRepository = etudiantRepository;
        this.equipeRepository = equipeRepository;
        this.contratRepository = contratRepository;
        this.departementRepository = departementRepository;
        this.clubRepository = clubRepository;
        this.evenementRepository = evenementRepository;
    }

    @Override
    public void addEtudiant(Etudiant etudiant) {
        // TODO checking etudiant !existence before inserting
        etudiantRepository.save(etudiant);
    }

    @Override
    public void updateEtudiant(Long etudiantId, Etudiant etudiant) {
        // TODO checking etudiant existence before updating
        Etudiant etudiantToUpdate = getEtudiantById(etudiantId);
        if (etudiantToUpdate != null){
            if (etudiant != null && !Objects.equals(etudiantToUpdate, etudiant)){
                etudiantToUpdate.setNomE(etudiant.getNomE());
                etudiantToUpdate.setPrenomE(etudiant.getPrenomE());
                etudiantToUpdate.setOption(etudiant.getOption());
                etudiantRepository.save(etudiant);
            }
        }
        else throw new IllegalStateException("Etudiant with id " + etudiantId + " does not exist");

    }

    @Override
    public void deleteEtudiant(Etudiant etudiant) {
        // TODO checking etudiant existence before deleting
        etudiantRepository.delete(etudiant);
    }

    @Override
    public void deleteEtudiantById(Long etudiantId) {
        // TODO checking etudiant existence before deleting
        etudiantRepository.deleteById(etudiantId);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantById(Long etudiantId) {
        return etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new IllegalStateException("Etudiant with id " + etudiantId + " does not exist"));
    }

    @Override
    public void assignEtudiantToDepartement(Long etudiantId, Long departementId) {

        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Departement departement = departementRepository.findById(departementId).orElse(null);
        etudiant.setDepartement(departement);
        etudiantRepository.save(etudiant);

    }

    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant etudiant, Long idContrat, Long idEquipe) {
        Contrat contrat = contratRepository.findById(idContrat).orElseThrow(() -> new IllegalStateException("Contrat with id " + idContrat + " does not exist"));
        Equipe equipe = equipeRepository.findById(idEquipe).orElseThrow(() -> new IllegalStateException("Equipe with id " + idEquipe + " does not exist"));
        etudiant.getEquipes().add(equipe);
        contrat.setEtudiant(etudiant);
      return   etudiantRepository.save(etudiant);


    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Long idDepartement) {
        Departement departement = departementRepository.findById(idDepartement)
                .orElseThrow(() -> new IllegalStateException("Departement with id " + idDepartement +
                        " does not exist"));
        return etudiantRepository.findEtudiantByDepartement(departement);
    }

    @Override
    public Departement ShowDepartementEtudiantDetails(Long departementId) {
        return departementRepository.findById(departementId).
                orElseThrow(() -> new IllegalStateException("Departement with id " + departementId + " does not exist"));
    }

    @Override
    public List<Contrat> getAllContratByIdEtudiant(Long idEtudiant) {
            Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElseThrow(() -> new IllegalStateException("Etudiant with id " + idEtudiant +
                    " does not exist"));
            return contratRepository.findContratByEtudiant(etudiant);

    }
    @Override
    public void AssignEtudiantToClub(Long etudiantId, Long clubId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Club club = clubRepository.findById(clubId).orElse(null);
        etudiant.setClub(club);
        etudiantRepository.save(etudiant);
    }

    @Override
    @Scheduled(cron = "* * */13 * * *")
    public String retrieveAndUpdateStatusContratbyEtudiant() throws ParseException {
      /*  LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String format = date.plusDays(15).format(formatter);
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(format);
        List<Contrat> contratList =contratRepository.findContratsByDateFinContrat(date1);
        System.out.println(contratList);*/
        return null;
    }

    @Override
    public List<Etudiant> findetudiantByNameOrLastName(String nomE, String prenomE) {

        return etudiantRepository.findByNomEOrPrenomE(nomE,prenomE);
    }

    @Override
    public Etudiant findetudiantByName(String nomE) {
        return etudiantRepository.findByNomE(nomE);
    }

    @Override
    public List<Etudiant> ShowStudentbyOption(Option option) {
        return etudiantRepository.findByOption(option);
    }

    @Override
    public List<Etudiant> ShowStudentbyNomClub(String nomClub) {
        return etudiantRepository.findByClubNomClub(nomClub);
    }

    @Override
    public TreeSet<Etudiant> TriEtudiantbyName() {
        List<Etudiant> etudiantList = etudiantRepository.findAll();
        TreeSet<Etudiant> collect = etudiantList.stream().collect(Collectors.toCollection(TreeSet::new));
        return collect;
    }

    @Override
    public Optional<Departement> findDepartementByname(String nomDep) {
        return departementRepository.findDepartementByNomDepart(nomDep);
    }

    @Override
    public List<Equipe> findEquipeByNomEquipe(String nomEqu) {
        return equipeRepository.findEquipeByNomEquipe(nomEqu);
    }

    @Override
    public List<Contrat> findContratBySpecialiteAndDateDebutContratAndDateFinContratAndMontantContrat(Specialite specialite, Date datededebut, Date datedefin,Long montant) {
        return contratRepository.findContratBySpecialiteAndDateDebutContratAndDateFinContratAndMontantContrat(specialite,datededebut,datedefin,montant);
    }
    @Override
    public void AffectEtudiantToEvent(Long etudiantId, Long idEvent) {
        Evenement evenement = evenementRepository.findById(idEvent).orElseThrow(() -> new IllegalStateException("Event with id " + idEvent + " does not exist"));
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow(() -> new IllegalStateException("Etudiant with id " + etudiantId + " does not exist"));
        etudiant.getEvenements().add(evenement);
        etudiantRepository.save(etudiant);
    }
}
