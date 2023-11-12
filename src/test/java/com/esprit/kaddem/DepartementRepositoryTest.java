package com.esprit.kaddem;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.repositories.DepartementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository departementRepository;

    @Test
    void testFindAll() {
        // Given
        Departement departement1 = new Departement(1, "Departement 1");
        Departement departement2 = new Departement(2, "Departement 2");

        departementRepository.save(departement1);
        departementRepository.save(departement2);

        // When
        List<Departement> departements = departementRepository.findAll();

        // Then
        assertEquals(2, departements.size());
        assertEquals(departement1, departements.get(0));
        assertEquals(departement2, departements.get(1));
    }

    // Additional tests for custom queries or methods can be added as needed
}
