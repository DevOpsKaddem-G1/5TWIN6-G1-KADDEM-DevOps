package com.esprit.kaddem;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Option;
import com.esprit.kaddem.repositories.EtudiantRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)//JUnit5
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class EtudiantRepositoryTest {

    @Autowired
    EtudiantRepository etudiantRepository;

    @BeforeEach
    void initiateDefaultUsers() {
        etudiantRepository.save(new Etudiant( "Salim", "Ben Younes", Option.SE));
        etudiantRepository.save(new Etudiant( "Aymen", "Chaaban", Option.GAMIX));
        etudiantRepository.save(new Etudiant( "Karim", "Trabelsi", Option.TWIN));
        etudiantRepository.save(new Etudiant( "Elyes", "Boudhina", Option.INFINI));
    }

    @AfterEach
    void destroyAll() {
        etudiantRepository.deleteAll();
    }

    @Test
    void testGetAllEtudiants() {
        List<Etudiant> etudiantList = (List<Etudiant>) etudiantRepository.findAll();
        Assertions.assertThat(etudiantList).hasSize(4);
        for (Etudiant etudiant : etudiantList) {
            Assertions.assertThat(etudiant).isNotNull();

            // Add assertions for each field and value
            Assertions.assertThat(etudiant.getIdEtudiant()).isNotNull().isNotNegative().isPositive();
            Assertions.assertThat(etudiant.getPrenomE()).isNotNull().isNotEmpty();
            Assertions.assertThat(etudiant.getNomE()).isNotNull().isNotEmpty();
            Assertions.assertThat(etudiant.getOp()).isNotNull().isIn(Option.values());
        }
    }

    @Test
    void testCreateEtudiant() {
        //Student to be persisted
        Etudiant etudiantToBePersisted = new Etudiant("Aziz", "Ben Hmida", Option.SAE);
        //Returned value
        Etudiant etudiantReturned = etudiantRepository.save(etudiantToBePersisted);

        //Testing Student Id value
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isNotNull();
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isNotNegative();
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isPositive();
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isEqualTo(etudiantToBePersisted.getIdEtudiant());

        //Testing Student First Name value
        Assertions.assertThat(etudiantReturned.getPrenomE()).isNotNull();
        Assertions.assertThat(etudiantReturned.getPrenomE()).isNotEmpty();
        Assertions.assertThat(etudiantReturned.getPrenomE()).hasSize(etudiantToBePersisted.getPrenomE().length());
        Assertions.assertThat(etudiantReturned.getPrenomE()).isEqualTo(etudiantToBePersisted.getPrenomE());

        //Testing Student Last Name value
        Assertions.assertThat(etudiantReturned.getNomE()).isNotNull();
        Assertions.assertThat(etudiantReturned.getNomE()).isNotEmpty();
        Assertions.assertThat(etudiantReturned.getNomE()).hasSize(etudiantToBePersisted.getNomE().length());
        Assertions.assertThat(etudiantReturned.getNomE()).isEqualTo(etudiantToBePersisted.getNomE());

        //Testing Student Option value
        Assertions.assertThat(etudiantReturned.getOp()).isNotNull();
        // Checking that the returned option is one of the expected options
        Assertions.assertThat(etudiantReturned.getOp()).isIn(Option.GAMIX, Option.SE, Option.SAE, Option.INFINI, Option.TWIN);
        // Comparing that the returned option is the one we persisted or the expected option
        Assertions.assertThat(etudiantReturned.getOp()).isEqualTo(etudiantToBePersisted.getOp());
    }

    @Test
    void testUpdateEtudiant() {
        // Create a new student
        Etudiant etudiantToBePersisted = new Etudiant("John", "Doe", Option.SE);
        Etudiant savedEtudiant = etudiantRepository.save(etudiantToBePersisted);

        // Update the student's information
        savedEtudiant.setPrenomE("UpdatedFirstName");
        savedEtudiant.setNomE("UpdatedLastName");
        savedEtudiant.setOp(Option.GAMIX);
        Etudiant updatedEtudiant = etudiantRepository.save(savedEtudiant);

        // Retrieve the updated etudiant from the repository
        Etudiant retrievedEtudiant = etudiantRepository.findById(updatedEtudiant.getIdEtudiant())
                .orElseThrow(NoSuchElementException::new);

        // Verify that the etudiant's information has been updated correctly
        Assertions.assertThat(retrievedEtudiant.getPrenomE()).isEqualTo("UpdatedFirstName");
        Assertions.assertThat(retrievedEtudiant.getNomE()).isEqualTo("UpdatedLastName");
        Assertions.assertThat(retrievedEtudiant.getOp()).isEqualTo(Option.GAMIX);
    }

    @Test
    void testDeleteEtudiant() {
        // Arrange
        Etudiant etudiantToBePersisted = new Etudiant("Amen", "Jouini", Option.TWIN);
        Etudiant savedEtudiant = etudiantRepository.save(etudiantToBePersisted);

        // Act
        etudiantRepository.delete(etudiantToBePersisted);

        // Assert
        assertFalse(etudiantRepository.findById(savedEtudiant.getIdEtudiant()).isPresent());
    }
}
