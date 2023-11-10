package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class UniversiteRepositoryUnitTest {

    @Mock
    UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        universiteRepository.save(new Universite(1,"Manar"));
        universiteRepository.save(new Universite(2,"Sfax"));
    }

    @AfterEach
    void destroy() {
        universiteRepository.deleteAll();
    }

    @Test
    void testGetInvalidUniversite() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> universiteRepository.findById(1).get());

        Assertions.assertThat(exception)
                .isNotNull()
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }





    @Test
    void testDeleteUniversite() {
        Universite saved = new Universite(5, "ron");
        universiteRepository.save(saved);
        universiteRepository.delete(saved);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> universiteRepository.findById(5).get());

        Assertions.assertThat(exception)
                .isNotNull()
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No value present");
    }


}
