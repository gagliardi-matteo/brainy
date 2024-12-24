import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-expirience',
  imports: [CommonModule],
  templateUrl: './expirience.component.html',
  styleUrl: './expirience.component.css'
})
export class ExpirienceComponent {

  experiences = [
    {
      title: 'Tech Lead',
      date: 'Settembre 2024 - Presente',
      description: 'Sviluppo API RESTful con Spring Boot e PostgreSQL.'
    },
    {
      title: 'Software Engineer',
      date: 'Marzo 2022 - Settembre 2024',
      description: 'Manutenzione e sviluppo di applicazioni legacy.'
    }
  ];

}
