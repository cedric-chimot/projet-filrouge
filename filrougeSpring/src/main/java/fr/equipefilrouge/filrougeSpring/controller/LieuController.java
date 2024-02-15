package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.services.impl.LieuServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour un centre de formation
 */
@RestController
@RequestMapping("/lieux")
@CrossOrigin
public class LieuController {

    /**
     * Appelle le service du lieu
     */
    private final LieuServiceImpl lieuService;

    /**
     * Constructeur du controller lieu
     * @param lieuService, le service centre de formation
     */
    public LieuController(LieuServiceImpl lieuService) {
        this.lieuService = lieuService;
    }

    /**
     * Affiche tous les lieux
     * @return la liste des lieux
     */
    @GetMapping("/all")
    public List<Lieu> getAllLieux() {
        return lieuService.findAll();
    }

    /**
     * Méthode pour rechercher un lieu avec son identifiant
     * @param id l'identifiant du lieu
     * @return le lieu recherchée
     */
    @GetMapping("/{id}")
    public Lieu getLieuById(@PathVariable Long id) {
        return lieuService.findById(id);
    }

    /**
     * Méthode pour créer un nouveau lieu
     * @param lieu, le lieu à créer
     * @return le lieu nouvellement créé
     */
    @PostMapping("/create")
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.create(lieu);
    }

    /**
     * Méthode pour créer une liste de lieux
     * @param lieux les lieux à créer
     * @return la liste des lieux créée
     */
    @PostMapping("/createLieux")
    public List<Lieu> createLieux(@RequestBody List<Lieu> lieux) {
        return lieuService.createListe(lieux);
    }

    /**
     * Méthode pour mettre à jour un lieu
     * @param lieu le lieu à mettre à jour
     * @return le lieu mis à jour
     */
    @PatchMapping("/update")
    public Lieu updateLieu(@RequestBody Lieu lieu) {
        return lieuService.update(lieu);
    }

    /**
     * Supprimer un lieu selon son id
     * @param id l'identifiant du lieu
     * @return le lieu supprimé
     */
    @DeleteMapping("/delete/{id}")
    public Lieu deleteLieuById(@PathVariable Long id) {
        return lieuService.deleteById(id);
    }

    /**
     * Supprimer tous les lieux
     */
    @DeleteMapping("/delete/all")
    public void deleteAllLieux() {
        lieuService.deleteAll();
    }


}
