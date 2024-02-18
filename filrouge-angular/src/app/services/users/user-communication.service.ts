import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserCommunicationService {
  private loggedInUserSubject: BehaviorSubject<User | undefined> = new BehaviorSubject<User | undefined>(undefined);

  setLoggedInUser(user: User | undefined): void {
    this.loggedInUserSubject.next(user);
  }

  getLoggedInUser(): Observable<User | undefined> {
    return this.loggedInUserSubject.asObservable();
  }
}
