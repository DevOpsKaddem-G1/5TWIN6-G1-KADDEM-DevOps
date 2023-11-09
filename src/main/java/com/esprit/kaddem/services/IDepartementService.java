package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.restcontrollers.dto.DepartementDTO;

import java.util.List;

public interface IDepartementService {
    List<Departement> retrieveAllDepartements();
    Departement addDepartement(DepartementDTO d);
    Departement updateDepartement(DepartementDTO d);
    Departement retrieveDepartement(Integer idDepart);
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite);


}

