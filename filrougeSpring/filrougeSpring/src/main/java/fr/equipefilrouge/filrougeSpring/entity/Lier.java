package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Classe Lier pour gérer les bootcamps avec les formations et lieux concernés
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "lier")
public class Lier {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id", nullable = false)
    private Bootcamp bootcamp;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    /**
     * Constructeur pour la classe de liaison
     * @param bootcamp la session de formation
     * @param formation la formation liée
     * @param lieu le lieu où elle se déroule
     */
    public Lier(Bootcamp bootcamp, Formation formation, Lieu lieu) {
        this.bootcamp = bootcamp;
        this.formation = formation;
        this.lieu = lieu;
    }

    /**
     * Affichage de la table de liaison
     * @return les données de la table
     */
    @Override
    public String toString() {
        return "Lier{" +
                "bootcamp=" + bootcamp +
                ", formation=" + formation +
                ", lieu=" + lieu +
                '}';
    }
}
