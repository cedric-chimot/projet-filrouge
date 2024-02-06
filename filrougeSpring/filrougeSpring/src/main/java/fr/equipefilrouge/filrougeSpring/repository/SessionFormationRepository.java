package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.SessionFormation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionFormationRepository extends JpaRepository<SessionFormation, Long> {
}
