package fr.equipefilrouge.filrougeSpring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.equipefilrouge.filrougeSpring.dto.StagiaireCompletDTO;
import fr.equipefilrouge.filrougeSpring.dto.StagiaireReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.entity.Stagiaire;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.exceptions.ExistException;
import fr.equipefilrouge.filrougeSpring.repository.StagiaireRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import fr.equipefilrouge.filrougeSpring.services.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour un stagiaire
 */
@Service
@Transactional
public class StagiaireServiceImpl implements AllServices<Stagiaire, Long> {

    /**
     * Le repository du stagiaire
     */
    private final StagiaireRepository stagiaireRepository;

    private final UsersService usersService;

    private final JdbcTemplate jdbcTemplate;

    private final ObjectMapper objectMapper;

    /**
     * Le constructeur du service
     * @param stagiaireRepository le repository correspondant
     */
    public StagiaireServiceImpl(StagiaireRepository stagiaireRepository, UsersService usersService,
                                JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.stagiaireRepository = stagiaireRepository;
        this.usersService = usersService;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * @return tous les stagiaires
     */
    @Override
    public List<Stagiaire> findAll() {
        return stagiaireRepository.findAll();
    }

    public List<Stagiaire> createListe(List<Stagiaire> stagiaires) {

        return stagiaireRepository.saveAll(stagiaires);
    }

    /**
     * Méthode pour retourner tous les stagiaires avec les informations choisies
     * @return la liste des stagiaires
     */
    public List<StagiaireReduitDTO> findAllStagiairesReduit() {
        List<Stagiaire> stagiaires = stagiaireRepository.findAll();
        return stagiaires.stream()
                .map(stagiaire -> objectMapper.convertValue(stagiaire, StagiaireReduitDTO.class))
                .toList();
    }

    /**
     * @param id l'identifiant recherché
     * @return le stagiaire correspondant
     */
    @Override
    public Stagiaire findById(Long id) {
        return stagiaireRepository.findById(id)
                .orElseThrow(() -> new CustomException("Stagiaire", "id", id));
    }

    /**
     * Méthode pour trouver un utilisateur selon l'id et afficher certaines de ses informations
     * @param id l'identifiant du stagiaire
     * @return le stagiaire trouvé et ses infos
     */
    public StagiaireCompletDTO stagiaireById(Long id) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new CustomException("Stagiaire", "id", id));
        return objectMapper.convertValue(stagiaire, StagiaireCompletDTO.class);
    }

    /**
     * Méthode de création d'un nouveau stagiaire
     * @param newObj le nouvel objet stagiaire
     * @return le stagiaire nouvellement créé
     */
    @Override
    public Stagiaire create(Stagiaire newObj) {
        String email = newObj.getEmail();
        Stagiaire newStagiaire;
        if (usersService.isEmailExist(email) && email != null) {
            System.out.println("Email existant");
            throw new ExistException("User", "email", email);
        } else {
            newStagiaire = new Stagiaire(newObj.getNom(), newObj.getPrenom(), newObj.getTelephone(),
                    email, newObj.getPseudo(), usersService.hashMdp(newObj.getMdp()), newObj.getRole());
        }
        return stagiaireRepository.save(newStagiaire);
    }

    /**
     * @param stagiaire l'objet stagiaire à mettre à jour
     */
    @Override
    public Stagiaire update(Stagiaire stagiaire) {
        if (stagiaire.getId() != null) {
            jdbcTemplate.update("UPDATE users SET role = ? WHERE id_user = ?",
                    stagiaire.getRole(), stagiaire.getId());
            stagiaireRepository.save(stagiaire);
        } else {
            throw new CustomException("Stagiaire", "id", "Identifiant inconnu !");
        }
        return stagiaire;
    }

    /**
     * @param id l'identifiant recherché
     * @return le stagiaire à supprimer
     */
    @Override
    public Stagiaire deleteById(Long id) {
        Stagiaire stagiaire = findById(id);
        stagiaireRepository.deleteById(id);
        return stagiaire;
    }

    /**
     * Méthode pour supprimer tous les stagiaires
     */
    @Override
    public void deleteAll() {
        stagiaireRepository.deleteAll();
    }

}
