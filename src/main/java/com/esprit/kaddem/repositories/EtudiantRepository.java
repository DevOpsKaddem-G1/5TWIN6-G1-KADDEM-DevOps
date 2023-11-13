package com.esprit.kaddem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esprit.kaddem.entities.Etudiant;

import com.esprit.kaddem.entities.Contrat;
import com.esprit.kaddem.entities.Niveau;
import com.esprit.kaddem.entities.Specialite;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    //public List<Etudiant> findByDepartementIdDepartement(Long idDepartement);
    //public List<Etudiant> findByEquipesNiveau(Niveau nv);


















    List<Etudiant> findByDepartementIdDepartement(Integer idDepartement);

   List<Etudiant> findByEquipesNiveau(Niveau niveau);

   Etudiant findByNomEAndPrenomE(String nomE, String prenomE);






    @Query("SELECT e FROM Etudiant e, Contrat c where "
           + "e.idEtudiant = c.etudiant.idEtudiant "
           + "and c.specialite =:specialite ")
   List<Etudiant> retrieveEtudiantsByContratSpecialite(@Param("specialite") Specialite specialite);

 //  @Query(value = "SELECT * FROM etudiant e INNER JOIN contrat c ON e.id_etudiant =   c.etudiant_id_etudiant where c.specialite = :specialite", nativeQuery = true)
  // List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(@Param("specialite") Specialite specialite);
 @Query(value = "SELECT * FROM etudiant e INNER JOIN contrat c ON e.id_etudiant =   c.etudiant_id_etudiant where c.specialite =:specialite", nativeQuery = true)
 List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(@Param("specialite") String specialite);




}
