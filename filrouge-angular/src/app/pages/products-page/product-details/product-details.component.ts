import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ActivatedRoute, RouterModule } from '@angular/router'; // Importez ActivatedRoute
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { FormationService } from '../../../services/formation/formation.service';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, RouterModule, CommonModule],
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  @Input()
  product!: any;
  bootcamps: any[] = [];
  formationId!: number; // Nouvelle propriété pour stocker l'ID de la formation
  disabled: boolean = false;

  constructor(
    private formationService: FormationService,
    private bootcampService: BootcampService,
    private route: ActivatedRoute // Injectez ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Obtenez l'ID de la formation à partir de l'URL
    const formationIdString = this.route.snapshot.paramMap.get('id');
    this.formationId = formationIdString ? +formationIdString : 0;
    
    // Utilisez l'ID de la formation pour récupérer les bootcamps
    this.getBootcampsForFormation(this.formationId);
  } 

  getBootcampsForFormation(formationId: number): void {
    this.formationService.getBootcampsInFormation(formationId)
      .subscribe(bootcamps => {
        this.bootcamps = bootcamps;
    });
  }
}
