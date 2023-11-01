package com.esprit.kaddem.repositories;

import com.esprit.kaddem.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipeRepository  extends JpaRepository<Equipe, Integer> {
    

}
