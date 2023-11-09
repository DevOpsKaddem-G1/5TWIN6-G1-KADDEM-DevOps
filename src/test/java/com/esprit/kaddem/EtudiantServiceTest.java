package com.esprit.kaddem;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Option;
import com.esprit.kaddem.repositories.EtudiantRepository;
import com.esprit.kaddem.restcontrollers.dtos.EtudiantDTO;
import com.esprit.kaddem.services.EtudiantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EtudiantServiceTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantServiceImpl;

    @Test
    public void testGetAllEtudiants() {

        //Creating 3 default students
        Etudiant etudiant1 = new Etudiant( "Salim", "Ben Younes", Option.SE);
        Etudiant etudiant2 = new Etudiant( "Aymen", "Chaaban", Option.GAMIX);
        Etudiant etudiant3 = new Etudiant( "Karim", "Trabelsi", Option.TWIN);

        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2, etudiant3));
        List<Etudiant> etudiantsList = etudiantServiceImpl.retrieveAllEtudiants();

        //Asserting size
        assertEquals(etudiantsList.size(), 3);

        //Asserting student 1 values
        assertEquals(etudiantsList.get(0).getPrenomE(), "Salim");
        assertEquals(etudiantsList.get(0).getNomE(), "Ben Younes");
        assertEquals(etudiantsList.get(0).getOp(), Option.SE);

        // Asserting student 2 values
        assertEquals("Aymen", etudiantsList.get(1).getPrenomE());
        assertEquals("Chaaban", etudiantsList.get(1).getNomE());
        assertEquals(Option.GAMIX, etudiantsList.get(1).getOp());

        // Asserting student 3 values
        assertEquals("Karim", etudiantsList.get(2).getPrenomE());
        assertEquals("Trabelsi", etudiantsList.get(2).getNomE());
        assertEquals(Option.TWIN, etudiantsList.get(2).getOp());
    }
    @Test
    public void testFindEtudiantById() {

        //Arranging
        Etudiant etudiant1 = new Etudiant( "Elyes", "Boudhina", Option.TWIN);
        when(etudiantRepository.findById(etudiant1.getIdEtudiant())).thenReturn(Optional.of(etudiant1));

        // Acting
        Etudiant etudiantById = etudiantServiceImpl.retrieveEtudiant(etudiant1.getIdEtudiant());

        //Asserting
        assertNotEquals(etudiantById, null);
        assertEquals(etudiantById.getPrenomE(), "Elyes");
        assertEquals(etudiantById.getNomE(), "Boudhina");
        assertEquals(etudiantById.getOp(), Option.TWIN);
    }

}
