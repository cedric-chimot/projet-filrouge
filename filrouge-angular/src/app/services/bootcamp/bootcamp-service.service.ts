import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formations } from '../../models/formations.model';
import { Bootcamp } from '../../models/bootcampmodel';

@Injectable({
  providedIn: 'root'
})
export class BootcampServiceService {
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  // Récupère les bootcamp
  getBootcampById(id: number): Observable<Bootcamp> {
    return this.httpClient.get<Bootcamp>(`${this.apiUrl}/bootcamps/${id}`);
  }

  // Récupère les bootcamp
  getBootcamp(): Observable<Bootcamp[]> {
    return this.httpClient.get<Bootcamp[]>(`${this.apiUrl}/bootcamps/all`);
  }

  // Création d'un bootcamp
  createBootcamp(bootcamp: Bootcamp): Observable<Bootcamp> {
    return this.httpClient.post<Bootcamp>(`${this.apiUrl}/bootcamps/create`, bootcamp);
  }

}
