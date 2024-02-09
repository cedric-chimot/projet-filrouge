package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.services.impl.LieuServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pour un centre de formation
 */
@RestController
@RequestMapping("/lieu")
public class LieuController {

    /**
     * Appelle le service du centre de formation
     */
    private final LieuServiceImpl lieuService;

    /**
     * Constructeur du controller centre de formation
     * @param lieuService, le service centre de formation
     */
    public LieuController(LieuServiceImpl lieuService) {
        this.lieuService = lieuService;
    }

    /**
     * Méthode pour créer un nouveau centre de formation
     * @param lieu, le centre de formation à créer
     * @return le centre de formation nouvellement créé
     */
    @PostMapping("/create")
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.create(lieu);
    }

}
