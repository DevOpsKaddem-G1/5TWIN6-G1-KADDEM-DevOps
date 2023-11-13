package com.esprit.kaddem;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.repositories.DepartementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository departementRepository;

    @Test
    void testFindAll() {
        // Given

            // Create and save a sample Departement entity
            Departement departement = new Departement();
            departement.setNomDepart("Sample Departement");
            departement = departementRepository.save(departement);

            // Retrieve the entity by ID
            Optional<Departement> retrievedDepartement = departementRepository.findById(departement.getIdDepartement());

            // Assert that the retrieved entity matches the original entity
            assertTrue(retrievedDepartement.isPresent());
            assertEquals(departement.getNomDepart(), retrievedDepartement.get().getNomDepart());

    }

    // Additional tests for custom queries or methods can be added as needed
}
