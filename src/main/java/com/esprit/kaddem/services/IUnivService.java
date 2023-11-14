package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Universite;

import java.util.List;

public interface IUnivService {

    List<Universite> retrieveAllUniversites();

    Universite addUniversite(Universite u);

    Universite updateUniversite(Universite u);

    Universite retrieveUniversite(Integer idUniversite);

    void removeUniversite(Integer idUniversite);
}
