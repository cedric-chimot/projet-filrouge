<<<<<<<< HEAD:filrougeSpring/src/main/java/fr/equipefilrouge/filrougeSpring/entity/Formation.java
package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Formations pour gérer toutes les formations et leurs données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idFormation")
    private Long id;

    @Column(name = "nomFormation", nullable = false)
    private String nom;

    @Column(name = "prix", nullable = false)
    private int prix;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "img", nullable = true)
    private String img;

    @OneToMany(mappedBy = "formation")
    private List<Lier> liens;

    /**
     * Constructeur pour une formation
     * @param nom, l'intitulé de la formation
     * @param prix, le coût de la formation
     * @param description, description détaillée de la formation
     */
    public Formation(String nom, int prix, String description) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    /**
     * Méthode pour afficher une formation
     * @return la formation
     */
    @Override
    public String toString() {
        return "Formations{" +
                "idFormation=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                '}';
    }

}
========
package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Classe Formations pour gérer toutes les formations et leurs données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idFormation")
    private Long id;

    @Column(name = "nomFormation", nullable = false)
    private String nom;

    @Column(name = "prix", nullable = false)
    private int prix;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sous_theme_id")
    private SousTheme sousTheme;

    @ManyToMany
    @JoinTable(
            name = "lier",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "bootcamp_id")
    )
    private List<Bootcamp> bootcamps;

    /**
     *
     * @param nom, le nom de la formation
     * @param prix, le prix
     * @param description, sa description
     * @param sousTheme, le sous-thème lié
     */
    public Formation(String nom, int prix, String description, SousTheme sousTheme) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.sousTheme = sousTheme;
    }

    /**
     * Méthode pour afficher une formation
     * @return la formation
     */
    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", sousTheme=" + sousTheme +
                '}';
    }
}
>>>>>>>> cedric:filrougeSpring/filrougeSpring/filrougeSpring/src/main/java/fr/equipefilrouge/filrougeSpring/entity/Formation.java
