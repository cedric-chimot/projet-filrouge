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

    @OneToMany(mappedBy = "formation", fetch = FetchType.EAGER)
    private List<SessionFormation> sessionsFormation = new ArrayList<>();

    @OneToMany(mappedBy = "formations", fetch = FetchType.EAGER)
    private List<FormationSousThemes> formationSousThemes = new ArrayList<>();

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
