package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.StagiaireCompletDTO;
import fr.equipefilrouge.filrougeSpring.dto.StagiaireReduitDTO;
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
