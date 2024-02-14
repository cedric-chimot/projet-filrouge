package fr.equipefilrouge.filrougeSpring.services.impl;

import fr.equipefilrouge.filrougeSpring.entity.SousTheme;
import fr.equipefilrouge.filrougeSpring.exceptions.CustomException;
import fr.equipefilrouge.filrougeSpring.repository.SousThemeRepository;
import fr.equipefilrouge.filrougeSpring.services.AllServices;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SousThemeServiceImpl implements AllServices<SousTheme, Long> {

    /**
     * Le repository du sous-thème
     */
    private final SousThemeRepository sousThemeRepository;

    /**
     * Le constructeur
     * @param sousThemeRepository le repository correspondant
     */
    public SousThemeServiceImpl(SousThemeRepository sousThemeRepository) {
        this.sousThemeRepository = sousThemeRepository;
    }

    /**
     * @return tous les sous-thèmes
     */
    @Override
    public List<SousTheme> findAll() {
        return sousThemeRepository.findAll();
    }

    /**
     * @param id l'identifiant recherché
     * @return le sous-thème recherché
     */
    @Override
    public SousTheme findById(Long id) {
        return sousThemeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sous-thème", "id", id));
    }

    /**
     * @param newObj le nouvel objet sous-thème
     * @return le sous-thème nouvellement créé
     */
    @Override
    public SousTheme create(SousTheme newObj) {
        return sousThemeRepository.save(newObj);
    }

    /**
     * Méthode pour créer une liste de sous-thèmes
     * @param sousThemes la liste de sous-thèmes à créer
     * @return la liste de sous-thèmes nouvellement créée
     */
    public List<SousTheme> createListe(List<SousTheme> sousThemes) {
        return sousThemeRepository.saveAll(sousThemes);
    }

    /**
     * @param sousTheme l'objet sous-thème à mettre à jour
     * @return le sous-thème mis à jour
     */
    @Override
    public SousTheme update(SousTheme sousTheme) {
       if (!sousThemeRepository.existsById(sousTheme.getId())) {
           throw new CustomException("Sous-thème", "id", sousTheme.getId());
       }
       sousThemeRepository.save(sousTheme);
       return sousTheme;
    }

    /**
     * Méthode pour supprimer un sous-thème selon l'id recherché
     * @param id l'identifiant recherché
     * @return l'objet à supprimer
     */
    @Override
    public SousTheme deleteById(Long id) {
        SousTheme sousTheme = findById(id);
        sousThemeRepository.deleteById(id);
        return sousTheme;
    }

    /**
     * Méthode pour supprimer tous les sous-thèmes
     */
    @Override
    public void deleteAll() {
        sousThemeRepository.deleteAll();
    }

}
