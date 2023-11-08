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


}
