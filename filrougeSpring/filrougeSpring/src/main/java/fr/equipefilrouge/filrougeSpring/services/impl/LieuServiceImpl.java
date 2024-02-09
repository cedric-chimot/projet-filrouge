package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Lieu;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.LieuRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour un centre de formation
 */
@Service
@Transactional
public class LieuServiceImpl implements AllServices<Lieu, Long> {

    /**
     * Le repository du centre de formation
     */
    private final LieuRepository lieuRepository;

    /**
     * Le constructeur du service
     * @param lieuRepository le repository correspondant
     */
    public LieuServiceImpl(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    /**
     * @return tous les objets existants
     */
    @Override
    public List<Lieu> findAll() {
        return lieuRepository.findAll();
    }

    /**
     * Méthode pour trouver un objet par son identifiant
     * @param id l'identifiant recherché
     * @return l'objet recherché
     */
    @Override
    public Lieu findById(Long id) {
        return lieuRepository.findById(id)
                .orElseThrow(() -> new CustomException("CentreFormation", "id", id));
    }

    /**
     * Méthode pour créer un nouvel objet
     * @param newObj le nouvel objet
     * @return l'objet nouvellement créé
     */
    @Override
    public Lieu create(Lieu newObj) {
        return lieuRepository.save(newObj);
    }

    /**
     * @param lieu l'objet centre de formation à mettre à jour
     */
    @Override
    public Lieu update(Lieu lieu) {
        if (!lieuRepository.existsById(lieu.getId())) {
            throw new CustomException("CentreFormation", "id", lieu.getId());
        }
        lieuRepository.save(lieu);
        return lieu;
    }

    /**
     * Méthode pour supprimer un centre de formation selon l'id recherché
     * @param id l'identifiant recherché
     * @return l'objet à supprimer
     */
    @Override
    public Lieu deleteById(Long id) {
        Lieu lieu = findById(id);
        lieuRepository.deleteById(id);
        return lieu;
    }

    /**
     * Méthode pour supprimer tous les centres de formation
     */
    @Override
    public void deleteAll() {
        lieuRepository.deleteAll();
    }
}
