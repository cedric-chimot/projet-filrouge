import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Bootcamp } from '../../../models/bootcampmodel';
import Formation  from '../../../models/formation.model';
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { FormationService } from '../../../services/formation/formation.service';
import { RouterModule } from '@angular/router';
import { ProductAdminListComponent } from '../../products-page/product-admin-list/product-admin-list.component';


@Component({
  selector: 'app-admin-formation',
  templateUrl: './admin-formation-page.component.html',
  styleUrls: ['./admin-formation-page.component.css'],
  standalone: true,
  imports: [ProductAdminListComponent, RouterModule]
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
  formations: Formation[] = [];
  bootcamps: Bootcamp[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private bootcampService: BootcampService,
    private formationService: FormationService
  ){};

  ngOnInit(): void {
      this.formationService.getFormations()
        .subscribe((formations: Formation[]) => this.formations = formations); 

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
