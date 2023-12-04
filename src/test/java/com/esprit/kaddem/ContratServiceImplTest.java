package com.esprit.kaddem;
import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;
import com.esprit.kaddem.restcontrollers.dtos.ContratDTO;
import com.esprit.kaddem.services.ContratServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
 class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Test
     void testRetrieveAllContrats() {
        // Arrange
        List<Contrat> expectedContrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findAll()).thenReturn(expectedContrats);

        // Act
        List<Contrat> actualContrats = contratService.retrieveAllContrats();

        // Assert
        assertThat(actualContrats).isEqualTo(expectedContrats);
    }

    @Test
     void testUpdateContrat() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setId(1);
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.IA);

        Contrat existingContrat = new Contrat();
        existingContrat.setIdContrat(1);

        when(contratRepository.findById(1)).thenReturn(Optional.of(existingContrat));

        // Act
        Contrat updatedContrat = contratService.updateContrat(contratDTO);

        // Assert
        assertThat(updatedContrat).isNotNull();
        assertThat(contratDTO.getDateDebutContrat()).isEqualTo(updatedContrat.getDateDebutContrat());
        assertThat(contratDTO.getDateFinContrat()).isEqualTo(updatedContrat.getDateFinContrat());
        assertThat(contratDTO.getSpecialite()).isEqualTo(updatedContrat.getSpecialite());
    }

    @Test
     void testRetrieveContrat() {
        // Arrange
        int contratId = 1;
        Contrat expectedContrat = new Contrat();
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(expectedContrat));

        // Act
        Contrat actualContrat = contratService.retrieveContrat(contratId);

        // Assert
        assertThat(actualContrat).isNotNull();
        assertThat(expectedContrat).isEqualTo(actualContrat);
    }

    @Test
     void testRemoveContrat() {
        // Arrange
        int contratId = 1;

        // Act
        contratService.removeContrat(contratId);

        // Assert
        verify(contratRepository, times(1)).deleteById(contratId);
    }

    @Test
     void testAddContrat() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.CLOUD);

        Contrat addedContrat = new Contrat();
        addedContrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        addedContrat.setDateFinContrat(contratDTO.getDateFinContrat());
        addedContrat.setSpecialite(contratDTO.getSpecialite());

        when(contratRepository.save(any(Contrat.class))).thenReturn(addedContrat);

        // Act
        Contrat actualContrat = contratService.addContrat(contratDTO);

        // Assert
        assertThat(actualContrat).isNotNull();
        assertThat(addedContrat.getDateDebutContrat()).isEqualTo(actualContrat.getDateDebutContrat());
        assertThat(addedContrat.getDateFinContrat()).isEqualTo(actualContrat.getDateFinContrat());
        assertThat(addedContrat.getSpecialite()).isEqualTo(actualContrat.getSpecialite());
    }
    @Test
    void testNbContratsValides() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        
        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);
    
        // Act
        Integer result = contratService.nbContratsValides(startDate, endDate);
    
        // Assert
        assertThat(result).isNotNull().isEqualTo(5);
    }

    @Test
     void testRetrieveAndUpdateStatusContrat() {
        // Arrange
        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setDateFinContrat(new Date());

        Contrat contrat2 = new Contrat();
        contrat2.setIdContrat(2);
        contrat2.setDateFinContrat(new Date());

        List<Contrat> contrats = Arrays.asList(contrat1, contrat2);
        when(contratRepository.findAll()).thenReturn(contrats);

        // Act
        contratService.retrieveAndUpdateStatusContrat();

        // Assert (use appropriate assertions based on your logic)
        verify(contratRepository, times(2)).save(any(Contrat.class));
    }

}