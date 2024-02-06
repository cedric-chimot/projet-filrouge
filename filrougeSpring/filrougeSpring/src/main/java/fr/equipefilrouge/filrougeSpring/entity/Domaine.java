package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe Domaine pour gérer les domaines et les thèmes associés
 * ici un seul domaine géré, l'informatique pour
 * correspondre au sujet
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domaine")
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDomaine")
    private Long id;

    @Column(name = "nomDomaine", nullable = false)
    private String domaine;

    @OneToMany(mappedBy = "domaine", fetch = FetchType.EAGER)
    private List<DomaineTheme> domaineThemes = new ArrayList<>();

    /**
     * Constructeur d'un domaine
     * @param domaine, le domaine
     */
    public Domaine(String domaine) {
        this.domaine = domaine;
    }

    /**
     * Méthode pour afficher un domaine
     * @return le domaine
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Domaine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("domaine='" + domaine + "'")
                .toString();
    }
}
