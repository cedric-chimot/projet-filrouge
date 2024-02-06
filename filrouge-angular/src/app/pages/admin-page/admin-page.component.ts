import { Component } from '@angular/core';
import { AdminNavbarComponent } from "../../commons/admin-navbar/admin-navbar.component";
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { StagiaireServiceService } from '../../services/stagiaires/stagiaire-service.service';


@Component({
    selector: 'app-admin-page',
    standalone: true,
    templateUrl: './admin-page.component.html',
    styleUrl: './admin-page.component.css',
    imports: [AdminNavbarComponent, MatToolbarModule, MatIconModule, MatButtonModule, RouterModule]
})
export class AdminPageComponent {
    // Variables pour compter les stagiaires et candidats
    nbStagiaires: number = 0; 
    nbCandidats: number = 0;

    // Constructeur
    constructor(private stagiaireService: StagiaireServiceService) {}
  
    ngOnInit(): void {
    // on récupère depuis le service la méthode pour trouver les stagiaires
      this.stagiaireService.getNbStagiaires()
        .subscribe({
            next: (nbStagiaires: number) => {
                // on va afficher le nombre de stagiaires enregistrés en BDD
                this.nbStagiaires = nbStagiaires;
            },
            error: (error) => {
                // message d'erreur en cas d'échec
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

    // on récupère depuis le service la méthode pour trouver les candidats
        this.stagiaireService.getNbCandidats()
        .subscribe({
            next: (nbCandidats: number) => {
                // on va afficher le nombre de candidats enregistrés en BDD
                this.nbCandidats = nbCandidats;
            },
            error: (error) => {
                // message d'erreur en cas d'échec
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });
    }

}
