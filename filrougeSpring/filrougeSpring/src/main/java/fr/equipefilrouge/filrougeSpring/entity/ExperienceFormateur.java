package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

/**
 * Classe ExperienceFormateur pour gérer les formateurs et
 * leur nombre d'année d'expérience sur un sous-thème donné
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experienceFormateur")
public class ExperienceFormateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "sous_theme_id")
    private SousTheme sousTheme;

    @Column(name = "annee_experience")
    private int annee;

    /**
     * Constructeur pour afficher l'expérience d'un formateur dans un sous-thèmes donné
     * @param formateur, le formateur concerné
     * @param sousTheme, le sous-thème
     * @param annee, le nombre d'année d'expérience dans le sous-thème
     */
    public ExperienceFormateur(Formateur formateur, SousTheme sousTheme, int annee) {
        this.formateur = formateur;
        this.sousTheme = sousTheme;
        this.annee = annee;
    }

    /**
     * Méthode pour afficher l'expérience d'un formateur dans différents sous-thèmes
     * @return l'expérience du formateur
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", ExperienceFormateur.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("formateur=" + formateur)
                .add("sousTheme=" + sousTheme)
                .add("annee=" + annee)
                .toString();
    }
}
