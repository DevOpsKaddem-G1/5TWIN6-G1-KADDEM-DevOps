package com.esprit.kaddem.restcontrollers.dtos;
import java.io.Serializable;
import java.util.Date;

import com.esprit.kaddem.entities.Specialite;

public class ContratDTO {

    private Integer id;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;


    public ContratDTO() {
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
