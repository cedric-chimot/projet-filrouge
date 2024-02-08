import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionFormations } from '../../models/sessionFormations.model';
import { Formations } from '../../models/formations.model';
import { SessionFormationServiceService } from '../../services/sessionFormation/session-formation-service.service';
import { FormationServiceService } from '../../services/formation/formation-service.service';

@Component({
  selector: 'app-admin-formation',
  templateUrl: './admin-formation.component.html',
  styleUrls: ['./admin-formation.component.css']
})
export class AdminFormationComponent implements OnInit {

  sessionFormationForm: FormGroup = this.formBuilder.group({
    dateDebut: ['', Validators.required],
    dateFin: ['', Validators.required],
    centreFormation: ['', Validators.required],
    formation: ['', Validators.required],
    statut: ['', Validators.required]
  });
  formationForm: FormGroup = this.formBuilder.group({
    nom: ['', Validators.required],
    prix: ['', Validators.required],
    description: ['', Validators.required]
  });


  submitted = false;
  formations: Formations[] = [];
  sessionsFormation: SessionFormations[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private sessionFormationService: SessionFormationServiceService,
    private formationService: FormationServiceService
  ){};

  ngOnInit(): void {
      this.formationService.getFormations()
        .subscribe((formations: Formations[]) => this.formations = formations); 

      this.sessionFormationService.getSessionsFormation()
        .subscribe((SessionFormationServiceService: SessionFormations[]) => this.sessionsFormation = SessionFormationServiceService); 
  
      };

  onSubmit(): void {
    this.submitted = true;
    if (this.sessionFormationForm.invalid) {
      return;
    }
    // Soumission du formulaire de session de formation
    console.log(this.sessionFormationForm.value);
  }

  onSubmitFormation(): void {
    this.submitted = true;
    if (this.formationForm.invalid) {
      return;
    }
  }

  get formationFormControls(): any {
    return this.formationForm.controls;
  }

  get sessionFormationFormControls(): any {
    return this.sessionFormationForm.controls;
  }
}
