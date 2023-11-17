package com.esprit.kaddem.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipe  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer idEquipe;

    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToMany(mappedBy = "equipes",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   //@JsonIgnore
    private List<Etudiant> etudiants;


}
