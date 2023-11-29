package com.esprit.kaddem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;
import com.esprit.kaddem.repositories.EtudiantRepository;
import com.esprit.kaddem.restcontrollers.dtos.ContratDTO;
import com.esprit.kaddem.services.ContratServiceImpl;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Slf4j
public class ContratServiceTest {
        @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Test
    public void testRetrieveAllContrats() {
        // Creating 2 default contracts
        Contrat contrat1 = new Contrat(1, new Date(), new Date(), Specialite.IA, false, 1000, new Etudiant());
        Contrat contrat2 = new Contrat(2, new Date(), new Date(), Specialite.CLOUD, true, 1500, new Etudiant());
    
        when(contratRepository.findAll()).thenReturn(Arrays.asList(contrat1, contrat2));
        List<Contrat> contratsList = contratService.retrieveAllContrats();
    
        // Asserting size
        assertEquals(2, contratsList.size());
    
        // Asserting contract 1 values
        assertNotNull(contratsList.get(0).getIdContrat());
    
        // Asserting contract 2 values
        assertNotNull(contratsList.get(1).getIdContrat());
    }
    

    @Test
    public void testUpdateContrat() {
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
        assertNotNull(updatedContrat);
        assertEquals(contratDTO.getDateDebutContrat(), updatedContrat.getDateDebutContrat());
        assertEquals(contratDTO.getDateFinContrat(), updatedContrat.getDateFinContrat());
        assertEquals(contratDTO.getSpecialite(), updatedContrat.getSpecialite());
    }

    @Test
    public void testAddContrat() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.CLOUD);

        // Act
        Contrat addedContrat = contratService.addContrat(contratDTO);

        // Assert
        assertNotNull(addedContrat);
        assertEquals(contratDTO.getDateDebutContrat(), addedContrat.getDateDebutContrat());
        assertEquals(contratDTO.getDateFinContrat(), addedContrat.getDateFinContrat());
        assertEquals(contratDTO.getSpecialite(), addedContrat.getSpecialite());
    }

    @Test
    public void testRetrieveContrat() {
        // Arrange
        int contratId = 1;
        Contrat expectedContrat = new Contrat();
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(expectedContrat));

        // Act
        Contrat actualContrat = contratService.retrieveContrat(contratId);

        // Assert
        assertNotNull(actualContrat);
        assertEquals(expectedContrat, actualContrat);
    }

    @Test
    public void testRemoveContrat() {
        // Arrange
        int contratId = 1;

        // Act
        contratService.removeContrat(contratId);

        // Assert
        verify(contratRepository, times(1)).deleteById(contratId);
    }

    @Test
    public void testAddAndAffectContratToEtudiant() {
        // Arrange
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setDateDebutContrat(new Date());
        contratDTO.setDateFinContrat(new Date());
        contratDTO.setSpecialite(Specialite.SECURITE);

        String nomE = "John";
        String prenomE = "Doe";

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE(nomE);
        etudiant.setPrenomE(prenomE);

        when(etudiantRepository.findByNomEAndPrenomE(nomE, prenomE)).thenReturn(etudiant);

        // Act
        Contrat addedContrat = contratService.addAndAffectContratToEtudiant(contratDTO, nomE, prenomE);

        // Assert
        assertNotNull(addedContrat);
        assertEquals(etudiant, addedContrat.getEtudiant());
    }

    @Test
    public void testNbContratsValides() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        // Act
        Integer result = contratService.nbContratsValides(startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(Integer.valueOf(5), result);
    }

    @Test
    public void testRetrieveAndUpdateStatusContrat() {
        // Arrange
        List<Contrat> contrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findAll()).thenReturn(contrats);

        // Act
        contratService.retrieveAndUpdateStatusContrat();

        // Assert (use appropriate assertions based on your logic)
    }

    @Test
    public void testGetChiffreAffaireEntreDeuxDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        Contrat contrat1 = new Contrat();
        contrat1.setSpecialite(Specialite.IA);
        contrat1.setMontantContrat(1000);

        Contrat contrat2 = new Contrat();
        contrat2.setSpecialite(Specialite.CLOUD);
        contrat2.setMontantContrat(800);

        when(contratRepository.findAll()).thenReturn(Arrays.asList(contrat1, contrat2));

        // Act
        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert (use appropriate assertions based on your logic)
    }

    
}
