package com.esprit.kaddem;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import com.esprit.kaddem.services.EquipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        // Mock data
        List<Equipe> fakeEquipes = Arrays.asList(new Equipe(1, "Équipe 1"), new Equipe(2, "Équipe 2"));
        when(equipeRepository.findAll()).thenReturn(fakeEquipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();
        assertEquals(fakeEquipes, result);
    }

    @Test
    void testAddEquipe() {
        Equipe fakeEquipe = new Equipe(1, "Nouvelle Équipe");
        when(equipeRepository.save(fakeEquipe)).thenReturn(fakeEquipe);

        Equipe result = equipeService.addEquipe(fakeEquipe);
        assertEquals(fakeEquipe, result);
    }

    @Test
    void testUpdateEquipe() {
        Equipe fakeEquipe = new Equipe(1, "Équipe à mettre à jour");
        when(equipeRepository.save(fakeEquipe)).thenReturn(fakeEquipe);

        Equipe result = equipeService.updateEquipe(fakeEquipe);
        assertEquals(fakeEquipe, result);
    }

    @Test
    void testRetrieveEquipe() {
        int equipeId = 1;
        Equipe fakeEquipe = new Equipe(equipeId, "Équipe 1");
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(fakeEquipe));

        Equipe result = equipeService.retrieveEquipe(equipeId);
        assertEquals(fakeEquipe, result);
    }
}
