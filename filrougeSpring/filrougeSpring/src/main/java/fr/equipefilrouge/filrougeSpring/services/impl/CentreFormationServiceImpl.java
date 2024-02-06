package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.CentreFormation;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.CentreFormationRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour un centre de formation
 */
@Service
@Transactional
public class CentreFormationServiceImpl implements AllServices<CentreFormation, Long> {

    /**
     * Le repository du centre de formation
     */
    private final CentreFormationRepository centreFormationRepository;

    /**
     * Le constructeur du service
     * @param centreFormationRepository le repository correspondant
     */
    public CentreFormationServiceImpl(CentreFormationRepository centreFormationRepository) {
        this.centreFormationRepository = centreFormationRepository;
    }

    /**
     * @return tous les objets existants
     */
    @Override
    public List<CentreFormation> findAll() {
        return centreFormationRepository.findAll();
    }

    /**
     * Méthode pour trouver un objet par son identifiant
     * @param id l'identifiant recherché
     * @return l'objet recherché
     */
    @Override
    public CentreFormation findById(Long id) {
        return centreFormationRepository.findById(id)
                .orElseThrow(() -> new CustomException("CentreFormation", "id", id));
    }

    /**
     * Méthode pour créer un nouvel objet
     * @param newObj le nouvel objet
     * @return l'objet nouvellement créé
     */
    @Override
    public CentreFormation create(CentreFormation newObj) {
        return centreFormationRepository.save(newObj);
    }

    /**
     * @param centreFormation l'objet centre de formation à mettre à jour
     */
    @Override
    public CentreFormation update(CentreFormation centreFormation) {
        if (!centreFormationRepository.existsById(centreFormation.getId())) {
            throw new CustomException("CentreFormation", "id", centreFormation.getId());
        }
        centreFormationRepository.save(centreFormation);
        return centreFormation;
    }

    /**
     * Méthode pour supprimer un centre de formation selon l'id recherché
     * @param id l'identifiant recherché
     * @return l'objet à supprimer
     */
    @Override
    public CentreFormation deleteById(Long id) {
        CentreFormation centreFormation = findById(id);
        centreFormationRepository.deleteById(id);
        return centreFormation;
    }

    /**
     * Méthode pour supprimer tous les centres de formation
     */
    @Override
    public void deleteAll() {
        centreFormationRepository.deleteAll();
    }
}
