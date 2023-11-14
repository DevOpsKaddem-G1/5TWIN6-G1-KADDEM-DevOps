package com.esprit.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

    @Autowired
    public UniversiteServiceImpl(UniversiteRepository universiteRepository) {
        this.universiteRepository = universiteRepository;
    }

    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    public void add() {
        universiteRepository.save(new Universite(1, "Université de Technologie Innovante"));
        universiteRepository.save(new Universite(3, "Ibn Khaldûn (1332-1406)"));
        universiteRepository.save(new Universite(4, "Université Internationale des Sciences"));
        universiteRepository.save(new Universite(2, "Université des Arts Créatifs"));
        universiteRepository.save(new Universite(5, "Université de Recherche Avancée"));
        universiteRepository.save(new Universite(6, "Université Mondiale de la Paix"));
        universiteRepository.save(new Universite(7, "Université Numérique Avancée"));
        universiteRepository.save(new Universite(8, "Université Cosmique des Étoiles"));
        universiteRepository.save(new Universite(9, "Université Polytechnique du Futur"));
        universiteRepository.save(new Universite(10, "Université Écologique Verte"));
    }

    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            return optionalUniversite.get();
        } else {
            // Handle the case where the university is not found
            return null; // or throw an appropriate exception
        }
    }

    public void deleteUniversite(Integer idUniversite) {
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }
}
