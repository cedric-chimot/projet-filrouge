import { Component } from '@angular/core';
import { ProductListComponent } from "./product-list/product-list.component";
import { Formations } from '../../models/formations.model';
import { FormationServiceService } from '../../services/formation/formation-service.service';


@Component({
    selector: 'app-products-page',
    standalone: true,
    templateUrl: './products-page.component.html',
    styleUrl: './products-page.component.scss',
    imports: [ProductListComponent]
})
export class ProductsPageComponent {

  formations: Formations[] = []

  constructor(private formationService: FormationServiceService) {}//, private albumService: AlbumService

  ngOnInit(): void {
    this.formationService.getFormations().subscribe((formations) => {this.formations = formations;});
  }

}
