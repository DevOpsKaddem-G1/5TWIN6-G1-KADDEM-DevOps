package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Niveau;
import com.esprit.kaddem.entities.Specialite;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant addEtudiant(Etudiant e);
    Etudiant updateEtudiant(Etudiant e);
    Etudiant retrieveEtudiant(Integer idEtudiant);
    String removeEtudiant(Integer idEtudiant);

}
