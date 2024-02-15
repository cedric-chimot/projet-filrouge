import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { Stagiaire } from '../../models/stagiaire.model';
import { StagiaireService } from '../stagiaires/stagiaire.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService{
  private apiUrl = 'http://localhost:8080';
  // Pour suivre l'état de la conneisLoggedxion (true or false)
  private isLogged : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //private reponseBack!: Observable<any>;
  private user! : Stagiaire;
  

  // Observable permettant de surveiller l'état d'authentification
  isAuthenticated$: Observable<boolean> = this.isLogged.asObservable();
  reponseBack!: Observable<Object>;


  constructor(private httpClient: HttpClient, private stagiaireService: StagiaireService) {}

  // Fonction pour effectuer la connexion
  login(email: string, mdp: string): Observable<Object>{
    const identite = { email, mdp };
    // Envoi de la requête HTTP POST pour la connexion
    // le Back répond par un objet stagiaire donc je définis <Stagiaire> dans la reception de la réponse
    this.reponseBack =  this.httpClient.post<Stagiaire>(`${this.apiUrl}/stagiaires/login`, identite);
    if(this.reponseBack != null){ // si le réponse n'est pas null alors on créer l'observable user
      this.httpClient.post<Stagiaire>(`${this.apiUrl}/stagiaires/login`, identite).subscribe({
        next: (reponse) => this.user = reponse,// un StagiaireLoginDTO (sans mdp) si le serveur répond un stagiaireLoginDTO
        error: (err) => console.error('Erreur au chargement', err)
      });
      
    }
    return this.reponseBack;
  }
  // Fonction pour mettre à jour l'état d'authentification
  setLogin(value: boolean): void {
    this.isLogged.next(value);
  }
  //getter pour recupérer le boolean de login pour savoir si nous sommes connecter ou non
  get getLogin(){
    return this.isLogged.asObservable();
  }
  get getLoginUser(){
    return this.user;
  }
  // Fonction de déconnexion
  logOut(): void {
    // Met à jour l'état d'authentification à false lors de la déconnexion
    alert("Déconnecté !");
    this.setLogin(false);
  }
  
  //Todo : créer le unsubscribe pour les observables, pour eviter les fuites de données 
  
  
  
}
