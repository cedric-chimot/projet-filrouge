import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formateurs } from '../../../models/formateurs.model';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class FormateurServiceService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  getUserFormateurs(): Observable<Formateurs[]> {
    return this.httpClient.get<Formateurs[]>(`${this.apiUrl}/users/all/formateurs`);
  }

}
