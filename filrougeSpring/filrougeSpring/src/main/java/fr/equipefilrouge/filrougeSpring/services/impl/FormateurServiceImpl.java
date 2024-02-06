package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Formateur;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.exceptions.ExistException;
import fr.equipefilrouge.filrougeSpring.repository.FormateurRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import fr.equipefilrouge.filrougeSpring.services.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour un formateur
 */
@Service
@Transactional
public class FormateurServiceImpl implements AllServices<Formateur, Long> {

    /**
     * Le repository du formateur
     */
    private final FormateurRepository formateurRepository;

    /**
     * Appel du service Users
     */
    private final UsersService usersService;

    /**
     * API pour gérer les opérations sur la BDD
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Le constructeur du service
     * @param formateurRepository le repository correspondant
     */
    public FormateurServiceImpl(FormateurRepository formateurRepository, UsersService usersService, JdbcTemplate jdbcTemplate) {
        this.formateurRepository = formateurRepository;
        this.usersService = usersService;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return tous les formateurs
     */
    @Override
    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }

    /**
     * @param id l'identifiant recherché
     * @return le formateur correspondant
     */
    @Override
    public Formateur findById(Long id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new CustomException("Stagiaire", "id", id));
    }

    /**
     * @param newObj le nouvel objet formateur
     * @return le formateur nouvellement créé
     */
    @Override
    public Formateur create(Formateur newObj) {
        String email = newObj.getEmail();
        Formateur newFormateur;
        if (usersService.isEmailExist(email) && email != null) {
            throw new ExistException("User", "email", email);
        } else {
            newFormateur = new Formateur(newObj.getNom(), newObj.getPrenom(), newObj.getTelephone(), email,
                    newObj.getPseudo(), usersService.hashMdp(newObj.getMdp()), newObj.getRole(), newObj.getNoteMoyenne());
        }
        return formateurRepository.save(newFormateur);
    }

    /**
     * Méthode pour mettre à jour un formateur
     * @param formateur l'objet formateur à mettre à jour
     * @return le formateur mis à jour
     */
    @Override
    public Formateur update(Formateur formateur) {
        if (formateur.getId() != null) {
            jdbcTemplate.update("UPDATE users SET note_moyenne = ? WHERE id_user = ?",
                    formateur.getNoteMoyenne(), formateur.getId());
            formateurRepository.save(formateur);
        } else {
            throw new CustomException("Formateur", "id", "Identifiant inconnu !");
        }
        return formateur;
    }

    /**
     * @param id l'identifiant recherché
     * @return le formateur à supprimer
     */
    @Override
    public Formateur deleteById(Long id) {
        Formateur formateur = findById(id);
        formateurRepository.deleteById(id);
        return formateur;
    }

    /**
     * Méthode pour supprimer tous les formateurs
     */
    @Override
    public void deleteAll() {
        formateurRepository.deleteAll();
    }

}
