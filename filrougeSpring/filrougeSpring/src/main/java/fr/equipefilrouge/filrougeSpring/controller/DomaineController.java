package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Domaine;
import fr.equipefilrouge.filrougeSpring.services.impl.DomaineServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pour un domaine
 */
@RestController
@RequestMapping("/domaines")
public class DomaineController {

    /**
     * Le service du domaine
     */
    private final DomaineServiceImpl domaineService;

    /**
     * Le controller
     * @param domaineService le service Domaine
     */
    public DomaineController(DomaineServiceImpl domaineService) {
        this.domaineService = domaineService;
    }

    /**
     * Méthode pour créer un domaine
     * @param domaine le domaine à créer
     * @return le domaine nouvellement créé
     */
    @PostMapping("/create")
    public Domaine createDomaine(@RequestBody Domaine domaine) {
        return domaineService.create(domaine);
    }


}
