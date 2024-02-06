import { Component } from '@angular/core';
import { FormationsPageComponent } from "../formations-page/formations-page.component";

@Component({
    selector: 'app-home-page',
    standalone: true,
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.css',
    imports: [FormationsPageComponent]
})
export class HomePageComponent {

}
