import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formations } from '../../models/formations.model';
import { SessionFormations } from '../../models/sessionFormations.model';

@Injectable({
  providedIn: 'root'
})
export class SessionFormationServiceService {
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  // Récupère les formations
  getSessionFormationById(id: number): Observable<SessionFormations> {
    return this.httpClient.get<SessionFormations>(`${this.apiUrl}/sessionFormations/${id}`);
  }

  // Récupère les formations
  getSessionsFormation(): Observable<SessionFormations[]> {
    return this.httpClient.get<SessionFormations[]>(`${this.apiUrl}/sessionFormations/all`);
  }

  
  // Création d'une formation
  createSessionFormation(sessionFormations: SessionFormations): Observable<SessionFormations> {
    return this.httpClient.post<SessionFormations>(`${this.apiUrl}/sessionFormations/create`, sessionFormations);
  }

}
