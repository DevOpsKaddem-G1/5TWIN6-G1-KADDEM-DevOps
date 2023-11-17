package com.esprit.kaddem;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import com.esprit.kaddem.services.EquipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplMock {

    @Mock
    EquipeRepository equipeRepository;

    @InjectMocks
    EquipeServiceImpl equipeService;

    Equipe equipe1 = new Equipe(1, "Equipe 1");
    Equipe equipe2 = new Equipe(2, "Equipe 2");

    List<Equipe> equipes = Arrays.asList(
            equipe1,
            equipe2,
            new Equipe(3, "Equipe 3")
    );

    @Test
    void testRetrieveAllEquipes() {
        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);
        List<Equipe> result = equipeService.retrieveAllEquipes();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testAddEquipe() {
        Mockito.when(equipeRepository.save(equipe1)).thenReturn(equipe1);
        Equipe savedEquipe = equipeService.addEquipe(equipe1);
        Assertions.assertEquals("Equipe 1", savedEquipe.getNomEquipe());
    }

    @Test
    void testUpdateEquipe() {
        Mockito.when(equipeRepository.save(equipe1)).thenReturn(equipe1);
        Equipe updatedEquipe = equipeService.updateEquipe(equipe1);
        Assertions.assertEquals("Equipe 1", updatedEquipe.getNomEquipe());
    }

    @Test
    void testRetrieveEquipe() {
        Mockito.when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe1));
        Equipe result = equipeService.retrieveEquipe(1);
        Assertions.assertEquals("Equipe 1", result.getNomEquipe());
    }
}
