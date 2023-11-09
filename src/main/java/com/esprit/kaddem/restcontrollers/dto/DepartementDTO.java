package com.esprit.kaddem.restcontrollers.dto;

public class DepartementDTO {
    private Integer id;
    private String name;

    public DepartementDTO() {
        // Default constructor
    }

    public DepartementDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
