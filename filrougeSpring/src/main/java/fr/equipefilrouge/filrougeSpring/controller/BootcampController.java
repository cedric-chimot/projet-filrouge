package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.BootcampReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.services.impl.BootcampServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.LieuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

/**
 * Controller des bootcamps
 */
@RestController
@RequestMapping("/bootcamps")
@CrossOrigin
public class BootcampController {

    /**
     * Le service des bootcamps
     */
    private final BootcampServiceImpl bootcampService;

    /**
     * Le service des lieux
     */
    private final LieuServiceImpl lieuService;

    /**
     * Le constructeur
     * @param bootcampService le service bootcamp
     * @param lieuService le service lieu
     */
    public BootcampController(BootcampServiceImpl bootcampService, LieuServiceImpl lieuService) {
        this.bootcampService = bootcampService;
        this.lieuService = lieuService;
    }

    /**
     * Méthode pour récupérer tous les bootcamps
     * @return la liste de tous les bootcamps
     */
    @GetMapping("/all")
    public List<Bootcamp> findAll(){
        return bootcampService.findAll();
    }

    /**
     * Méthode pour trouver un bootcamp avec son id
     * @param id l'id recherché
     * @return le bootcamp recherché
     */
    @GetMapping("/find/{id}")
    public Bootcamp findById(@PathVariable long id){
        return bootcampService.findById(id);
    }

    /**
     * Méthode pour créer un bootcamp
     * @param bootcamp le bootcamp à créer
     * @param idLieu l'id lieu à récupérer
     * @return le bootcamp nouvellement créé
     */
    @PostMapping("/createBootcamp")
    public Bootcamp createBootcamp(@Validated @RequestBody Bootcamp bootcamp, Long idLieu){
        return bootcampService.createBootcamp(bootcamp, idLieu);
    }

    /**
     * Crée une liste de bootcamps avec les informations fournies.
     *
     * @param bootcamps  Liste des bootcamps à créer.
     * @param idLieux    Liste des identifiants des lieux associés aux bootcamps.
     * @return Liste des bootcamps créés sous forme de DTO réduit.
     */
    @PostMapping("/createBootcamps")
    public List<BootcampReduitDTO> createBootcamps(
            @Validated @RequestBody List<Bootcamp> bootcamps,
            @RequestParam List<Long> idLieux) {

        // Liste pour stocker les bootcamps créés sous forme de DTO réduit
        List<BootcampReduitDTO> createdBootcamps = new ArrayList<>();

        // Parcours de la liste des bootcamps à créer
        for (int i = 0; i < bootcamps.size(); i++) {
            // Récupération du bootcamp et de l'identifiant du lieu associé
            Bootcamp bootcamp = bootcamps.get(i);
            Long idLieu = idLieux.get(i);

            // Récupération du lieu associé à partir de l'identifiant
            Lieu lieu = lieuService.findById(idLieu);

            // Création du bootcamp à l'aide du service
            Bootcamp createdBootcamp = bootcampService.create(
                    new Bootcamp(bootcamp.getDateDebut(), bootcamp.getDateFin(),
                            bootcamp.getStatut(), lieu));

            // Création du DTO réduit pour le bootcamp créé
            BootcampReduitDTO bootcampDTO = new BootcampReduitDTO();
            bootcampDTO.setDateDebut(createdBootcamp.getDateDebut());
            bootcampDTO.setDateFin(createdBootcamp.getDateFin());
            bootcampDTO.setStatut(createdBootcamp.getStatut());

            // Ajout du DTO réduit à la liste des bootcamps créés
            createdBootcamps.add(bootcampDTO);
        }
        // Retourne la liste des bootcamps créés sous forme de DTO réduit
        return createdBootcamps;
    }

    /**
     * Ajouter un user à un bootcamp
     * @param bootcampId l'id du bootcamp
     * @param user l'utilisateur à ajouter
     * @return Réponse ok si l'utilisateur a été ajouté
     */
    @PostMapping("/bootcamps/{bootcampId}/addUser")
    public ResponseEntity<String> addUserToBootcamp(@PathVariable long bootcampId, @RequestBody Users user) {
        bootcampService.addUserBootcamp(bootcampId, user);
        return new ResponseEntity<>("User ajouté au bootcamp", HttpStatus.OK);
    }

    /**
     * Méthode pour mettre à jour un bootcamp
     * @param bootcamp le bootcamp mis à jour
     */
    @PatchMapping("/update")
    public void update(@RequestBody Bootcamp bootcamp){
        bootcampService.update(bootcamp);
    }

    /**
     * Méthode pour supprimer un bootcamp selon son id
     * @param id l'id recherché
     */
    @DeleteMapping("/delete/{id}")
    public Bootcamp deleteById(@PathVariable long id){
        return bootcampService.deleteById(id);
    }

    /**
     * Méthode pour supprimer tous les bootcamps
     */
    @DeleteMapping("/delete")
    public void deleteAll(){
        bootcampService.deleteAll();
    }

}