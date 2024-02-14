import { Component, OnInit } from '@angular/core';
import { LoginFormComponent } from '../connexion-page/connexion-page.component';
import { LoginService } from '../../services/login/login.service';
import { Stagiaire } from '../../models/stagiaire.model';
import { StagiaireService } from '../../services/stagiaires/stagiaire.service';

@Component({
  selector: 'app-a-propos',
  standalone: true,
  imports: [LoginFormComponent],
  templateUrl: './a-propos.component.html',
  styleUrl: './a-propos.component.css'
})
export class AProposComponent implements OnInit{
  isAuthentificated: boolean = false;
  user!: Stagiaire;
  constructor(private loginService: LoginService, private stagiaireService: StagiaireService) {}
  
  ngOnInit(): void {
    this.user = this.loginService.getLoginUser;
    this.loginService.getLogin.
    subscribe({
      next: (isLogged) => this.isAuthentificated = isLogged,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }


}
