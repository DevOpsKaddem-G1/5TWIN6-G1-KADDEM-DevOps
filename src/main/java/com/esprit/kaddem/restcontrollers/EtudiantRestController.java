package com.esprit.kaddem.restcontrollers;

import com.esprit.kaddem.entities.Etudiant;
import com.esprit.kaddem.entities.Niveau;
import com.esprit.kaddem.entities.Specialite;
import com.esprit.kaddem.restcontrollers.dtos.EtudiantDTO;
import com.esprit.kaddem.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
public class EtudiantRestController {
    @Autowired
    IEtudiantService etudiantService;
    // http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    @ResponseBody
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    // http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiantId}")
    @ResponseBody
    public Etudiant retrieveEtudiant(@PathVariable("etudiantId") Integer etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

       // http://localhost:8089/Kaddem/etudiant/add-etudiant
       @PostMapping("/add-etudiant")
       @ResponseBody
       public Etudiant addEtudiant(@RequestBody EtudiantDTO e) {
           return etudiantService.addEtudiant(e);
       }
   
       // http://localhost:8089/Kaddem/etudiant/update-etudiant
       @PutMapping("/update-etudiant")
       @ResponseBody
       public Etudiant updateEtudiant(@RequestBody EtudiantDTO e) {
          return etudiantService.updateEtudiant(e);
       }
       // http://localhost:8089/Kaddem/etudiant/removeEtudiant
       @DeleteMapping("/removeEtudiant/{idEtudiant}")
       @ResponseBody
       public String removeEtudiant(@PathVariable("idEtudiant") Integer idEtudiant) {
           return etudiantService.removeEtudiant(idEtudiant);
       }
}
