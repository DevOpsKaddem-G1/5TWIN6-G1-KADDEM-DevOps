package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.restcontrollers.dtos.EtudiantDTO;
import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    Etudiant addEtudiant(EtudiantDTO e);

    Etudiant updateEtudiant(EtudiantDTO e);

    Etudiant retrieveEtudiant(Integer idEtudiant);

    String removeEtudiant(Integer idEtudiant);

}