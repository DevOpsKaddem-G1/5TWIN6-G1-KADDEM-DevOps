package com.esprit.kaddem.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversiteServiceImpl implements IUniversiteService{

//    @Autowired
    UniversiteRepository universiteRepository;
    public UniversiteServiceImpl() {
        // TODO Auto-generated constructor stub
    }
    public   List<Universite> retrieveAllUniversites(){
        return (List<Universite>) universiteRepository.findAll();
    }

    public    Universite addUniversite (Universite  u){
        return  (universiteRepository.save(u));
    }

    public    Universite updateUniversite (Universite  u){
        return  (universiteRepository.save(u));
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            Universite u = optionalUniversite.get();
            return u;
        } else {
            // Gérer le cas où l'université n'a pas été trouvée
            return null; // ou lancer une exception appropriée
        }
    }

    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

}
