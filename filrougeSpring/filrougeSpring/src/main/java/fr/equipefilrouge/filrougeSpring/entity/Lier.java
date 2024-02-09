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

    public Lier(Bootcamp bootcamp, Formation formation, Lieu lieu) {
        this.bootcamp = bootcamp;
        this.formation = formation;
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Lier{" +
                "bootcamp=" + bootcamp +
                ", formation=" + formation +
                ", lieu=" + lieu +
                '}';
    }
}
