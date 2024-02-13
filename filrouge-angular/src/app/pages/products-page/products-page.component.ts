import { Component } from '@angular/core';
import { ProductListComponent } from "./product-list/product-list.component";
import Formation from '../../models/formation.model';
// import Album from '../../models/album.model';
import { FormationService } from '../../services/formation/formation.service';
// import { AlbumService } from '../../services/album.service';

@Component({
    selector: 'app-products-page',
    standalone: true,
    templateUrl: './products-page.component.html',
    styleUrl: './products-page.component.scss',
    imports: [ProductListComponent]
})
export class ProductsPageComponent {

  formations: Formation[] = []

  // albums: Album[] = []

  constructor(private formationService: FormationService) {}//, private albumService: AlbumService

  ngOnInit(): void {
    this.formationService.getFormations().subscribe((formations) => {this.formations = formations;});
    // this.albumService.getAlbums().subscribe((albums) => {this.albums = albums;});
  }

}
