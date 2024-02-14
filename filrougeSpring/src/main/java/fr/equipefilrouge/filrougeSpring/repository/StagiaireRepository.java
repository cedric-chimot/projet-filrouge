package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Stagiaire;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Le repository du stagiaire
 */
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    /**
     * Requête pour vérifier l'existence de l'email en BDD
     * @param email l'email à vérifier
     * @return l'utilisateur
     */
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Stagiaire findByMail(@Param("email") String email);

}
