package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe SessionFormation pour gérer une session de formation et toutes ses données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessionFormation")
public class SessionFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession")
    private Long id;

    @Column(name = "date_debut", nullable = false)
    //@Temporal : Pour spécifier si un champ de type "Date" doit être mappé en tant que date
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(name = "statut", nullable = false)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "centre_formation_id", nullable = true)
    private CentreFormation centreFormation;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private List<Participation> participations = new ArrayList<>();

    /**
     * Constructeur pour une session de formation
     * @param dateDebut, date de début de la session
     * @param dateFin, date de fin de la session
     * @param statut, le statut
     * @param formation, la formation concernée
     * @param centreFormation, le centre de formation lié s'il y en a un
     */
    public SessionFormation(Date dateDebut, Date dateFin, String statut, Formation formation, CentreFormation centreFormation) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.formation = formation;
        this.centreFormation = centreFormation;
    }

    /**
     * Méthode pour afficher une session de formation
     * @return la session de formation
     */
    @Override
    public String toString() {
        return "SessionFormation{" +
                "idSession=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut='" + statut + '\'' +
                ", formation=" + formation +
                ", centreFormation=" + centreFormation +
                '}';
    }

}

