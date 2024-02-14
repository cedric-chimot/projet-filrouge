package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.SousTheme;
import fr.equipefilrouge.filrougeSpring.services.impl.SousThemeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sous_themes")
@CrossOrigin
public class SousThemeController {

    /**
     * Le service des sous-thèmes
     */
    public final SousThemeServiceImpl sousThemeService;

    /**
     * Le constructeur
     * @param sousThemeService le service correspondant
     */
    public SousThemeController(SousThemeServiceImpl sousThemeService) {
        this.sousThemeService = sousThemeService;
    }

    /**
     * Méthode pour créer une nouvelle sousTheme
     * @param sousTheme La sousTheme à créer
     * @return l sousTheme nouvellement créée
     */
    @PostMapping("/create")
    public SousTheme createsousTheme(@RequestBody SousTheme sousTheme) {
        return sousThemeService.create(sousTheme);
    }

    /**
     * Création d'une liste de sousThemes
     * @param sousThemes la liste des sousThemes à créer
     * @return la liste des sousThemes nouvellement créée
     */
    @PostMapping("/createSousThemes")
    public List<SousTheme> listSousThemes(@RequestBody List<SousTheme> sousThemes) {
        return sousThemeService.createListe(sousThemes);
    }

    /**
     * Méthode pour mettre à jour une sousTheme
     * @param sousTheme la sousTheme à mettre à jour
     * @return la sousTheme mis à jour
     */
    @PatchMapping("/update")
    public SousTheme updateSousTheme(@RequestBody SousTheme sousTheme) {
        return sousThemeService.update(sousTheme);
    }

    /**
     * Affiche toutes les sousThemes
     * @return la liste des sousThemes
     */
    @GetMapping("/all")
    public List<SousTheme> getAllSousThemes() {
        return sousThemeService.findAll();
    }
    /**
     * Méthode pour rechercher une session avec son identifiant
     * @param id l'identifiant de la session
     * @return la session recherchée
     */
    @GetMapping("/{id}")
    public SousTheme getSousThemeById(@PathVariable Long id) {
        return sousThemeService.findById(id);
    }

    /**
     * Supprimer une sousTheme selon son id
     * @param id l'identifiant de la sousTheme
     * @return la sousTheme supprimée
     */
    @DeleteMapping("/delete/{id}")
    public SousTheme deleteSousThemeById(@PathVariable Long id) {
        return sousThemeService.deleteById(id);
    }

    /**
     * Supprimer toutes les sousThemes
     */
    @DeleteMapping("/delete/all")
    public void deleteAllSousTheme() {
        sousThemeService.deleteAll();
    }
    
}
