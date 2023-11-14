package com.esprit.kaddem.restcontrollers.dtos;

import java.util.Date;

import com.esprit.kaddem.entities.Specialite;

public class ContratDTO {

    private Integer id;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;

    // Default constructor is intentionally left empty
    // Add a comment here to explain why this method is empty.

    public ContratDTO() {
        // An empty constructor is provided for frameworks or libraries that require it.
    }

    public Integer getId() {
        return id;
    }

    public Date getDateDebutContrat() {
        return dateDebutContrat;
    }

    public Date getDateFinContrat() {
        return dateFinContrat;
    }

    public Specialite getSpecialite() {
        return specialite;
    }
}
