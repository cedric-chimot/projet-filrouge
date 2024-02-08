import { Component } from '@angular/core';
import { ProductsPageComponent } from '../products-page.component';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProductDetailsComponent } from "../product-details/product-details.component";
import { ProductCardComponent } from "../product-card/product-card.component";
import { FormationServiceService } from '../../../services/formation/formation-service.service';
import { Formations } from '../../../models/formations.model';

@Component({
    selector: 'app-product-page',
    standalone: true,
    templateUrl: './product-page.component.html',
    styleUrl: './product-page.component.css',
    imports: [ProductDetailsComponent, RouterModule, ProductCardComponent]
})
export class ProductPageComponent {

 
  product!: Formations ;

  constructor(
      private router: Router, 
      private route:ActivatedRoute, 
      private formationService: FormationServiceService 

  ) {}

  private subscribeFormation(id: number): void {
    this.formationService.getFormationById(id).subscribe({
      next: (formation) => this.product = formation,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }

  private setSubscribe(type: string | null, id: string | null): void {
    if(type === 'formations' && id){
      this.subscribeFormation(+id);
    } else {
      this.router.navigate(['/not-found']);
    }
  }
  

  ngOnInit(): void {
    const type =this.route.snapshot.paramMap.get('type');
    const id = this.route.snapshot.paramMap.get('id');
    this.setSubscribe(type,id);
  }

}
