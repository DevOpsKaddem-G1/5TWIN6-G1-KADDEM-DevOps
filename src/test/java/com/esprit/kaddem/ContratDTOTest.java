package com.esprit.kaddem;
import org.junit.jupiter.api.Test;

import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.restcontrollers.dtos.ContratDTO;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

 class ContratDTOTest {

    @Test
     void testContratDTO() {
        // Arrange
        Integer id = 1;
        Date startDate = new Date();
        Date endDate = new Date();
        Specialite specialite = Specialite.IA;

        // Act
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setId(id);
        contratDTO.setDateDebutContrat(startDate);
        contratDTO.setDateFinContrat(endDate);
        contratDTO.setSpecialite(specialite);

        // Assert
        assertThat(contratDTO.getId()).isEqualTo(id);
        assertThat(contratDTO.getDateDebutContrat()).isEqualTo(startDate);
        assertThat(contratDTO.getDateFinContrat()).isEqualTo(endDate);
        assertThat(contratDTO.getSpecialite()).isEqualTo(specialite);
    }

    @Test
     void testEmptyConstructor() {
        // Arrange

        // Act
        ContratDTO contratDTO = new ContratDTO();

        // Assert
        // Add assertions if there's a specific behavior you expect from the empty constructor
        assertThat(contratDTO).isNotNull();
    }
}