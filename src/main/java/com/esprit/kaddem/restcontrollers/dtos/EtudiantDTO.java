package com.esprit.kaddem.restcontrollers.dtos;

import com.esprit.kaddem.entities.Option;

public class EtudiantDTO {
    public EtudiantDTO( String prenomE, String nomE, Option op) {
        this.prenomE = prenomE;
        this.nomE = nomE;
        this.op = op;
    }

    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    private Option op;


    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public String getNomE() {
        return nomE;
    }

    public Option getOp() {
        return op;
    }
}
