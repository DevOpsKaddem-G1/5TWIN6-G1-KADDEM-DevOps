package com.esprit.kaddem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;
import com.esprit.kaddem.restcontrollers.ContratRestController;
import com.esprit.kaddem.restcontrollers.dtos.ContratDTO;
import com.esprit.kaddem.services.IContratService;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ContratRestControllerTest {
        @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @Test
    public void testGetContrats() {
        // Arrange
        List<Contrat> expectedContrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratService.retrieveAllContrats()).thenReturn(expectedContrats);

        // Act
        List<Contrat> actualContrats = contratRestController.getContrats();

        // Assert
        assertEquals(expectedContrats, actualContrats);
    }

    @Test
    public void testAddContrat() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.RESEAU);

        Contrat addedContrat = new Contrat();
        addedContrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        addedContrat.setDateFinContrat(contratDTO.getDateFinContrat());
        addedContrat.setSpecialite(contratDTO.getSpecialite());

        when(contratService.addContrat(contratDTO)).thenReturn(addedContrat);

        // Act
        Contrat actualContrat = contratRestController.addContrat(contratDTO);

        // Assert
        assertNotNull(actualContrat);
        assertEquals(addedContrat.getDateDebutContrat(), actualContrat.getDateDebutContrat());
        assertEquals(addedContrat.getDateFinContrat(), actualContrat.getDateFinContrat());
        assertEquals(addedContrat.getSpecialite(), actualContrat.getSpecialite());
    }


}
