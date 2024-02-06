package fr.equipefilrouge.filrougeSpring.dto;

import fr.equipefilrouge.filrougeSpring.entity.SessionFormation;
import lombok.Data;

@Data
public class ParticipationReduitDTO {
    private Long id;
    private SessionFormation session;
}
