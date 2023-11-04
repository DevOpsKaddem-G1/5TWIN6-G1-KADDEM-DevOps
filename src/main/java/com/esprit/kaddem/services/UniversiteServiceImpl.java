package com.esprit.kaddem.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    @Autowired
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

    public Universite retrieveUniversite (Integer idUniversite){
        Universite u = universiteRepository.findById(idUniversite).get();
        return  u;
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

}
