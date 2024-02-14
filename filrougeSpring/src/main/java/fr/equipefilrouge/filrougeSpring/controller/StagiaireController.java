package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.StagiaireCompletDTO;
import fr.equipefilrouge.filrougeSpring.dto.StagiaireLoginDTO;
import fr.equipefilrouge.filrougeSpring.dto.StagiaireReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.entity.Stagiaire;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.UsersService;
import fr.equipefilrouge.filrougeSpring.services.impl.StagiaireServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller pour un stagiaire
 */
@RestController
@RequestMapping("/stagiaires")
@CrossOrigin
public class StagiaireController {

    /**
     * Appelle le service users
     */
    public final UsersService usersService;
    /**
     * Appelle le service du stagiaire
     */
    public final StagiaireServiceImpl stagiaireServiceImpl;

    /**
     * Constructeur
     * @param stagiaireServiceImpl le service Stagiaire
     */
    public StagiaireController(UsersService usersService, StagiaireServiceImpl stagiaireServiceImpl) {
        this.usersService = usersService;
        this.stagiaireServiceImpl = stagiaireServiceImpl;
    }

    /**
     * Créer un stagiaire
     * @param stagiaire le stagiaire à créer
     * @return le stagiaire nouvellement créé
     */
    @PostMapping("/create")
    public Stagiaire createStagiaire(@Valid @RequestBody Stagiaire stagiaire) {
        return stagiaireServiceImpl.create(stagiaire);
    }

    @PostMapping("/createUsers")
    public List<Stagiaire> listStagiaire(@RequestBody List<Stagiaire> stagiaires) {
        return stagiaireServiceImpl.createListe(stagiaires);
    }
    /**
     * Endpoint pour gérer la connexion
     * @param identifiants les identifiants de connexion
     * @return une réponse ok ou erreur au moment de la validation
     */
    @PostMapping("/login")
    public StagiaireLoginDTO login(@RequestBody Map<String, String> identifiants) {
        // Extraire l'e-mail et le mot de passe à partir du corps de la requête
        String email = identifiants.get("email");
        String mdp = identifiants.get("mdp");

        // Vérifier les informations d'identification
        if (usersService.areIdsValid(email, mdp)) {
            System.out.println("connexion réussie !");
            // Si les informations d'identification sont valides, renvoyer une réponse 200 OK avec un message de succès
            return stagiaireServiceImpl.stagiaireLoginByMail(email);
        } else {
            System.out.println("connexion impossible !");
            // Si les informations d'identification ne sont pas valides, renvoyer une réponse 401 Unauthorized avec un message d'erreur
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects !");
            return null;
        }
    }

    /**
     * Mettre à jour un stagiaire
     * @param stagiaire le stagiaire à mettre à jour
     * @return le stagiaire mis à jour
     */
    @PatchMapping("/update")
    public Stagiaire updateStagiaire(@RequestBody Stagiaire stagiaire) {
        return stagiaireServiceImpl.update(stagiaire);
    }

    /**
     * Afficher tous les stagiaires
     * @return la liste de tous les stagiaires
     */
    @GetMapping("/all")
    public List<StagiaireReduitDTO> getAllStagiaires() {
        return stagiaireServiceImpl.findAllStagiairesReduit();
    }

    /**
     * Afficher un stagiaire selon son id
     * @param id l'identifiant du stagiaire
     * @return le stagiaire recherché
     */
    @GetMapping("/{id}")
    public StagiaireCompletDTO getStagiaireById(@PathVariable Long id) {
        return stagiaireServiceImpl.stagiaireById(id);
    }

    /**
     * Renvoyer le nombre de stagiaires en BDD
     * @return le nombre de stagiaire
     */
    @GetMapping("/nbStagiaires")
    public Long nbStagiaires() {
        return usersService.countUsersByRole(UserRole.STAGIAIRE);
    }
    /**
     * Renvoyer le nombre de candidats en BDD
     * @return le nombre de candidats
     */
    @GetMapping("/nbCandidats")
    public Long nbCandidats() {
        return usersService.countUsersByRole(UserRole.CANDIDAT);
    }

    /**
     * Supprimer un stagiaire selon son id
     * @param id l'identifiant du stagiaire
     * @return le stagiaire supprimé
     */
    @DeleteMapping("/delete/{id}")
    public Stagiaire deleteStagiaireById(@PathVariable Long id) {
        return stagiaireServiceImpl.deleteById(id);
    }

    /**
     * Supprimer tous les stagiaires
     */
    @DeleteMapping("/delete/all")
    public void deleteAllStagiaire() {
        stagiaireServiceImpl.deleteAll();
    }

}
