package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.services.impl.BootcampServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour une session de formation
 */
@RestController
@RequestMapping("/bootcamps")
@CrossOrigin
public class BootcampController {

    /**
     * Appel du service session de formation
     */
    private final BootcampServiceImpl bootcampService;

    /**
     * Constructeur du controller
     * @param bootcampService le service de la session de formation
     */
    public BootcampController(BootcampServiceImpl bootcampService) {
        this.bootcampService = bootcampService;
    }

    /**
     * Méthode pour créer une session de formation
     * @param bootcamp la session de formation à créer
     * @return la session de formation nouvellement créée
     */
    @PostMapping("/create")
    public Bootcamp createBootcamp(@RequestBody Bootcamp bootcamp) {
        return bootcampService.create(bootcamp);
    }


    /**
     * Méthode pour mettre à jour une session de formation
     * @param bootcamp la session de formation à mettre à jour
     * @return la session de formation mis à jour
     */
    @PatchMapping("/update")
    public Bootcamp updateBootcamp(@RequestBody Bootcamp bootcamp) {
        return bootcampService.update(bootcamp);
    }

    /**
     * Affiche toutes les sessions de formations
     * @return la liste des sessions formations
     */
    @GetMapping("/all")
    public List<Bootcamp> getAllBootcamps() {
        return bootcampService.findAll();
    }

    /**
     * Méthode pour rechercher une session avec son identifiant
     * @param id l'identifiant de la session
     * @return la session recherchée
     */
    @GetMapping("/{id}")
    public Bootcamp getBootcampById(@PathVariable Long id) {
        return bootcampService.findById(id);
    }

    /**
     * Supprimer une session de formation selon son id
     * @param id l'identifiant de la session
     * @return la session supprimée
     */
    @DeleteMapping("/delete/{id}")
    public Bootcamp deleteBootcampById(@PathVariable Long id) {
        return bootcampService.deleteById(id);
    }

    /**
     * Supprimer toutes les sessions de formation
     */
    @DeleteMapping("/delete/all")
    public void deleteAllBootcamps() {
        bootcampService.deleteAll();
    }

}
