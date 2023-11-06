package com.esprit.kaddem.repositories;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Niveau;
import com.esprit.kaddem.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

}
