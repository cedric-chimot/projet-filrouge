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
@Table(name = "lieu")
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idLieu")
    private Long id;

    @Column(name = "raisonSociale", nullable = false)
    private String raisonSociale;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "lieu")
    private List<Lier> liens;

    /**
     * Constructeur du centre de formation
     * @param raisonSociale, le nom du centre
     * @param adresse, l'adresse du centre
     */
    public Lieu(String raisonSociale, String adresse) {
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
    }

    /**
     * Méthode pour afficher un centre de formation
     * @return le centre de formation
     */
    @Override
    public String toString() {
        return "CentreFormation{" +
                "idLieu=" + id +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

}
