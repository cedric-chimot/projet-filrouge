package fr.equipefilrouge.filrougeSpring.entity;

import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe Formateurs pour gérer les formateurs et leurs données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("F")
public class Formateur extends Users{

    @Column(name = "noteMoyenne")
    private double noteMoyenne;

    @OneToMany(mappedBy = "formateur", fetch = FetchType.EAGER)
    private List<ExperienceFormateur> experienceFormateurs = new ArrayList<>();

    @OneToMany(mappedBy = "animateur", fetch = FetchType.EAGER)
    private List<Participation> participations = new ArrayList<>();

    /**
     * Constructeur d'un formateur
     * @param nom, le nom du formateur
     * @param prenom, le prénom du formateur
     * @param telephone, le téléphone du formateur
     * @param email, l'email du formateur
     * @param pseudo, le pseudo du formateur
     * @param mdp, le mot de passe du formateur
     * @param role, le role
     * @param noteMoyenne, la note moyenne du formateur
     */
    public Formateur(String nom, String prenom, String telephone, String email, String pseudo,
                     String mdp, UserRole role, double noteMoyenne) {
        super(nom, prenom, telephone, email, pseudo, mdp, role);
        this.noteMoyenne = noteMoyenne;
    }

    /**
     * Méthode pour afficher un formateur
     * @return le formateur
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Formateur.class.getSimpleName() + "[", "]")
                .add("Users" + super.toString())
                .add("noteMoyenne=" + noteMoyenne)
                .toString();
    }
}
