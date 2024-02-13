import { Component } from '@angular/core';
import { ProductsPageComponent } from '../products-page.component';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProductDetailsComponent } from "../product-details/product-details.component";
// import Album from '../../../models/album.model';
import Formation from '../../../models/formation.model';
// import { AlbumService } from '../../../services/album.service';
import { FormationService } from '../../../services/formation/formation.service';
import { ProductCardComponent } from "../product-card/product-card.component";

@Component({
    selector: 'app-product-page',
    standalone: true,
    templateUrl: './product-page.component.html',
    styleUrl: './product-page.component.css',
    imports: [ProductDetailsComponent, RouterModule, ProductCardComponent]
})
export class ProductPageComponent {

 
  product!: Formation ;//| Album

  constructor(
      private router: Router, 
      private route:ActivatedRoute, 
      private formationService: FormationService 
      //private albumService: AlbumService
  ) {}

  private subscribeFormation(id: number): void {
    this.formationService.getFormation(id).subscribe({
      next: (formation) => this.product = formation,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }

  // private subscribeAlbum(id: number): void {
  //   this.albumService.getAlbum(id).subscribe({
  //     next: (album) => this.product = album,
  //     error: (err) => console.error('Error loading album:', err)
  //   });
  // }

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
