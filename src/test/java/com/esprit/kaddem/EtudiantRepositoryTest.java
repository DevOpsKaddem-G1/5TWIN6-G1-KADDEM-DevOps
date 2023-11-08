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

}
