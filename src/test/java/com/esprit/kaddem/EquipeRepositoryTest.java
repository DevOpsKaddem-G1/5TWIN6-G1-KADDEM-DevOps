package com.esprit.kaddem;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EquipeRepositoryTest {
    @Autowired
    private EquipeRepository equipeRepository;

    @Test
     void testFindById() {
        // Create and save a sample Equipe entity
        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Sample Equipe");
        equipe = equipeRepository.save(equipe);

        // Retrieve the entity by ID
        Optional<Equipe> retrievedEquipe = equipeRepository.findById(equipe.getIdEquipe());

        // Assert that the retrieved entity matches the original entity
        assertTrue(retrievedEquipe.isPresent());
        assertEquals(equipe.getNomEquipe(), retrievedEquipe.get().getNomEquipe());
    }
    @Test
    void testFindAll() {
        // Save multiple Equipe entities
        Equipe equipe1 = new Equipe();
        equipe1.setNomEquipe("Equipe 1");
        equipeRepository.save(equipe1);

        Equipe equipe2 = new Equipe();
        equipe2.setNomEquipe("Equipe 2");
        equipeRepository.save(equipe2);

        // Retrieve all entities from the repository
        Iterable<Equipe> allEquipes = equipeRepository.findAll();

        // Assert that the number of retrieved entities matches the number saved
        long count = StreamSupport.stream(allEquipes.spliterator(), false).count();
        assertEquals(2, count);
    }

    @Test
    void testDeleteById() {
        // Create and save a sample Equipe entity
        Equipe equipe = new Equipe();
        equipe.setNomEquipe("Equipe to delete");
        equipe = equipeRepository.save(equipe);

        // Delete the entity by ID
        equipeRepository.deleteById(equipe.getIdEquipe());

        // Try to retrieve the deleted entity
        Optional<Equipe> retrievedEquipe = equipeRepository.findById(equipe.getIdEquipe());

        // Assert that the entity is not present after deletion
        assertFalse(retrievedEquipe.isPresent());
    }

// Add more tests as needed based on your application requirements

}
