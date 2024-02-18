package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {

    /**
     * Requête pour compter le nombre de formations
     * @return le nombre de formations trouvés
     */
    @Query("SELECT COUNT(DISTINCT f.id) FROM Formation f")
    Long countFormation();

    List<Formation> findByNomContaining(String nom);

    @Query("SELECT b FROM Bootcamp b JOIN b.formations f WHERE f.id = :formationId")
    List<Bootcamp> getBootcampsInFormations(@Param("formationId") Long formationId);


//    List<Formation> findByThemeId(Long themeId);

}
