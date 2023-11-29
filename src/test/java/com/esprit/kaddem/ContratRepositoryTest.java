package com.esprit.kaddem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.repositories.ContratRepository;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
public class ContratRepositoryTest {
        @BeforeEach
    void initiateDefaultContrats() {
        Contrat defaultContrat1 = new Contrat();
        defaultContrat1.setIdContrat(1);
        defaultContrat1.setDateDebutContrat(new Date());
        defaultContrat1.setDateFinContrat(new Date());
        defaultContrat1.setSpecialite(Specialite.IA);

        Contrat defaultContrat2 = new Contrat();
        defaultContrat2.setIdContrat(2);
        defaultContrat2.setDateDebutContrat(new Date());
        defaultContrat2.setDateFinContrat(new Date());
        defaultContrat2.setSpecialite(Specialite.CLOUD);

        when(contratRepository.findById(1)).thenReturn(Optional.of(defaultContrat1));
        when(contratRepository.findById(2)).thenReturn(Optional.of(defaultContrat2));
    }

    @AfterEach
    void destroyAll() {
        contratRepository.deleteAll();
    }
        @Mock
    private ContratRepository contratRepository;

    @Test
    public void testGetnbContratsValides() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        // Act
        Integer result = contratRepository.getnbContratsValides(startDate, endDate);

        // Assert
        assertEquals(Integer.valueOf(5), result);
    }

    @Test
    public void testFindByEtudiantIdEtudiant() {
        // Arrange
        Integer idEtudiant = 1;
        List<Contrat> expectedContrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findByEtudiantIdEtudiant(idEtudiant)).thenReturn(expectedContrats);

        // Act
        List<Contrat> actualContrats = contratRepository.findByEtudiantIdEtudiant(idEtudiant);

        // Assert
        assertEquals(expectedContrats, actualContrats);
    }

}
