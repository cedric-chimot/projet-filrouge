package fr.equipefilrouge.filrougeSpring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.equipefilrouge.filrougeSpring.dto.BootcampReduitDTO;
import fr.equipefilrouge.filrougeSpring.entity.Bootcamp;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.BootcampRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour implémenter les méthodes du CRUD pour une session de formation
 */
@Service
@Transactional
public class BootcampServiceImpl implements AllServices<Bootcamp, Long> {

    /**
     * Le repository de la session de formation
     */
    private final BootcampRepository bootcampRepository;

    private final ObjectMapper objectMapper;

    /**
     * Le constructeur du service
     * @param bootcampRepository le repository correspondant
     */
    public BootcampServiceImpl(BootcampRepository bootcampRepository, ObjectMapper objectMapper) {
        this.bootcampRepository = bootcampRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * @return la liste des sessions de formation
     */
    @Override
    public List<Bootcamp> findAll() {
        return bootcampRepository.findAll();
    }

    /**
     * Méthode pour retourner toutes les formations avec les informations choisies
     *
     * @return la liste des formations
     */
    public List<BootcampReduitDTO> findAllBootcampReduit() {
        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        return bootcamps.stream()
                .map(bootcamp -> objectMapper.convertValue(bootcamp, BootcampReduitDTO.class))
                .toList();
    }

    /**
     * @param id l'identifiant recherché
     * @return la session de formation correspondante
     */
    @Override
    public Bootcamp findById(Long id) {
        return bootcampRepository.findById(id)
                .orElseThrow(() -> new CustomException("SessionFormation", "id", id));
    }

    /**
     * @param newObj le nouvel objet session de formation
     * @return la session de formation nouvellement créée
     */
    @Override
    public Bootcamp create(Bootcamp newObj) {
        return bootcampRepository.save(newObj);
    }


    /**
     * Méthode pour mettre à jour une session de formation selon l'id recherché
     * @param bootcamp l'objet à mettre à jour
     * @return la session mise à jour
     */
    @Override
    public Bootcamp update(Bootcamp bootcamp) {
        if (!bootcampRepository.existsById(bootcamp.getId())) {
            throw new CustomException("Bootcamp", "id", bootcamp.getId());
        }
        bootcampRepository.save(bootcamp);
        return bootcamp;
    }

    /**
     * Méthode pour supprimer une  session de formation selon l'id recherché
     * @param id l'identifiant recherché
     * @return la session de formation à supprimer
     */
    @Override
    public Bootcamp deleteById(Long id) {
        Bootcamp bootcamp = findById(id);
        bootcampRepository.deleteById(id);
        return bootcamp;
    }

    /**
     * Méthode pour supprimer toutes les sessions de formation
     */
    @Override
    public void deleteAll() {
        bootcampRepository.deleteAll();
    }

}
