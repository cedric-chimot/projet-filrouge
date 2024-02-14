package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
