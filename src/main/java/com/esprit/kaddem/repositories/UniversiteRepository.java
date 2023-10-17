package com.esprit.kaddem.repositories;

import com.esprit.kaddem.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Integer> {
}
