import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Bootcamp } from '../../models/bootcampmodel';
import { Formations } from '../../models/formations.model';
import { BootcampServiceService } from '../../services/bootcamp/bootcamp-service.service';
import { FormationServiceService } from '../../services/formation/formation-service.service';

@Component({
  selector: 'app-admin-formation',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminFormationComponent implements OnInit {

  bootcampForm: FormGroup = this.formBuilder.group({
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
  bootcamps: Bootcamp[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private bootcampService: BootcampServiceService,
    private formationService: FormationServiceService
  ){};

  ngOnInit(): void {
      this.formationService.getFormations()
        .subscribe((formations: Formations[]) => this.formations = formations); 

      this.bootcampService.getBootcamp()
        .subscribe((BootcampServiceService: Bootcamp[]) => this.bootcamps = BootcampServiceService); 
  
      };

  onSubmit(): void {
    this.submitted = true;
    if (this.bootcampForm.invalid) {
      return;
    }
    // Soumission du formulaire de session de formation
    console.log(this.bootcampForm.value);
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
    return this.bootcampForm.controls;
  }
}
