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
     * Le repository du bootcamp
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
     * Méthode pour retourner tous les bootcamps avec les informations choisies
     *
     * @return la liste des bootcamps
     */
    public List<BootcampReduitDTO> findAllBootcampReduit() {
        List<Bootcamp> bootcamps = bootcampRepository.findAll();
        return bootcamps.stream()
                .map(bootcamp -> objectMapper.convertValue(bootcamp, BootcampReduitDTO.class))
                .toList();
    }

    /**
     * @param id l'identifiant recherché
     * @return le bootcamp correspondant
     */
    @Override
    public Bootcamp findById(Long id) {
        return bootcampRepository.findById(id)
                .orElseThrow(() -> new CustomException("Bootcamp", "id", id));
    }

    /**
     * @param newObj le nouvel objet bootcamp
     * @return le bootcamp nouvellement créée
     */
    @Override
    public Bootcamp create(Bootcamp newObj) {
        return bootcampRepository.save(newObj);
    }


    /**
     * Méthode pour mettre à jour un bootcamp selon l'id recherché
     * @param bootcamp l'objet à mettre à jour
     * @return le bootcamp
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
     * Méthode pour supprimer un bootcamp selon l'id recherché
     * @param id l'identifiant recherché
     * @return le bootcamp à supprimer
     */
    @Override
    public Bootcamp deleteById(Long id) {
        Bootcamp bootcamp = findById(id);
        bootcampRepository.deleteById(id);
        return bootcamp;
    }

    /**
     * Méthode pour supprimer tous bootcamps
     */
    @Override
    public void deleteAll() {
        bootcampRepository.deleteAll();
    }

}
