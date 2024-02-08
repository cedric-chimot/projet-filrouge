import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formations } from '../../models/formations.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormationServiceService {

  private apiUrl = 'http://localhost:8080'

  constructor(private HttpClient : HttpClient) { }

  getFormationById(id: number) Observable<Formations>{
    return this.HttpClient.get<Formations>(`${this.apiUrl}/formation/{id}`);
  }

}
