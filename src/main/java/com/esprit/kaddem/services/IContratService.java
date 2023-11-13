package com.esprit.kaddem.services;

import java.util.Date;
import java.util.List;

import com.esprit.kaddem.entities.Contrat;

public interface IContratService {
    List<Contrat> retrieveAllContrats();
    Contrat updateContrat(Contrat ce);
    Contrat retrieveContrat(Integer idContrat);
    void removeContrat(Integer idContrat);
    Contrat addContrat(Contrat c);

    Contrat addAndAffectContratToEtudiant (Contrat ce, String nomE ,String prenomE );

    public 	Integer nbContratsValides(Date startDate, Date endDate);


    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate);

    public void retrieveAndUpdateStatusContrat();
}
