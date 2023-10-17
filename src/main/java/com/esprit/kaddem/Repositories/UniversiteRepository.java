package com.esprit.kaddem.Repositories;

import com.esprit.kaddem.Entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Integer> {
}
