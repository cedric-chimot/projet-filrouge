package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.CentreFormation;
import fr.equipefilrouge.filrougeSpring.services.impl.CentreFormationServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pour un centre de formation
 */
@RestController
@RequestMapping("/centreFormations")
public class CentreFormationController {

    /**
     * Appelle le service du centre de formation
     */
    private final CentreFormationServiceImpl centreFormationService;

    /**
     * Constructeur du controller centre de formation
     * @param centreFormationService, le service centre de formation
     */
    public CentreFormationController(CentreFormationServiceImpl centreFormationService) {
        this.centreFormationService = centreFormationService;
    }

    /**
     * Méthode pour créer un nouveau centre de formation
     * @param centreFormation, le centre de formation à créer
     * @return le centre de formation nouvellement créé
     */
    @PostMapping("/create")
    public CentreFormation createCentre(@RequestBody CentreFormation centreFormation) {
        return centreFormationService.create(centreFormation);
    }

}
