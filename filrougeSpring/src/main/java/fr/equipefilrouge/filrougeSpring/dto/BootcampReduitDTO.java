package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import lombok.Data;

import java.util.Date;

@Data
public class BootcampReduitDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    private Lieu lieu;
}
