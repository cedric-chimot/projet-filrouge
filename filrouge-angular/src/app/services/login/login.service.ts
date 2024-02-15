import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../../models/user.model';
import { UserService } from '../users/user.service';



@Injectable({
  providedIn: 'root'
})
export class LoginService{
  private apiUrl = 'http://localhost:8080';
  // Pour suivre l'état de la conneisLoggedxion (true or false)
  private isLogged : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //private reponseBack!: Observable<any>;
  private user! : User;
  
  // Observable permettant de surveiller l'état d'authentification
  isAuthenticated$: Observable<boolean> = this.isLogged.asObservable();
  reponseBack!: Observable<Object> | null;

  constructor(private httpClient: HttpClient, private userService: UserService) {}

  // Fonction pour effectuer la connexion
  login(email: string, mdp: string): Observable<Object>{
    this.reponseBack = null;
    const identite = { email, mdp };
    // Envoi de la requête HTTP POST pour la connexion
    // le Back répond par un objet user donc je définis <User> dans la reception de la réponse
    this.reponseBack =  this.httpClient.post<User>(`${this.apiUrl}/users/login`, identite);
    if(this.reponseBack != null){ // si le réponse n'est pas null alors on créer l'observable user
      this.httpClient.post<User>(`${this.apiUrl}/users/login`, identite).subscribe({
        next: (reponse) => this.user = reponse,// un UserLoginDTO (sans mdp) si le serveur répond un userLoginDTO
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
