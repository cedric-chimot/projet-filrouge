package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    /**
     * Appelle le service users
     */
    public final UsersService usersService;

    /**
     * Constructeur
     * @param usersService le service users
     */
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Endpoint pour gérer la connexion
     * @param identifiants les identifiants de connexion
     * @return une réponse ok ou erreur au moment de la validation
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> identifiants) {
        // Extraire l'e-mail et le mot de passe à partir du corps de la requête
        String email = identifiants.get("email");
        String mdp = identifiants.get("mdp");

        // Vérifier les informations d'identification
        if (usersService.areIdsValid(email, mdp)) {
            System.out.println("connexion réussie !");
            // Si les informations d'identification sont valides, renvoyer une réponse 200 OK avec un message de succès
            return ResponseEntity.ok("Connexion réussie !");
        } else {
            System.out.println("connexion impossible !");
            // Si les informations d'identification ne sont pas valides, renvoyer une réponse 401 Unauthorized avec un message d'erreur
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects !");
        }
    }

    /**
     * Endpoint pour obtenir le nombre d'utilisateurs par type.
     * @param type Le type d'utilisateur pour lequel obtenir le nombre.
     * @return ResponseEntity contenant le nombre d'utilisateurs de ce type.
     */
    @GetMapping("/count/{type}")
    public ResponseEntity<Long> countByType(@PathVariable String type) {
        Long count = usersService.countUsersByType(type);
        return ResponseEntity.ok(count);
    }

}
