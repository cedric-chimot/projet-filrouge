import { Component } from '@angular/core';
import { LoginFormComponent } from '../connexion-page/connexion-page.component';

@Component({
  selector: 'app-a-propos',
  standalone: true,
  imports: [LoginFormComponent],
  templateUrl: './a-propos.component.html',
  styleUrl: './a-propos.component.css'
})
export class AProposComponent {

}
