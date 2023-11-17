package com.esprit.kaddem;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Option;
import com.esprit.kaddem.repositories.EtudiantRepository;
import com.esprit.kaddem.restcontrollers.dtos.EtudiantDTO;
import com.esprit.kaddem.services.EtudiantServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EtudiantRestControllerTest {
    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(new Etudiant(), new Etudiant()));

        // Act
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, etudiants.size());
    }

    @Test
    void testAddEtudiant() {
        // Arrange
        EtudiantDTO etudiantDTO = new EtudiantDTO("Preno", "Nom", Option.TWIN);
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiantDTO);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testUpdateEtudiantNotFound() {
        // Arrange
        EtudiantDTO etudiantDTO = new EtudiantDTO("Preno", "Nom", Option.TWIN);
        when(etudiantRepository.existsById(Mockito.anyInt())).thenReturn(false);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> etudiantService.updateEtudiant(etudiantDTO));
    }

    @Test
    void testRetrieveByIdEtudiant() {
        // Arrange
        int idEtudiant = 1;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(idEtudiant);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testRetrieveEtudiantByIdNotFound() {
        // Arrange
        int idEtudiant = 1;
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(idEtudiant));
    }

    @Test
    void testRemoveEtudiant() {
        // Arrange
        int idEtudiant = 1;
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(new Etudiant()));

        // Act
        String result = etudiantService.removeEtudiant(idEtudiant);

        // Assert
        assertEquals("Etudiant deleted!", result);
    }

    @Test
    void testRemoveEtudiantNotFound() {
        // Arrange
        int idEtudiant = 1;
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.empty());

        // Act
        String result = etudiantService.removeEtudiant(idEtudiant);

        // Assert
        assertEquals("Etudiant not found with id: " + idEtudiant, result);
    }
}
