package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class EquipeServiceImpl implements IEquipeService{

    @Autowired
    EquipeRepository equipeRepository;


    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe addEquipe(Equipe e) {

       return equipeRepository.save(e);

    }

    @Override
    public Equipe updateEquipe(Equipe e) {
         return equipeRepository.save(e);

    }

    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);

        if (equipeOptional.isPresent()) {
            return equipeOptional.get();
        } else {
            // Handle the case when Equipe is not found, for example, throw an exception
            throw new EntityNotFoundException("Equipe not found for id: " + idEquipe);
        }
    }



}
