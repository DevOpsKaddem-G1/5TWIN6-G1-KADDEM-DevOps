package com.esprit.kaddem.restcontrollers;

import com.esprit.kaddem.entities.Departement;
import com.esprit.kaddem.restcontrollers.dto.DepartementDTO;
import com.esprit.kaddem.services.IDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {
    private final IDepartementService departementService;

    @Autowired
    public DepartementRestController(IDepartementService departementService) {
        this.departementService = departementService;
    }
    // http://localhost:8089/Kaddem/departement/retrieve-all-departements
    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        return departementService.retrieveAllDepartements();
    }

    // http://localhost:8089/Kaddem/departement/retrieve-departement/8
    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/departement/add-departement
    @PostMapping("/add-departement")
    @ResponseBody
    public Departement addDepartement(@RequestBody DepartementDTO departementDTO) {
        return departementService.addDepartement(departementDTO);

    }

    // http://localhost:8089/Kaddem/departement/update-departement
    @PutMapping("/update-departement")
    @ResponseBody
    public Departement updateDepartement(@RequestBody DepartementDTO departementDTO) {
        return departementService.updateDepartement(departementDTO);
    }




    // http://localhost:8089/Kaddem/departement/retrieveDepartementsByUniversite/1
    @GetMapping("/retrieveDepartementsByUniversite/{idUniversite}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByUniversite(@PathVariable("idUniversite") Integer idUniversite) {

        return departementService.retrieveDepartementsByUniversite(idUniversite);
    }


}
