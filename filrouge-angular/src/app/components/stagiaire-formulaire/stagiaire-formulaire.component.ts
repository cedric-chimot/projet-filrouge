import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StagiaireServiceService } from '../../services/stagiaires/stagiaire-service.service';
import { Stagiaires } from '../../models/stagiaires.model';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NavBarComponent } from "../../commons/navbar/nav-bar.component";
import { FooterComponent } from "../../commons/footer/footer.component";


@Component({
    selector: 'app-stagiaire-formulaire',
    standalone: true,
    templateUrl: './stagiaire-formulaire.component.html',
    styleUrls: ['./stagiaire-formulaire.component.css'],
    imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, NavBarComponent, FooterComponent]
})

export class StagiaireFormulaireComponent {
  // Validateurs pour les champs du formulaire
  stagiairesForm: FormGroup = this.formBuilder.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
    telephone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    role: ['', Validators.required],
    pseudo: ['', Validators.required],
    mdp: ['', [
      Validators.required,
      Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/),
      Validators.minLength(8)
    ]],
  })

  // Soumission du formulaire "false" de base
  submitted: boolean = false;

  // Tableau de stagiaires
  stagiaires: any[] = [];
  // Un seul stagiaire
  stagiaire!: Stagiaires;

  // Constructeur du formulaire
  constructor(private formBuilder: FormBuilder, private stagiaireService: StagiaireServiceService) {}; 

  // Méthode pour ajouter un stagiaire
  addStagiaire(): void {
    // On push les valeurs des inputs du formulaire
    this.stagiaires.push(this.stagiairesForm.value);
  
    // Appel du service pour la création
    this.stagiaireService.createStagiaire(this.stagiairesForm.value)
      .subscribe({
        next: (stagiairesForm) => {
          // Si c'est un succès le stagiaire est enregistré
          this.stagiaire = stagiairesForm;
          alert("Stagiaire créé avec succès !");
        },
        error: (error) => {
          if (error.status === 409) {
            // Envoi une erreur "conflict" si l'email enregistré existe déjà en BDD
            alert("Erreur lors de la création du stagiaire : Email déjà utilisé.");
          } else {
            // Envoi une erreur si la création échoue
            alert("Erreur lors de la création du stagiaire");
            console.log(error);
          }
        },
        complete: () => {
          console.log("Inscription complete");
        }
      });
  }
  
  onSubmit() {
    // A la soumission on passe le submit à "true"
    this.submitted = true;

    if(this.stagiairesForm.invalid) {
      // Si le formulaire est invalide(ex: champs vide), retourne "false"
      return false;
    } else {
      // Sinon on ajoute le stagiaire et le "submit" repasse à false
      this.addStagiaire();
      this.submitted = false;
      // Reset du formulaire
      this.stagiairesForm.reset();
      return true;
    }
    
  }

  // Pour contrôler les validators
  get form() {
    return this.stagiairesForm.controls;
  }
 
}
