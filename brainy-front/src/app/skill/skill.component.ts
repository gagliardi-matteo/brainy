import { animate, query, stagger, style, transition, trigger } from '@angular/animations';
import { CommonModule } from '@angular/common';
import { Component, ElementRef } from '@angular/core';

@Component({
  selector: 'app-skill',
  imports: [CommonModule],
  templateUrl: './skill.component.html',
  styleUrl: './skill.component.css',
  animations: [
    trigger('skillsAnimation', [
      transition(':enter', [
        query('.skill-item', [
          style({ opacity: 0, transform: 'translateY(20px)' }),
          stagger(100, [
            animate('0.6s ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
          ])
        ])
      ])
    ])
  ]
})
export class SkillComponent {

  categories = [
    {
      category: 'Frontend',
      skills: [
        { name: 'HTML', percentage: 90, currentPercentage: 0 },
        { name: 'CSS', percentage: 85, currentPercentage: 0 },
        { name: 'JavaScript', percentage: 80, currentPercentage: 0 }
      ]
    },
    {
      category: 'Backend',
      skills: [
        { name: 'Python', percentage: 70, currentPercentage: 0 },
        { name: 'Spring Boot', percentage: 65, currentPercentage: 0 },
        { name: 'Angular', percentage: 75, currentPercentage: 0 }
      ]
    },
    {
      category: 'Tools',
      skills: [
        { name: 'Docker', percentage: 60, currentPercentage: 0 },
        { name: 'GitLab DevOps', percentage: 55, currentPercentage: 0 },
        { name: 'Angular', percentage: 75, currentPercentage: 0 }
      ]
    }
  ];

  constructor(private el: ElementRef) {}

  ngAfterViewInit() {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            this.startAnimation();
            observer.disconnect();
          }
        });
      },
      { threshold: 0.1 }
    );

    observer.observe(this.el.nativeElement.querySelector('#skills'));
  }

  startAnimation() {
    this.categories.forEach((category) => {
      category.skills.forEach((skill) => this.animateSkill(skill));
    });
  }

  animateSkill(skill: any) {
    const step = skill.percentage / 60; // Incremento calcolato su 60 frame (~1 secondo)

    const animate = () => {
      if (skill.currentPercentage < skill.percentage) {
        skill.currentPercentage = Math.min(skill.currentPercentage + step, skill.percentage);
        requestAnimationFrame(animate);
      }
    };

    animate();
  }

}
