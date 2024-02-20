import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { Bootcamp } from '../../../models/bootcamp.model';
import { Lieu } from '../../../models/lieu.model';
import Formation from '../../../models/formation.model';
import { CommonModule } from '@angular/common';
import { User } from '../../../models/user.model';
import { LoginService } from '../../../services/login/login.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-inscription-form-page',
  templateUrl: './inscription-form-page.component.html',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  styleUrls: ['./inscription-form-page.component.css']
})
export class InscriptionFormPageComponent implements OnInit {

  user: User | undefined;
  bootcampId: number | null = null;
  bootcamp: Bootcamp = {
    id: 0,
    dateDebut: '',
    dateFin: '',
    statut: '',
    lieu: new Lieu(),
    formation: [],
    userCount: 0
  };
  inscriptionMessage: string = '';
  showFormForBootcampId: number | null = null;
  formation: Formation | null = null;

  constructor(
    private bootcampService: BootcampService,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    // Récupérer le paramètre d'URL pour obtenir l'ID du bootcamp
    this.route.paramMap.subscribe(params => {
      const bootcampIdString = params.get('bootcampId');
      this.showFormForBootcampId = bootcampIdString ? parseInt(bootcampIdString, 10) : null; // Convertir en nombre ou laisser null
      // Charger les détails du bootcamp à partir du service en fonction de l'ID
      this.loadBootcampDetails();
    });
  
    // Récupérer l'utilisateur connecté
    this.loginService.isAuthenticated$.subscribe(isAuthenticated => {
      if (isAuthenticated) {
        this.user = this.loginService.getLoginUser;
        console.log('User:', this.user);
        console.log('User ID:', this.user?.id);
      }
    });
  }  

  loadBootcampDetails() {
    // Utiliser le service pour récupérer les détails du bootcamp
    if (this.showFormForBootcampId !== null) {
      this.bootcampService.getBootcampById(this.showFormForBootcampId)
        .subscribe({
          next: (result) => {
            this.bootcamp = result; // Mettre à jour le bootcamp avec les détails récupérés
            
            // Charger les formations liées au bootcamp
            this.loadFormations();
          },
          error: (error) => {
            console.error('Erreur lors de la récupération des détails du bootcamp', error);
          },
          complete: () => {
            console.log("Récupération complète");
          }
        });
    }
  }

  loadFormations() {
    if (this.bootcamp && this.bootcamp.id) {
      this.bootcampService.getFormationsInBootcamp(this.bootcamp.id)
        .subscribe({
          next: (formations) => {
            // Mettre à jour la propriété formation avec les données récupérées
            if (formations && formations.length > 0) {
              this.formation = formations[0]; 
            }
          },
          error: (error) => {
            console.error('Erreur lors de la récupération des formations du bootcamp', error);
          },
          complete: () => {
            console.log("Récupération des formations complète");
          }
        });
    }
  }

  // Méthode pour obtenir l'ID de l'utilisateur connecté
  getLoggedInUserId(): number | undefined {
    return this.user?.id;
  }

  // Dans le composant où on appelle addUserToBootcamp
  submitInscription() {
    console.log('showFormForBootcampId:', this.showFormForBootcampId);
    console.log('user:', this.user);
    console.log('user.id:', this.user?.id);

  // S'assurer que this.showFormForBootcampId est correctement défini
  if (this.showFormForBootcampId !== null && this.user !== undefined) {
    // Vérifier si user.id est défini avant de l'envoyer au backend
    if (this.user.id !== undefined) {
      // Envoyer les données au backend en utilisant le service HTTP Angular
      this.bootcampService.addUserToBootcamp(this.showFormForBootcampId, this.user.id)
        .subscribe({
          next: (_) => {
            // Succès de l'inscription
            this.showFormForBootcampId = null;
            this.inscriptionMessage = 'Votre inscription a été validée. Vous serez recontacté prochainement pour des tests de positionnement.';

            // Log de la complétion du processus d'inscription
            console.log("Inscription complète");

            // Afficher une alerte
            window.alert(this.inscriptionMessage);

            // Redirection vers la page dédiée après l'inscription
            this.router.navigate(['/products']);
          },
          error: (error) => {
            // Gérer d'autres types d'erreurs ici si nécessaire
            console.error('Erreur lors de la soumission de l\'inscription', error);

            // Vérifier si l'erreur est due à une erreur de parsing
            if (error instanceof HttpErrorResponse && error.status === 200) {
              console.log('Réponse du serveur:', error.error.text); // Afficher le texte de la réponse du serveur
            }

            // Gérez l'erreur en conséquence, par exemple, affichez un message d'erreur convivial
            window.alert('Erreur lors de la soumission de l\'inscription. Veuillez réessayer.');
          }
        });
      }
    }
  }
}

