package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Formateur;
import fr.equipefilrouge.filrougeSpring.services.impl.FormateurServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pour un formateur
 */
@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    /**
     * Appelle le service du formateur
     */
    public final FormateurServiceImpl formateurServiceImpl;

    /**
     * Constructeur
     * @param formateurServiceImpl retourne le service formateur
     */
    public FormateurController(FormateurServiceImpl formateurServiceImpl) {
        this.formateurServiceImpl = formateurServiceImpl;
    }

    /**
     * Méthode pour créer un formateur
     * @param formateur, le formateur à créer
     * @return le formateur nouvellement créé
     */
    @PostMapping("/create")
    public Formateur createFormateur(@Valid @RequestBody Formateur formateur) {
        return formateurServiceImpl.create(formateur);
    }

}
