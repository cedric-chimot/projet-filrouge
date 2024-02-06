package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.SessionReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.SessionFormation;
import fr.equipefilrouge.filrougeSpring.services.impl.SessionFormationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour une session de formation
 */
@RestController
@RequestMapping("/sessionFormations")
public class SessionFormationController {

    /**
     * Appel du service session de formation
     */
    private final SessionFormationServiceImpl sessionFormationService;

    /**
     * Constructeur du controller
     * @param sessionFormationService le service de la session de formation
     */
    public SessionFormationController(SessionFormationServiceImpl sessionFormationService) {
        this.sessionFormationService = sessionFormationService;
    }

    /**
     * Méthode pour créer une session de formation
     * @param sessionFormation la session de formation à créer
     * @return la session de formation nouvellement créée
     */
    @PostMapping("/create")
    public SessionFormation createSession(@RequestBody SessionFormation sessionFormation) {
        return sessionFormationService.create(sessionFormation);
    }


    /**
     * Méthode pour mettre à jour une session de formation
     * @param sessionFormation la session de formation à mettre à jour
     * @return la session de formation mis à jour
     */
    @PatchMapping("/update")
    public SessionFormation updateSession(@RequestBody SessionFormation sessionFormation) {
        return sessionFormationService.update(sessionFormation);
    }

    /**
     * Affiche toutes les sessions de formations
     * @return la liste des sessions formations
     */
    @GetMapping("/all")
    public List<SessionReduitDTO> getAllSessions() {
        return sessionFormationService.findAllSessionReduit();
    }

    /**
     * Méthode pour rechercher une session avec son identifiant
     * @param id l'identifiant de la session
     * @return la session recherchée
     */
    @GetMapping("/{id}")
    public SessionFormation getSessionById(@PathVariable Long id) {
        return sessionFormationService.findById(id);
    }

    /**
     * Supprimer une session de formation selon son id
     * @param id l'identifiant de la session
     * @return la session supprimée
     */
    @DeleteMapping("/delete/{id}")
    public SessionFormation deleteSessionById(@PathVariable Long id) {
        return sessionFormationService.deleteById(id);
    }

    /**
     * Supprimer toutes les sessions de formation
     */
    @DeleteMapping("/delete/all")
    public void deleteAllSession() {
        sessionFormationService.deleteAll();
    }

}
