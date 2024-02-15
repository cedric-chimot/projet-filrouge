package fr.equipefilrouge.filrougeSpring.entity;

import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Classe Stagiaires pour gérer les stagiaires et leurs données
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("S")
public class Stagiaire extends Users {

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Participation> participations = new ArrayList<>();

    /**
     * Constructeur d'un stagiaire
     * @param nom, le nom du stagiaire
     * @param prenom, le prénom du stagiaire
     * @param telephone, le téléphone du stagiaire
     * @param email, l'email du stagiaire
     * @param pseudo, le pseudo du stagiaire
     * @param mdp, le mot de passe du stagiaire
     * @param role, le role
     */
    public Stagiaire(String nom, String prenom, String telephone, String email, String pseudo,
                     String mdp, UserRole role) {
        super(nom,prenom,telephone,email,pseudo,mdp,role);
    }

    /**
     * Méthode pour afficher un stagiaire
     * @return le stagiaire
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Stagiaire.class.getSimpleName() + "[", "]")
                .add("Users" + super.toString())
                .toString();
    }


}
