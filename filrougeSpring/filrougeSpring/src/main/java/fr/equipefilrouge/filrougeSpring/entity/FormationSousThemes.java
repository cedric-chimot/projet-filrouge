package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Classe FormationSousThemes permettant de lier les formations
 * aux sous-thèmes correspondants
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class FormationSousThemes {

    @Id
    @ManyToOne
    @JoinColumn(name = "idFormation", nullable = false)
    private Formation formations;

    @Id
    @ManyToOne
    @JoinColumn(name = "idSousThemes", nullable = false)
    private SousTheme sousThemes;

    /**
     * Constructeur pour afficher une formation et le sous-thème qui lui correspond
     * @param formations, la formation
     * @param sousThemes, le sous-thème concerné
     */
    public FormationSousThemes(Formation formations, SousTheme sousThemes) {
        this.formations = formations;
        this.sousThemes = sousThemes;
    }

    /**
     * Méthode pour afficher une formation et son sous-thème
     * @return la formation et le sous-thème
     */
    @Override
    public String toString() {
        return "FormationSousThemes{" +
                "formations=" + formations +
                ", sousThemes=" + sousThemes +
                '}';
    }

}

