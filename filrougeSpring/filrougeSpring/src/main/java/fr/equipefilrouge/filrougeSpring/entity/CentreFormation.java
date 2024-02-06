package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Classe CentreFormation pour gérer le centre de formation
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "centreFormation")
public class CentreFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idCentre")
    private Long id;

    @Column(name = "nomCentre", nullable = false)
    private String nom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "centreFormation")
    private List<SessionFormation> sessionsFormation;

    /**
     * Constructeur du centre de formation
     * @param nom, le nom du centre
     * @param adresse, l'adresse du centre
     */
    public CentreFormation(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    /**
     * Méthode pour afficher un centre de formation
     * @return le centre de formation
     */
    @Override
    public String toString() {
        return "CentreFormation{" +
                "idCentre=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

}
