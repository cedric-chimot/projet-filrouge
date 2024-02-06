package fr.equipefilrouge.filrougeSpring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.equipefilrouge.filrougeSpring.dto.SessionReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.SessionFormation;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.SessionFormationRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour une session de formation
 */
@Service
@Transactional
public class SessionFormationServiceImpl implements AllServices<SessionFormation, Long> {

    /**
     * Le repository de la session de formation
     */
    private final SessionFormationRepository sessionFormationRepository;
    private final ObjectMapper objectMapper;

    /**
     * Le constructeur du service
     * @param sessionFormationRepository le repository correspondant
     */
    public SessionFormationServiceImpl(SessionFormationRepository sessionFormationRepository, ObjectMapper objectMapper) {
        this.sessionFormationRepository = sessionFormationRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * @return la liste des sessions de formation
     */
    @Override
    public List<SessionFormation> findAll() {
        return sessionFormationRepository.findAll();
    }

    /**
     * Méthode pour retourner toutes les formations avec les informations choisies
     *
     * @return la liste des formations
     */
    public List<SessionReduitDTO> findAllSessionReduit() {
        List<SessionFormation> sessions = sessionFormationRepository.findAll();
        return sessions.stream()
                .map(sessionFormation -> objectMapper.convertValue(sessionFormation, SessionReduitDTO.class))
                .toList();
    }

    /**
     * @param id l'identifiant recherché
     * @return la session de formation correspondante
     */
    @Override
    public SessionFormation findById(Long id) {
        return sessionFormationRepository.findById(id)
                .orElseThrow(() -> new CustomException("SessionFormation", "id", id));
    }

    /**
     * @param newObj le nouvel objet session de formation
     * @return la session de formation nouvellement créée
     */
    @Override
    public SessionFormation create(SessionFormation newObj) {
        return sessionFormationRepository.save(newObj);
    }

    /**
     * Méthode pour mettre à jour une session de formation selon l'id recherché
     * @param sessionFormation l'objet à mettre à jour
     * @return la session mise à jour
     */
    @Override
    public SessionFormation update(SessionFormation sessionFormation) {
        if (!sessionFormationRepository.existsById(sessionFormation.getId())) {
            throw new CustomException("SessionFormation", "id", sessionFormation.getId());
        }
        sessionFormationRepository.save(sessionFormation);
        return sessionFormation;
    }

    /**
     * Méthode pour supprimer une  session de formation selon l'id recherché
     * @param id l'identifiant recherché
     * @return la session de formation à supprimer
     */
    @Override
    public SessionFormation deleteById(Long id) {
        SessionFormation sessionFormation = findById(id);
        sessionFormationRepository.deleteById(id);
        return sessionFormation;
    }

    /**
     * Méthode pour supprimer toutes les sessions de formation
     */
    @Override
    public void deleteAll() {
        sessionFormationRepository.deleteAll();
    }

}
