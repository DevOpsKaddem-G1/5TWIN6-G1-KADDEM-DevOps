package com.esprit.kaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;
import com.esprit.kaddem.repositories.EtudiantRepository;
import com.esprit.kaddem.restcontrollers.dtos.ContratDTO;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ContratServiceImpl implements IContratService {

    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        log.info("debut methode retrieveAllContrats");
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(ContratDTO c) {
        log.info("debut methode updateContrat");
    
        Optional<Contrat> optionalContrat = contratRepository.findById(c.getId());
    
        if (optionalContrat.isPresent()) {
            Contrat existingContrat = optionalContrat.get();
    
            existingContrat.setDateDebutContrat(c.getDateDebutContrat());
            existingContrat.setDateFinContrat(c.getDateFinContrat());
            existingContrat.setSpecialite(c.getSpecialite());
            contratRepository.save(existingContrat);
    
            return existingContrat;
        } else {
            return null;
        }
    }
    


    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        log.info("debut methode retrieveContrat");
        Optional<Contrat> optionalContrat = contratRepository.findById(idContrat);
        
        return optionalContrat.orElse(new Contrat());
    }
    

    @Override
    public void removeContrat(Integer idContrat) {
        log.info("debut methode removeContrat");
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat addContrat(ContratDTO c) {
        Contrat contrat = new Contrat();
        contrat.setDateDebutContrat(c.getDateDebutContrat());
        contrat.setDateFinContrat(c.getDateFinContrat());
        contrat.setSpecialite(c.getSpecialite());
        contratRepository.save(contrat);
        return contrat;
    }

    @Transactional
    public Contrat addAndAffectContratToEtudiant(ContratDTO c, String nomE, String prenomE) {
          Contrat ce = new Contrat();
        ce.setDateDebutContrat(c.getDateDebutContrat());
        ce.setDateFinContrat(c.getDateFinContrat());
        ce.setSpecialite(c.getSpecialite());
        Long startDate = new Date().getTime();
        log.info("startDate: " + startDate);
        log.info("debut methode addAndAffectContratToEtudiant");
        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
        log.info("etudiant: " + etudiant.getNomE() + " " + etudiant.getPrenomE());

        Integer nbContratsActifs = etudiant.getContrats().size();
        if (nbContratsActifs > 5) {
            log.info("nombre de contrats autorisés est atteint");
            Long endDate = new Date().getTime();
            Long executionTime = endDate - startDate;
            log.info("endDate: " + startDate);
            log.info("executionTime: " + executionTime + " ms");
            return ce;
        }
        log.info("nb Contrats en cours: " + nbContratsActifs);
        contratRepository.save(ce);
        ce.setEtudiant(etudiant);
        log.info("fin methode addAndAffectContratToEtudiant");
        Long endDate = new Date().getTime();
        Long executionTime = endDate - startDate;

        log.info("endDate: " + startDate);
        log.info("executionTime: " + executionTime + " ms");

        return ce;
    }

    public Integer nbContratsValides(Date startDate, Date endDate) {
        return contratRepository.getnbContratsValides(startDate, endDate);
    }

    public void retrieveAndUpdateStatusContrat() {
        log.info("debut methode retrieveAndUpdateStatusContrat");
        List<Contrat> contrats = contratRepository.findAll();
        log.info("total contrats :" + contrats.size());

        for (Contrat contrat : contrats) {
            log.info("id: " + contrat.getIdContrat());
            log.info("date fin" + contrat.getDateFinContrat());
            log.info("archived " + contrat.getArchived());

            Date dateSysteme = new Date();

            if (contrat.getArchived() == null || contrat.getArchived() == false) {
                long differenceInTime = contrat.getDateFinContrat().getTime() - dateSysteme.getTime();
                long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
                log.info("difference in days : " + differenceInDays);
                if (differenceInDays == 15) { // pour 15 jours exactement
                    log.info(" Contrat Commencant le : " + contrat.getDateDebutContrat() + "pour l'etudiant "
                            + contrat.getEtudiant().getNomE() +
                            " " + contrat.getEtudiant().getPrenomE() + "  va bientot s achever le "
                            + contrat.getDateFinContrat());
                }
                if (differenceInDays == 0) {
                    log.info("jour j: " + contrat.getIdContrat());
                    contrat.setArchived(true);
                    contratRepository.save(contrat);
                }
            }

            log.info("debut methode retrieveAndUpdateStatusContrat");
        }
    }

    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
        float differenceInTime = (float) (endDate.getTime() - startDate.getTime());
        float differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
        float differenceInMonths = differenceInDays / 30;
        List<Contrat> contrats = contratRepository.findAll();
        float chiffreAffaireEntreDeuxDates = 0;
        float chiffreAffaireEntreDeuxDatesIA = 0;
        float chiffreAffaireEntreDeuxDatesCloud = 0;
        float chiffreAffaireEntreDeuxDatesReseau = 0;
        float chiffreAffaireEntreDeuxDatesSecurite = 0;

        for (Contrat contrat : contrats) {
            if (contrat.getSpecialite() == Specialite.IA) {
                chiffreAffaireEntreDeuxDates += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesIA += (differenceInMonths * contrat.getMontantContrat());

            } else if (contrat.getSpecialite() == Specialite.CLOUD) {
                chiffreAffaireEntreDeuxDates += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesCloud += (differenceInMonths * contrat.getMontantContrat());
            } else if (contrat.getSpecialite() == Specialite.RESEAU) {
                chiffreAffaireEntreDeuxDates += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesReseau += (differenceInMonths * contrat.getMontantContrat());

            } else if (contrat.getSpecialite() == Specialite.SECURITE) {
                chiffreAffaireEntreDeuxDates += (differenceInMonths * contrat.getMontantContrat());
                chiffreAffaireEntreDeuxDatesSecurite += (differenceInMonths * contrat.getMontantContrat());

            }
        }
        log.info("chiffreAffaireEntreDeuxDates: " + chiffreAffaireEntreDeuxDates);
        log.info("chiffreAffaireEntreDeuxDatesIA:" + chiffreAffaireEntreDeuxDatesIA);
        log.info("chiffreAffaireEntreDeuxDatesCloud " + chiffreAffaireEntreDeuxDatesCloud);
        log.info("chiffreAffaireEntreDeuxDatesReseau " + chiffreAffaireEntreDeuxDatesReseau);
        log.info("chiffreAffaireEntreDeuxDatesSecurite " + chiffreAffaireEntreDeuxDatesSecurite);
        return chiffreAffaireEntreDeuxDates;

    }

}
