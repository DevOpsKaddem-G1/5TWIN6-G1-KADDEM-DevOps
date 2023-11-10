package com.esprit.kaddem.services;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.repositories.DepartementRepository;
import com.esprit.kaddem.repositories.UniversiteRepository;
import com.esprit.kaddem.restcontrollers.dto.DepartementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        // Create a list of sample Departements
        List<Departement> sampleDepartements = new ArrayList<>();

        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Departement1");
        // Set other properties as needed
        sampleDepartements.add(departement1);

        Departement departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Departement2");
        // Set other properties as needed
        sampleDepartements.add(departement2);

        // Mock the behavior of the departementRepository to return the sampleDepartements when retrieveAllDepartements is called
        when(departementRepository.findAll()).thenReturn(sampleDepartements);

        // Call the service method
        List<Departement> result = departementService.retrieveAllDepartements();

        // Verify that the service method returned the expected data
        assertEquals(sampleDepartements, result);

        System.err.println("testRetrieveAllDepartements: SUCCESS");
    }
}
