package com.esprit.kaddem.services;


import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.DepartementRepository;
import com.esprit.kaddem.repositories.UniversiteRepository;
import com.esprit.kaddem.restcontrollers.dto.DepartementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements IDepartementService {
    private final DepartementRepository departementRepository;
    private final UniversiteRepository universiteRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository, UniversiteRepository universiteRepository) {
        this.departementRepository = departementRepository;
        this.universiteRepository = universiteRepository;
    }

    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(DepartementDTO d) {
        Departement departement = new Departement();
        departement.setNomDepart(d.getName());

        return departementRepository.save(departement);
    }



    @Override
    public Departement updateDepartement(DepartementDTO departementDTO) {
        if (departementDTO.getId() == null) {
            throw new IllegalArgumentException("Departement ID cannot be null");
        }

        Departement existingDepartement = departementRepository.findById(departementDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Departement not found with ID: " + departementDTO.getId()));

        // Update only the properties that are not null in the departementDTO
        if (departementDTO.getName() != null) {
            existingDepartement.setNomDepart(departementDTO.getName());
        }
        // Add similar checks and updates for other properties as needed

        departementRepository.save(existingDepartement);

        return existingDepartement;
    }



    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

        if (optionalDepartement.isPresent()) {
            return optionalDepartement.get();
        } else {
            // Handle the case where Departement with the specified ID is not found
            throw new IllegalArgumentException("Departement not found with ID: " + idDepart);
        }
    }


    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            return universite.getDepartements();
        } else {
            // Handle the case where Universite with the specified ID is not found
            throw new IllegalArgumentException("Universite not found with ID: " + idUniversite);
        }
    }

}
