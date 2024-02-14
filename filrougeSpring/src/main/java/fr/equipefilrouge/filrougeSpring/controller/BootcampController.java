package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.services.impl.BootcampServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour un bootcamp de formation
 */
@RestController
@RequestMapping("/bootcamps")
@CrossOrigin
public class BootcampController {

    /**
     * Appel du service bootcamp
     */
    private final BootcampServiceImpl bootcampService;

    /**
     * Constructeur du controller
     * @param bootcampService le service du bootcamp
     */
    public BootcampController(BootcampServiceImpl bootcampService) {
        this.bootcampService = bootcampService;
    }

    /**
     * Méthode pour créer un bootcamp
     * @param bootcamp le bootcamp à créer
     * @return le bootcamp nouvellement créé
     */
    @PostMapping("/create")
    public Bootcamp createBootcamp(@RequestBody Bootcamp bootcamp) {
        return bootcampService.create(bootcamp);
    }


    /**
     * Méthode pour mettre à jour un bootcamp
     * @param bootcamp la session de formation à mettre à jour
     * @return la session de formation mis à jour
     */
    @PatchMapping("/update")
    public Bootcamp updateBootcamp(@RequestBody Bootcamp bootcamp) {
        return bootcampService.update(bootcamp);
    }

    /**
     * Affiche tous les bootcamps
     * @return la liste des bootcamps
     */
    @GetMapping("/all")
    public List<Bootcamp> getAllBootcamps() {
        return bootcampService.findAll();
    }

    /**
     * Méthode pour rechercher un bootcamp avec son identifiant
     * @param id l'identifiant du bootcamp
     * @return le bootcamp recherchée
     */
    @GetMapping("/{id}")
    public Bootcamp getBootcampById(@PathVariable Long id) {
        return bootcampService.findById(id);
    }

    /**
     * Supprimer un bootcamp selon son id
     * @param id l'identifiant de la session
     * @return la session supprimée
     */
    @DeleteMapping("/delete/{id}")
    public Bootcamp deleteBootcampById(@PathVariable Long id) {
        return bootcampService.deleteById(id);
    }

    /**
     * Supprimer tous les bootcamps
     */
    @DeleteMapping("/delete/all")
    public void deleteAllBootcamps() {
        bootcampService.deleteAll();
    }

}
