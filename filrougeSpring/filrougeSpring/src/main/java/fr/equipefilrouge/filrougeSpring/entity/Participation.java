package fr.equipefilrouge.filrougeSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

/**
 * Classe Participation pour gérer les participations aux sessions de formation
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "participationFormation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionFormation session;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", nullable = false)
    private Stagiaire user;

    @ManyToOne
    @JoinColumn(name = "formateur_id", nullable = false)
    private Formateur animateur;

    @Column(name = "noteFormateur")
    private int noteFormateur;

    @Column(name = "noteSession")
    private int noteSession;

    @Column(name = "recommandation")
    private Boolean recommandation;

    /**
     * Constructeur d'une participation à une formation
     * @param session, la session de formation
     * @param user, le stagiaire inscrit à la session
     * @param formateur, le formateur qui va animer la formation
     * @param noteFormateur, la note du formateur par les stagiaires
     * @param noteSession, la note de la session de formation par les stagiaires
     * @param recommandation, renseigne si le stagiaire recommande la formation
     */
    public Participation(SessionFormation session, Stagiaire user, Formateur formateur, int noteFormateur,
                         int noteSession, Boolean recommandation) {
        this.session = session;
        this.user = user;
        this.animateur = formateur;
        this.noteFormateur = noteFormateur;
        this.noteSession = noteSession;
        this.recommandation = recommandation;
    }

    /**
     * Méthode pour afficher une participation à une formation ainsi que le stagiaire
     * le formateur et la formation concernée
     * @return une participation à une formation
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Participation.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("session=" + session)
                .add("user=" + user)
                .add("animateur=" + animateur)
                .add("noteFormateur=" + noteFormateur)
                .add("noteSession=" + noteSession)
                .add("recommandation=" + recommandation)
                .toString();
    }
}
