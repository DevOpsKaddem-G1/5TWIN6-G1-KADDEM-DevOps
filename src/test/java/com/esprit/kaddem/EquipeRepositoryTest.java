package com.esprit.kaddem;

import com.esprit.kaddem.entities.Equipe;
import com.esprit.kaddem.repositories.EquipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;


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
}
