package com.esprit.kaddem.RestControllers;

import com.esprit.kaddem.RestControllers.dtos.UniversiteDTO;
import org.springframework.web.bind.annotation.*;
import com.esprit.kaddem.Entities.Universite;
import com.esprit.kaddem.Services.IUniversiteService;
import java.util.List;

@RestController
@RequestMapping("/universite")

public class UniversiteRestController {
    IUniversiteService universiteService;

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

        Universite updatedUniversite = universiteService.updateUniversite(universite);

        return updatedUniversite;
    }

//    // http://localhost:8089/Kaddem/universite/assignUniversiteToDepartement/1/1
//    @PutMapping("/assignUniversiteToDepartement/{universiteId}/{departementId}")
//    @ResponseBody
//    public void assignUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId,@PathVariable("departementId") Integer departementId) {
//        universiteService.assignUniversiteToDepartement(universiteId,departementId);
//    }
}
