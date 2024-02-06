package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Classe DomaineThemes pour gérer les domaines et les thèmes associés
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "regrouper")
public class DomaineTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idDomaine", nullable = false)
    private Domaine domaine;

    @ManyToOne
    @JoinColumn(name = "idThemes", nullable = false)
    private Theme theme;

    /**
     * Constructeur du domaine
     * @param domaine, le domaine
     * @param theme, le thème associé au domaine
     */
    public DomaineTheme(Domaine domaine, Theme theme) {
        this.domaine = domaine;
        this.theme = theme;
    }

    /**
     * Méthode pour afficher un domaine et le thème associé
     * @return le domaine et son thème
     */
    @Override
    public String toString() {
        return "DomaineThemes{" +
                "domaine=" + domaine +
                ", themes=" + theme +
                '}';
    }

}

