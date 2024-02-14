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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name ="theme_id")
    private Theme theme;

    @ManyToMany(mappedBy = "sousTheme")
    private List<Formation> formations = new ArrayList<>();

    /**
     * Constructeur du sous-thème
     * @param sousTheme le sous-thème
     * @param theme le thème lié
     */
    public SousTheme(SousThemesEnum sousTheme, Theme theme) {
        this.sousTheme = sousTheme;
        this.theme = theme;
    }

    /**
     * Méthode pour afficher un sous-thème
     * @return le sous-thème
     */
    @Override
    public String toString() {
        return "SousTheme{" +
                "id=" + id +
                ", sousTheme=" + sousTheme +
                ", theme=" + theme +
                '}';
    }
}
