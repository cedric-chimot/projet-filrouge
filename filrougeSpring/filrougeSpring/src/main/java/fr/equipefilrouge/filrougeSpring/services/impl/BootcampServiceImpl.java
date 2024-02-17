package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.dto.BootcampDTO;
import fr.equipefilrouge.filrougeSpring.dto.FormationDTO;
import fr.equipefilrouge.filrougeSpring.entity.*;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.*;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BootcampServiceImpl implements AllServices<Bootcamp, Long> {

    /**
     * Le repository Bootcamp
     */
    private final BootcampRepository bootcampRepository;

    private final UsersRepository usersRepository;

    private final FormationRepository formationRepository;

    private final SousThemeRepository sousThemeRepository;

    public BootcampServiceImpl(BootcampRepository bootcampRepository, UsersRepository usersRepository, FormationRepository formationRepository,
                               SousThemeRepository sousThemeRepository) {
        this.bootcampRepository = bootcampRepository;
        this.usersRepository = usersRepository;
        this.formationRepository = formationRepository;
        this.sousThemeRepository = sousThemeRepository;
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

    /**
     * Méthode pour ajouter un utilisateur au bootcamp
     * @param bootcampDTO l'identifiant du bootcamp
     * @param user l'utilisateur à ajouter
     */
    @Transactional
    public void addUserBootcamp(BootcampDTO bootcampDTO, Users user) {
        Long bootcampId = bootcampDTO.getId();
        Bootcamp bootcamp = findById(bootcampId);

        if (bootcamp != null) {
            // Vérifier si l'utilisateur existe déjà en base de données
            Users existingUser = usersRepository.findByEmail(user.getEmail());

            if (existingUser != null) {
                // Vérifier si l'utilisateur n'est pas déjà associé à un autre bootcamp
                if (existingUser.getBootcamps() == null || !existingUser.getBootcamps().contains(bootcamp)) {
                    // L'utilisateur existe déjà, on l'ajoute au bootcamp
                    bootcamp.getUsers().add(existingUser);
                    existingUser.getBootcamps().add(bootcamp);

                    // Mettre à jour le bootcamp
                    update(bootcamp);
                } else {
                    throw new CustomException("User", "User with email ", user.getEmail() + " is already associated with another bootcamp.");
                }
            } else {
                throw new CustomException("User", "User with email ", user.getEmail() + " does not exist.");
            }
        } else {
            throw new CustomException("Bootcamp", "id", bootcampId);
        }
    }

    /**
     * Méthode pour lier des formations à des bootcamps
     * @param bootcampDTO le bootcamp à lier
     * @param formations la liste de formations à ajouter
     */
    @Transactional
    public void addFormationsToBootcamp(BootcampDTO bootcampDTO, List<Formation> formations) {
        Long bootcampId = bootcampDTO.getId();
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> new CustomException("Bootcamp not found with id: ", "id", bootcampId));

        for (Formation formation : formations) {
            // Assurez-vous que le sous-thème existe
            SousTheme sousTheme = formation.getSousThemeId();
            if (sousTheme != null) {
                SousTheme existingSousTheme = sousThemeRepository.findById(sousTheme.getId())
                        .orElseThrow(() -> new CustomException("SousTheme not found with id: ", "id", sousTheme.getId()));
                formation.setSousThemeId(existingSousTheme);
            }

            // Rechercher la formation dans la base de données par ID
            Optional<Formation> existingFormationOptional = formationRepository.findById(formation.getId());

            if (existingFormationOptional.isPresent()) {
                // La formation existe déjà dans la base de données, associez-la au bootcamp
                Formation existingFormation = existingFormationOptional.get();
                bootcamp.getFormations().add(existingFormation);
                existingFormation.getBootcamps().add(bootcamp);
            } else {
                // La formation n'existe pas dans la base de données, ajoutez-la et associez-la au bootcamp
                bootcamp.getFormations().add(formation);
                formation.getBootcamps().add(bootcamp);
            }
        }

        // Mettez à jour le bootcamp
        bootcampRepository.save(bootcamp);
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