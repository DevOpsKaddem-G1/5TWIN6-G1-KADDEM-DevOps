package com.esprit.kaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;
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
    

    public Integer nbContratsValides(Date startDate, Date endDate) {
        return contratRepository.getnbContratsValides(startDate, endDate);
    }

    public void retrieveAndUpdateStatusContrat() {
        log.info("debut methode retrieveAndUpdateStatusContrat");
        List<Contrat> contrats = contratRepository.findAll();
        log.info("total contrats :" + contrats.size());
    
        Date currentDate = new Date();
    
        for (Contrat contrat : contrats) {
            logContractDetails(contrat);
    
            if (shouldUpdateContrat(contrat, currentDate)) {
                updateContratStatus(contrat);
            }
    
            log.info("debut methode retrieveAndUpdateStatusContrat");
        }
    }
    
    private void logContractDetails(Contrat contrat) {
        log.info("id: " + contrat.getIdContrat());
        log.info("date fin" + contrat.getDateFinContrat());
        log.info("archived " + contrat.getArchived());
    }
    
    private boolean shouldUpdateContrat(Contrat contrat, Date currentDate) {
        return (contrat.getArchived() == null || !contrat.getArchived())
                && contrat.getDateFinContrat() != null
                && calculateDifferenceInDays(contrat.getDateFinContrat(), currentDate) == 0;
    }
    
    private long calculateDifferenceInDays(Date endDate, Date currentDate) {
        long differenceInTime = endDate.getTime() - currentDate.getTime();
        return (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
    }
    
    private void updateContratStatus(Contrat contrat) {
        log.info("jour j: " + contrat.getIdContrat());
        contrat.setArchived(true);
        contratRepository.save(contrat);
    }
    
    



}
