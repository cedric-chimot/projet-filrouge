import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { Stagiaire } from '../../models/stagiaire.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService{
  private apiUrl = 'http://localhost:8080';
  private user! : Stagiaire | null;
  // Pour suivre l'état de la conneisLoggedxion (true or false)
  private isLogged : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  // Observable permettant de surveiller l'état d'authentification
  isAuthenticated$: Observable<boolean> = this.isLogged.asObservable();

 
  constructor(private httpClient: HttpClient) {}

  // Fonction pour effectuer la connexion
  login(email: string, mdp: string): Observable<any>{
    
    const identite = { email, mdp };
    // Envoi de la requête HTTP POST pour la connexion
    // responseType: 'text' indique que la réponse doit être traitée comme du texte
    //ToDo : confidition venant du back = reussit appelé fonction setLogin
    //old : return this.httpClient.post(`${this.apiUrl}/stagiaires/login`, identite);
    return this.;
  }
  // Fonction pour mettre à jour l'état d'authentification
  setLogin(value: boolean): void {
    this.isLogged.next(value);
  }
  get getLogin(){
    return this.isLogged.asObservable();
  }
  // Fonction de déconnexion
  logOut(): void {
    // Met à jour l'état d'authentification à false lors de la déconnexion
    alert("Déconnecté !");
    this.setLogin(false);
    this.user = null;
  }
  
  
}
