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
      date: 'September 2024 - Today',
      description: 'Led the design and development of scalable DEVOPS RESTful APIs on GCP environment, ensuring high performance and reliability. Mentored junior developers and oversaw the implementation of best coding practices.'
    },
    {
      title: 'Software Engineer',
      date: 'March 2022 - September 2024',
      description: 'Managed and enhanced legacy applications, implementing innovative solutions to improve system stability and performance. Collaborated with cross-functional teams to deliver software updates aligned with business objectives.'
    }
  ];

}
