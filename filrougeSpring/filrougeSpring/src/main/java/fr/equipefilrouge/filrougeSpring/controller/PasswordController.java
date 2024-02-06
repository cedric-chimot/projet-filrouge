package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.services.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pour gérer le hashage du mot de passe
 */
@RestController
public class PasswordController {

    private final UsersService usersService;

    /**
     * @param usersService Le service utilisateurs
     */
    public PasswordController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Utilisation de la méthode de cryptage de mot de passe
     * @param plainPassword le mot de passe en clair
     * @return le mot de passe hashé
     */
    @PostMapping("/hash-password")
    public String hashPassword(String plainPassword) {
        return usersService.hashMdp(plainPassword);
    }

}
