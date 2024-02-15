import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { StagiaireService } from '../../services/stagiaires/stagiaire.service';
import { FormateurServiceService } from '../../services/users/formateurs/formateur-service.service';


@Component({
    selector: 'app-admin-page',
    standalone: true,
    templateUrl: './admin-page.component.html',
    styleUrl: './admin-page.component.css',
    imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterModule]
})
export class AdminPageComponent {
    nbStagiaires: number = 0; 
    nbCandidats: number = 0;
    nbFormateurs: number = 0;

    constructor(private stagiaireService: StagiaireService, private formateurService: FormateurServiceService) {}

    ngOnInit(): void {
        this.stagiaireService.getNbStagiaires()
        .subscribe({
            next: (nbStagiaires: number) => {
                this.nbStagiaires = nbStagiaires;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.stagiaireService.getNbCandidats()
        .subscribe({
            next: (nbCandidats: number) => {
                this.nbCandidats = nbCandidats;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.formateurService.getNbFormateurs()
        .subscribe({
            next: (nbFormateurs: number) => {
                this.nbFormateurs = nbFormateurs;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

    }

}
