package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.BootcampRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BootcampServiceImpl implements AllServices<Bootcamp, Long> {
    /**
     * Le repository Bootcamp
     */
    private final BootcampRepository bootcampRepository;

    private final LieuServiceImpl lieuService;

    public BootcampServiceImpl(BootcampRepository bootcampRepository, LieuServiceImpl lieuService) {
        this.bootcampRepository = bootcampRepository;
        this.lieuService = lieuService;
    }

    public List<Bootcamp> findAll() {
        return bootcampRepository.findAll();
    }

    /**
     * Methode qui retourne le bootcamp en fonction de l'Id. La methode orElseThrow prend en compte une fonction lambda
     * cette fonction anonyme "()" créée un nouvel erreur NotFoundException avec le
     * message en paramètre
     * @param id
     * @return la session complete, ou un message d'erreur si pas trouvé
     */
    public Bootcamp findById(Long id){
        return bootcampRepository.findById(id)
                .orElseThrow(()-> new CustomException("Formation id : ", "id", id));
    }

    /**
     * @param newObj le nouvel objet bootcamp
     * @return le bootcamp créé
     */
    @Override
    public Bootcamp create(Bootcamp newObj) {
        Lieu lieu = newObj.getLieu();

        if (lieu != null && lieu.getId() != null) {
            // Vérifier si le lieu existe
            Lieu existingLieu = lieuService.findById(lieu.getId());

            if (existingLieu != null) {
                // Le lieu existe, associer le Bootcamp existant avec le Lieu
                newObj.setLieu(existingLieu);
            } else {
                // Le lieu n'existe pas, créer un nouveau lieu et l'associer avec le Bootcamp
                Lieu createdLieu = lieuService.create(lieu);
                newObj.setLieu(createdLieu);
            }
        }

        // Enregistrer le Bootcamp (et potentiellement le Lieu associé)
        return bootcampRepository.save(newObj);
    }



    public List<Bootcamp> createBootcamps(List<Bootcamp> bootcamps) {
        List<Bootcamp> saveBootcamps = new ArrayList<>();
        for (Bootcamp bootcamp : bootcamps) {
            Lieu lieu = lieuService.findById(bootcamp.getLieu().getId());
            if (lieu != null) {
                saveBootcamps.add(new Bootcamp(bootcamp.getDateDebut(), bootcamp.getDateFin(), bootcamp.getStatut(), lieu));
            } else {
                throw new CustomException("Bootcamp", "bootcamp", bootcamp);
            }
        }
        return saveBootcamps;
    }

    /**
     * Méthode pour ajouter un utilisateur au bootcamp
     * @param bootcampId l'identifiant du bootcamp
     * @param user l'utilisateur à ajouter
     */
    public void addUserBootcamp(Long bootcampId, Users user) {
        Bootcamp bootcamp = findById(bootcampId);

        if (bootcamp != null) {
            bootcamp.getUsers().add(user);
            user.getBootcamps().add(bootcamp);
            bootcampRepository.save(bootcamp);
        } else {
            throw new CustomException("Bootcamp", "id", bootcampId);
        }
    }

    /**
     * Methode de mise à jour d'un bootcamp dans la base de données
     * @param bootcamp le bootcamp à modifier
     * @return le bootcamp modifié
     */
    public Bootcamp update(Bootcamp bootcamp){
        return bootcampRepository.save(bootcamp);
    }

    /**
     * Methode pour effacer l'ensemble des sessions dans la base de données
     */
    public void deleteAll(){
        bootcampRepository.deleteAll();
    }

    /**
     * Methode pour supprimer la session en fonction de l'id passé en paramètre.
     * @param id
     * @return la session qui a été supprimée.
     */
    public Bootcamp deleteById(Long id){
        Bootcamp session = findById(id);
        bootcampRepository.deleteById(id);
        return session;
    }

}