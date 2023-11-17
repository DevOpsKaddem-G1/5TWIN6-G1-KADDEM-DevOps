package com.esprit.kaddem.restcontrollers.dtos;

import com.esprit.kaddem.entities.Niveau;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@Setter
@Data // Assuming you have Lombok
public class EquipeDTO {
    private Integer id;
    private String name;
    private Niveau niveau;
    // Add other fields as needed

    // Constructors, getters, setters
}

