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
  user!: Stagiaire | null;
  constructor(private loginService: LoginService, private stagiaireService: StagiaireService) {}
  
  ngOnInit(): void {
      this.loginService.getLogin.subscribe(
        isLoggedIn => { if(isLoggedIn){ 
                            this.isAuthentificated = true;
                            
    // param ,je vais t'appliquer le code suivant, voici le code 
  
          }else{
            this.isAuthentificated = false;
            
          }
        }
      );  //this.user= this.stagiaireService.getStagiaires;
             // this.stagiaireService.getStagiaires.subscribe(
            //   () => {
            //     this.user = user;
            //   }
            //   );
      //this.user = null;
  }


}
