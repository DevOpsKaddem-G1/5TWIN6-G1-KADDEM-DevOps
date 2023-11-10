package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KaddemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaddemApplication.class, args);
	}

	@Autowired
	private UniversiteRepository universiteRepository;

	@Bean
	ApplicationRunner init() {
		return args -> {
			// Save
			universiteRepository.save(new Universite(1, "Université de Technologie Innovante"));
			universiteRepository.save(new Universite(3, "Ibn Khaldûn (1332-1406)"));
			universiteRepository.save(new Universite(4, "Université Internationale des Sciences"));
			universiteRepository.save(new Universite(2, "Université des Arts Créatifs"));
			universiteRepository.save(new Universite(5, "Université de Recherche Avancée"));
			universiteRepository.save(new Universite(6, "Université Mondiale de la Paix"));
			universiteRepository.save(new Universite(7, "Université Numérique Avancée"));
			universiteRepository.save(new Universite(8, "Université Cosmique des Étoiles"));
			universiteRepository.save(new Universite(9, "Université Polytechnique du Futur"));
			universiteRepository.save(new Universite(10, "Université Écologique Verte"));
			// fetch
			// universiteRepository.findAll().forEach(System.out::println);
		};
	}
}
