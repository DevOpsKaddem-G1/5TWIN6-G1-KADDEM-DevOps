package com.esprit.kaddem.restcontrollers;

import com.esprit.kaddem.restcontrollers.dtos.UniversiteDTO;
import com.esprit.kaddem.services.UniversiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esprit.kaddem.entities.Universite;

import java.util.List;

@RestController
@RequestMapping("/universite")
public class UniversiteRestController {

    private final UniversiteServiceImpl universiteService;

    @Autowired
    public UniversiteRestController(UniversiteServiceImpl universiteService) {
        this.universiteService = universiteService;
    }

    // http://localhost:8089/Kaddem/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    @ResponseBody
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    // http://localhost:8089/Kaddem/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    @ResponseBody
    public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    // http://localhost:8089/Kaddem/universite/add-universite
    @PostMapping("/add-universite")
    @ResponseBody
    public UniversiteDTO addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getNomUniv());

        // Utilisez le service pour ajouter l'entité Universite

        UniversiteDTO responseDTO = new UniversiteDTO();
        responseDTO.setNomUniv(universite.getNomUniv());

        return responseDTO;
    }

    // http://localhost:8089/Kaddem/universite/update-universite
    @PutMapping("/update-universite")
    @ResponseBody
    public Universite updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getNomUniv());

        return universiteService.updateUniversite(universite);
    }

    @DeleteMapping("/delete-universite/{universite-id}")
    public ResponseEntity<Void> deleteUniversite(@PathVariable("universite-id") Integer universiteId) {
        try {
            universiteService.deleteUniversite(universiteId);
            return ResponseEntity.ok().build(); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}
