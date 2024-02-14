package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Le repository du stagiaire
 */
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {}
