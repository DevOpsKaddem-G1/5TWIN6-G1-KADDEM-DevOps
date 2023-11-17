package com.esprit.kaddem.restcontrollers;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.restcontrollers.dtos.EquipeDTO;
import com.esprit.kaddem.services.IEquipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")

public class EquipeRestController {
    IEquipeService equipeService;

    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<EquipeDTO> getEquipes() {
        List<Equipe> equipes = equipeService.retrieveAllEquipes();
        return equipes.stream()
                .map(equipeService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public EquipeDTO retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        Equipe equipe = equipeService.retrieveEquipe(equipeId);
        return equipeService.convertToDTO(equipe);
    }



    @PostMapping("/add-equipe")
    @ResponseBody
    public EquipeDTO addEquipe(@RequestBody EquipeDTO equipeDTO) {
        // Convert EquipeDTO to Equipe entity if needed before saving
        Equipe equipe = equipeService.convertToEntity(equipeDTO);
        return equipeService.convertToDTO(equipeService.addEquipe(equipe));
    }

    @PutMapping("/update-equipe")
    @ResponseBody
    public EquipeDTO updateEquipe(@RequestBody EquipeDTO equipeDTO) {
        // Convert EquipeDTO to Equipe entity if needed before updating
        Equipe equipe = equipeService.convertToEntity(equipeDTO);
        return equipeService.convertToDTO(equipeService.updateEquipe(equipe));
    }



}
