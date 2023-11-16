package com.esprit.kaddem.restcontrollers;

import com.esprit.kaddem.restcontrollers.dtos.UniversiteDTO;
import org.springframework.web.bind.annotation.*;
import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.services.IUniversiteService;
import java.util.List;

@RestController
@RequestMapping("/universite")

public class UniversiteRestController {
    IUniversiteService universiteService;

    @GetMapping("/retrieve-all-universites")
    @ResponseBody
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }


    @GetMapping("/retrieve-universite/{universite-id}")
    @ResponseBody
    public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @PostMapping("/add-universite")
    @ResponseBody
    public UniversiteDTO addUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getNomUniv());



        UniversiteDTO responseDTO = new UniversiteDTO();
        responseDTO.setNomUniv(universite.getNomUniv());

        return responseDTO;
    }


    @PutMapping("/update-universite")
    @ResponseBody
    public Universite updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
        Universite universite = new Universite();
        universite.setNomUniv(universiteDTO.getNomUniv());

       return universiteService.updateUniversite(universite);


    }


}
