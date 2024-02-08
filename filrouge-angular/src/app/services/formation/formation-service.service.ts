import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formations } from '../../models/formations.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormationServiceService {

  private apiUrl = 'http://localhost:8080'

  constructor(private httpClient : HttpClient) { }

  getFormationById(id: number): Observable<Formations> {
    return this.httpClient.get<Formations>(`${this.apiUrl}/formations/${id}`);
  }

  // Récupère les formations
  getFormations(): Observable<Formations[]> {
    return this.httpClient.get<Formations[]>(`${this.apiUrl}/formations/all`);
  }

  // Création d'une formation
  createFormation(formations: Formations): Observable<Formations> {
    return this.httpClient.post<Formations>(`${this.apiUrl}/formations/create`, formations);
  }

}
