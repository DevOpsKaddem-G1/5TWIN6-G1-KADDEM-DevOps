package com.esprit.kaddem.Services;

import com.esprit.kaddem.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite  u);

    Universite updateUniversite (Universite  u);

    Universite retrieveUniversite (Integer idUniversite);


//    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) ;


}
