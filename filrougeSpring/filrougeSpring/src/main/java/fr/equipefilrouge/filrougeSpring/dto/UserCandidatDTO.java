package fr.equipefilrouge.filrougeSpring.dto;

import lombok.Data;

@Data
public class UserCandidatDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
}
