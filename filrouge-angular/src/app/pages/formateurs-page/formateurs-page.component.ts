import { Component } from '@angular/core';
import { Formateurs } from '../../models/formateurs.model';
import { FormateurServiceService } from '../../services/users/formateur-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-formateurs-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './formateurs-page.component.html',
  styleUrl: './formateurs-page.component.css'
})
export class FormateursPageComponent {
  formateurs!: Formateurs[];

  constructor(private formateurService: FormateurServiceService) {}

  onInit(): void {
    this.findFormateurs();
  }

  findFormateurs() {
    this.formateurService.getUserFormateurs().subscribe((data) => {
      this.formateurs = data;
    })
  }

}
