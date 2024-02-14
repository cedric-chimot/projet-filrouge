import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { SousThemes } from '../../models/sousThemes.model';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  img =  './../../../assets/images/logo.png';
  isAuthentificated!: boolean;
  user: any[] = [];
  userId!: number | null;

  constructor(private loginService: LoginService) {}
  
  ngOnInit(): void {
      this.loginService.getLogin.subscribe(
        isLoggedIn => {
          if(isLoggedIn){
            this.isAuthentificated = true;
          }else{
            this.isAuthentificated = false;
          }
        }
      );
  }

  deconnexion(): void {  
    if(this.isAuthentificated){
      
      this.loginService.logOut();
      
    }else{
      this.isAuthentificated = false;
    }
    this.userId = null;
    
  }
  /*

  isAuthentificated!: boolean;
  userId!: number | null;
  constructor(private loginService: LoginService) {}
  
  ngOnInit(): void {
    this.deconnexion();
  }

  deconnexion() {
    this.loginService.getLogin.subscribe(
      isLoggedIn => {
        if(isLoggedIn){
          this.loginService.logOut;
        }else{
          this.isAuthentificated = false;
        }
      }
    );
    this.userId = null;
  }
  */
}