import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Stagiaires } from '../../models/stagiaires.model';

@Injectable({
  providedIn: 'root'
})
export class StagiaireServiceService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  // Récupère tous les stagiaires
  getStagiaires(): Observable<Stagiaires[]>{
    return this.httpClient.get<Stagiaires[]>(`${this.apiUrl}/stagiaires/all`);
  }

  // Récupère le nombre de stagiaires
  getNbStagiaires(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/stagiaires/nbStagiaires`);
  }
  
  // Récupère le nombre de candidats
  getNbCandidats(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/stagiaires/nbCandidats`);
  }
  
  // Création d'un stagiaire
  createStagiaire(stagiaire: Stagiaires): Observable<Stagiaires>{
    return this.httpClient.post<Stagiaires>(`${this.apiUrl}/stagiaires/create`, stagiaire);
  }

}
