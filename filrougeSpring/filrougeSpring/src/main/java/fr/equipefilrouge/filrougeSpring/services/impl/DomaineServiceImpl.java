package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.Domaine;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.DomaineRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour un formateur
 */
@Service
@Transactional
public class DomaineServiceImpl implements AllServices<Domaine, Long> {

    /**
     * Le repository du domaine
     */
    private final DomaineRepository domaineRepository;

    /**
     * Le constructeur du service
     * @param domaineRepository le repository correspondant
     */
    public DomaineServiceImpl(DomaineRepository domaineRepository) {
        this.domaineRepository = domaineRepository;
    }

    /**
     * @return tous les domaines
     */
    @Override
    public List<Domaine> findAll() {
        return domaineRepository.findAll();
    }

    /**
     * @param id l'identifiant recherché
     * @return le domaine correspondant
     */
    @Override
    public Domaine findById(Long id) {
        return domaineRepository.findById(id)
                .orElseThrow(() -> new CustomException("Domaine", "id", id));
    }

    /**
     * @param newObj le nouvel objet domaine
     * @return le domaine nouvellement créé
     */
    @Override
    public Domaine create(Domaine newObj) {
        return domaineRepository.save(newObj);
    }

    /**
     * @param domaine l'objet à mettre à jour
     * @return le domaine mis à jour
     */
    @Override
    public Domaine update(Domaine domaine) {
        if (!domaineRepository.existsById(domaine.getId())) {
            throw new CustomException("Formateur", "id", domaine.getId());
        }
        domaineRepository.save(domaine);
        return domaine;
    }

    /**
     * Méthode pour supprimer un domaine selon l'id recherché
     * @param id l'identifiant recherché
     * @return le domaine à supprimer
     */
    @Override
    public Domaine deleteById(Long id) {
        Domaine domaine = findById(id);
        domaineRepository.deleteById(id);
        return domaine;
    }

    /**
     * Méthode pour supprimer touts les domaines
     */
    @Override
    public void deleteAll() {
        domaineRepository.deleteAll();
    }
}
