import { Component, OnInit } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { Stagiaire } from '../../models/stagiaire.model';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  providers: [Stagiaire],
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  img =  './../../../assets/images/logo.png';
  isAuthentificated!: boolean;
  user!: Stagiaire;

  constructor(private loginService: LoginService,  private stagiaire: Stagiaire) {}
  
  //On s'inscrit à l'observable getLogin pour connaitre l'état de connexion en temps réel (Boolean)
  ngOnInit(): void {
    //On observe si la connexion change
    this.loginService.getLogin.
    subscribe({
      next: (isLogged) => this.isAuthentificated = isLogged,
      error: (err) => console.error('Erreur au chargement', err)
    });
    //On enregistre le User connecter dans la variable du composant 
    //(Getter d'un observable ouvert) dans login.service lors de la connexion
    //si l'utilisateur est connecté
    //if(this.isAuthentificated){
      this.user = this.loginService.getLoginUser; 
   // }
  }

  deconnexion(): void {  
    if(this.isAuthentificated){
      this.loginService.logOut();
    }else{
      this.isAuthentificated = false;
    }
    //ToDo : appeller les fonction de "destroy"
  }
}