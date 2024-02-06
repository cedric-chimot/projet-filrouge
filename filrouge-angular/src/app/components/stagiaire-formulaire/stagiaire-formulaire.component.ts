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
  stagiairesForm: FormGroup = this.formBuider.group({
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

  submitted: boolean = false;

  stagiaires: any[] = [];
  stagiaire!: Stagiaires;

  constructor(private formBuider: FormBuilder, private stagiaireService: StagiaireServiceService) {};

  addStagiaire(): void {
    this.stagiaires.push(this.stagiairesForm.value);
  
    this.stagiaireService.createStagiaire(this.stagiairesForm.value)
      .subscribe({
        next: (stagiairesForm) => {
          this.stagiaire = stagiairesForm;
          alert("Stagiaire créé avec succès !");
        },
        error: (error) => {
          if (error.status === 409) {
            alert("Erreur lors de la création du stagiaire : Email déjà utilisé.");
          } else {
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
    this.submitted = true;

    if(this.stagiairesForm.invalid) {
      return false;
    } else {
      this.addStagiaire();
      this.submitted = false;
      this.stagiairesForm.reset();
      return true;
    }
    
  }

  get form() {
    return this.stagiairesForm.controls;
  }
 
}
