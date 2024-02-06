import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { StagiaireFormulaireComponent } from "./components/stagiaire-formulaire/stagiaire-formulaire.component";
import { NavBarComponent } from "./commons/navbar/nav-bar.component";
import { FooterComponent } from "./commons/footer/footer.component";
import { AdminNavbarComponent } from "./commons/admin-navbar/admin-navbar.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, StagiaireFormulaireComponent, NavBarComponent, FooterComponent, AdminNavbarComponent]
})
export class AppComponent {
  title = 'filrouge';

  constructor(private router: Router) {}

  isDashboardRoute(): boolean {
    return this.router.url.includes('/dashboard');
  }

}
