import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NavBarComponent } from "../../commons/navbar/nav-bar.component";
import { FooterComponent } from "../../commons/footer/footer.component";
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'app-connexion-page',
  standalone: true, 
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, NavBarComponent, FooterComponent, CommonModule, RouterModule],
  templateUrl: './connexion-page.component.html',
  styleUrl: './connexion-page.component.css'
})

export class LoginFormComponent {
  // Définition du formulaire réactif avec les champs email et mdp
  loginForm: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    mdp: ['', Validators.required],
  })

  // Indique si le formulaire a été soumis
  submitted: boolean = false;  

  constructor(
    private formBuilder: FormBuilder, private loginService: LoginService, private router: Router
  ) {};

  // Fonction appelée lors de la soumission du formulaire
  onSubmit() {
    this.submitted = true;

    // Vérifie si le formulaire est valide
    if(this.loginForm.valid) {
      // Récupération des valeurs des champs email et mdp
      const email = this.form['email'].value;
      const password = this.form['mdp'].value;

      // Appel du service de connexion
      this.loginService.login(email, password)
        .subscribe({
          next: (_) => {
            // Affiche une alerte en cas de connexion réussie
            alert("Connexion réussie !");
            // met la variable observable a true pour confirmé la connexion et pouvoir vérifié
            this.loginService.setLogin(true);
            this.loginService
            // Redirection sur l'accueil en cas de succès
            this.router.navigate(['/home']);
          },
          error: (error) => {
            // Affiche une alerte en cas d'erreur lors de la connexion
            console.error("Erreur lors de la connexion :", error);
            alert("Identifiants erronés ou utilisateur inconnu !");
          },
          complete: () => {
            // Log de la complétion du processus de connexion
            console.log("Connexion complète");
          }
        });
        this.submitted = false;
        this.loginForm.reset();
    }
  }
  
  // Getter pour accéder aux contrôles du formulaire plus facilement dans le template
  get form() {
    return this.loginForm.controls;
  }

}
