import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/users/user.service';


@Component({
    selector: 'app-admin-page',
    standalone: true,
    templateUrl: './admin-page.component.html',
    styleUrl: './admin-page.component.css',
    imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterModule]
})
export class AdminPageComponent {
    nbUsers: number = 0; 
    nbCandidats: number = 0;

    constructor(private userService: UserService) {}
    ngOnInit(): void {
        this.userService.getNbUsers()
        .subscribe({
            next: (nbUsers: number) => {
                this.nbUsers = nbUsers;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de users', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.userService.getNbCandidats()
        .subscribe({
            next: (nbCandidats: number) => {
                this.nbCandidats = nbCandidats;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de users', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });
    }

}
