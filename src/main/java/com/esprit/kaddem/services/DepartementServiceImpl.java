package com.esprit.kaddem.services;


import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.DepartementRepository;
import com.esprit.kaddem.repositories.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImpl implements IDepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        return departementRepository.findById(idDepart).get();
    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).get();
        return universite.getDepartements();
    }
}
