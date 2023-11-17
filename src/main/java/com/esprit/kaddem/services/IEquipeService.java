package com.esprit.kaddem.services;



import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.restcontrollers.dtos.EquipeDTO;

import java.util.List;

public interface IEquipeService {

    List<Equipe> retrieveAllEquipes();

    Equipe  addEquipe(Equipe  e);

    Equipe updateEquipe (Equipe  e);

    Equipe retrieveEquipe (Integer idEquipe);



}
