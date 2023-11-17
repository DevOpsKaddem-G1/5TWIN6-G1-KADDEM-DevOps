package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import com.esprit.kaddem.restcontrollers.dtos.EquipeDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImpl implements IEquipeService {

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
        return equipeRepository.findById(idEquipe)
                .orElseThrow(() -> new EntityNotFoundException("Equipe with id " + idEquipe + " not found"));
    }

    // Conversion method
    public EquipeDTO convertToDTO(Equipe equipe) {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setId(equipe.getIdEquipe());
        equipeDTO.setName(equipe.getNomEquipe());
        // Set other fields
        return equipeDTO;
    }

    // Additional method to convert a list of Equipe entities to EquipeDTOs
    public List<EquipeDTO> convertToDTOList(List<Equipe> equipes) {
        return equipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public Equipe convertToEntity(EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(equipeDTO.getId());
        equipe.setNomEquipe(equipeDTO.getName());
        // Set other fields
        return equipe;
    }
}
