package fr.equipefilrouge.filrougeSpring.entity;

import fr.equipefilrouge.filrougeSpring.enums.SousThemesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe SousThemes pour gérer les sous-thèmes des différentes formations
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sous_themes")
public class SousTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSousTheme")
    private Long id;

    @Column(name = "nomSousTheme")
    @Enumerated(EnumType.STRING)
    private SousThemesEnum sousTheme;

    @OneToMany(mappedBy = "sousTheme", fetch = FetchType.EAGER)
    private List<Theme> themes = new ArrayList<>();

    @OneToMany(mappedBy = "sousTheme", fetch = FetchType.EAGER)
    private List<ExperienceFormateur> experiencesSousThemes = new ArrayList<>();

    /**
     * Constructeur des sous-thèmes
     * @param sousTheme, le sous-thème
     */
    public SousTheme(SousThemesEnum sousTheme) {
        this.sousTheme = sousTheme;
    }

    /**
     * Méthode pour afficher un sous-thème
     * @return le sous-thème
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", SousTheme.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("sousTheme='" + sousTheme + "'")
                .toString();
    }
}
