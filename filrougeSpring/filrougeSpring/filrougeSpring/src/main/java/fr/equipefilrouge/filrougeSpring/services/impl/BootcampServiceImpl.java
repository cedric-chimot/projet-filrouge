package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.entity.Users;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.BootcampRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    /**
     * Méthode pour afficher tous les bootcamps
     * @return la liste des bootcamps
     */
    public List<Bootcamp> findAll() {
        return bootcampRepository.findAll();
    }

    /**
     * Methode qui retourne le bootcamp en fonction de l'Id. La methode orElseThrow prend en compte une exception
     * qui créée une nouvelle erreur NotFoundException avec le
     * message en paramètre
     * @param id l'id recherché
     * @return le bootcamp complet, ou un message d'erreur si pas trouvé
     */
    public Bootcamp findById(Long id){
        return bootcampRepository.findById(id)
                .orElseThrow(()-> new CustomException("Formation id : ", "id", id));
    }

    /**
     * @param newObj le nouvel objet bootcamp
     * @return le bootcamp nouvellement créé
     */
    @Override
    public Bootcamp create(Bootcamp newObj) {
        return bootcampRepository.save(newObj);
    }

    public Bootcamp createBootcamp(Bootcamp bootcamp, Long idLieu) {
        Lieu lieu = lieuService.findById(idLieu);
        create(new Bootcamp(bootcamp.getDateDebut(), bootcamp.getDateFin(), bootcamp.getStatut(), lieu));
        return bootcamp;
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
     * Methode pour supprimer le bootcamp en fonction de l'id passé en paramètre.
     * @param id l'id recherché
     * @return la bootcamp qui a été supprimée.
     */
    public Bootcamp deleteById(Long id){
        Bootcamp session = findById(id);
        bootcampRepository.deleteById(id);
        return session;
    }

    /**
     * Methode pour effacer l'ensemble des sessions dans la base de données
     */
    public void deleteAll(){
        bootcampRepository.deleteAll();
    }

}