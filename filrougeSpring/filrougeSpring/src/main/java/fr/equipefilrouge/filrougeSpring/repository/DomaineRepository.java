package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Le repository qui répertorie toutes les méthodes du CRUD pour un domaine
 */
public interface DomaineRepository extends JpaRepository<Domaine, Long> {
}
