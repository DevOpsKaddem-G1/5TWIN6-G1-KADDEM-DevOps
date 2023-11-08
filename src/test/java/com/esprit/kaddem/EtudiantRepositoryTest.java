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

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)//JUnit5
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class EtudiantRepositoryTest {

    @Autowired
    EtudiantRepository etudiantRepository;

    @BeforeEach
    public void initiateDefaultUsers() {
        etudiantRepository.save(new Etudiant( "Salim", "Ben Younes", Option.SE));
        etudiantRepository.save(new Etudiant( "Aymen", "Chaaban", Option.GAMIX));
        etudiantRepository.save(new Etudiant( "Karim", "Trabelsi", Option.TWIN));
        etudiantRepository.save(new Etudiant( "Elyes", "Boudhina", Option.INFINI));
    }

    @AfterEach
    public void destroyAll() {
        etudiantRepository.deleteAll();
    }

    @Test
    public void testGetAllEtudiants() {
        List<Etudiant> etudiantList = (List<Etudiant>) etudiantRepository.findAll();
        Assertions.assertThat(etudiantList.size()).isEqualTo(4);
        for (Etudiant etudiant : etudiantList) {
            Assertions.assertThat(etudiant).isNotNull();

            // Add assertions for each field and value
            Assertions.assertThat(etudiant.getIdEtudiant()).isNotNull().isNotNegative().isGreaterThan(0);
            Assertions.assertThat(etudiant.getPrenomE()).isNotNull().isNotEmpty();
            Assertions.assertThat(etudiant.getNomE()).isNotNull().isNotEmpty();
            Assertions.assertThat(etudiant.getOp()).isNotNull().isIn(Option.values());
        }
    }

    @Test
    public void testCreateEtudiant() {
        //Student to be persisted
        Etudiant etudiantToBePersisted = new Etudiant("Aziz", "Ben Hmida", Option.SAE);
        //Returned value
        Etudiant etudiantReturned = etudiantRepository.save(etudiantToBePersisted);

        //Testing Student Id value
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isNotNull();
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isNotNegative();
        Assertions.assertThat(etudiantReturned.getIdEtudiant()).isGreaterThan(0);
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


}
