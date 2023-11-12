package com.esprit.kaddem;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.repositories.DepartementRepository;
import com.esprit.kaddem.repositories.UniversiteRepository;
import com.esprit.kaddem.restcontrollers.dto.DepartementDTO;
import com.esprit.kaddem.services.DepartementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> fakeDepartements = Arrays.asList(new Departement(1, "Departement 1"), new Departement(2, "Departement 2"));
        when(departementRepository.findAll()).thenReturn(fakeDepartements);

        List<Departement> result = departementService.retrieveAllDepartements();
        assertEquals(fakeDepartements, result);
    }

    @Test
    void testAddDepartement() {
        // Mock data
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setName("Nouvelle Departement");

        Departement fakeDepartement = new Departement(1, "Nouvelle Departement");
        when(departementRepository.save(any())).thenReturn(fakeDepartement);

        Departement result = departementService.addDepartement(departementDTO);

        assertEquals(fakeDepartement, result);
    }

//    @Test
//    void testUpdateDepartement() {
//        DepartementDTO departementDTO = new DepartementDTO();
//        departementDTO.setName("Departement mise à jour");
//        Departement fakeDepartement = new Departement(1, "Departement à mettre à jour");
//        when(departementRepository.save(fakeDepartement)).thenReturn(fakeDepartement);
//
//        Departement result = departementService.updateDepartement(departementDTO);
//        assertEquals(fakeDepartement, result);
//    }

    @Test
    void testRetrieveDepartement() {
        int idDepartement = 1;
        Departement fakeDepartement = new Departement(idDepartement, "Équipe 1");
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(fakeDepartement));

        Departement result = departementService.retrieveDepartement(idDepartement);
        assertEquals(fakeDepartement, result);
    }
}
