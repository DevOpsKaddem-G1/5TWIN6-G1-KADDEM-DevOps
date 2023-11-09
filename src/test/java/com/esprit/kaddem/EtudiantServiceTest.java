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
class EtudiantServiceTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantServiceImpl;

    @Test
    void testGetAllEtudiants() {

        //Creating 3 default students
        Etudiant etudiant1 = new Etudiant( "Salim", "Ben Younes", Option.SE);
        Etudiant etudiant2 = new Etudiant( "Aymen", "Chaaban", Option.GAMIX);
        Etudiant etudiant3 = new Etudiant( "Karim", "Trabelsi", Option.TWIN);

        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2, etudiant3));
        List<Etudiant> etudiantsList = etudiantServiceImpl.retrieveAllEtudiants();

        //Asserting size
        assertEquals(3, etudiantsList.size());

        //Asserting student 1 values
        assertEquals("Salim", etudiantsList.get(0).getPrenomE() );
        assertEquals("Ben Younes", etudiantsList.get(0).getNomE());
        assertEquals(Option.SE, etudiantsList.get(0).getOp());

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
    void testFindEtudiantById() {

        //Arranging
        Etudiant etudiant1 = new Etudiant( "Elyes", "Boudhina", Option.TWIN);
        when(etudiantRepository.findById(etudiant1.getIdEtudiant())).thenReturn(Optional.of(etudiant1));

        // Acting
        Etudiant etudiantById = etudiantServiceImpl.retrieveEtudiant(etudiant1.getIdEtudiant());

        //Asserting
        assertNotEquals(null, etudiantById);
        assertEquals("Elyes", etudiantById.getPrenomE());
        assertEquals("Boudhina", etudiantById.getNomE());
        assertEquals(Option.TWIN, etudiantById.getOp());
    }

    @Test
    void testCreateEtudiant() {
        EtudiantDTO etudiantDTO = new EtudiantDTO("Karim", "Trabelsi", Option.TWIN);
        Etudiant etudiantToBeSaved = mapToEtudiant(etudiantDTO);  // Convert EtudiantDTO to Etudiant

        // Stubbing the save method to return the same entity with an ID
        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> {
            Etudiant savedEtudiant = invocation.getArgument(0);
            return savedEtudiant;
        });

        // Act
        Etudiant createdEtudiant = etudiantServiceImpl.addEtudiant(etudiantDTO);

        // Perform assertions on the createdEtudiant
        assertEquals("Karim", createdEtudiant.getPrenomE());
        assertEquals("Trabelsi", createdEtudiant.getNomE());
        assertEquals(Option.TWIN, createdEtudiant.getOp());

        // Verifying that the save method was called with the correct argument
        verify(etudiantRepository, times(1)).save(etudiantToBeSaved); // Use eq() for object equality check
    }

    private Etudiant mapToEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant(etudiantDTO.getPrenomE(), etudiantDTO.getNomE(), etudiantDTO.getOp());
        return etudiant;
    }

    @Test
    void testDeleteEtudiant() {
        // Arrange
        Etudiant etudiantToBeDeleted = new Etudiant("Firas", "Sbai", Option.INFINI);
        etudiantToBeDeleted.setIdEtudiant(1); // Assuming an ID for testing purposes

        // Stubbing the findById method to return the etudiantToBeDeleted when called with the specified ID
        when(etudiantRepository.findById(etudiantToBeDeleted.getIdEtudiant())).thenReturn(Optional.of(etudiantToBeDeleted));

        // Act
        etudiantServiceImpl.removeEtudiant(etudiantToBeDeleted.getIdEtudiant());

        // Verify that the findById method was called with the correct argument
        verify(etudiantRepository, times(1)).findById(etudiantToBeDeleted.getIdEtudiant());

        // Verify that the deleteById method was called with the correct argument
        verify(etudiantRepository, times(1)).deleteById(etudiantToBeDeleted.getIdEtudiant());
    }

}
