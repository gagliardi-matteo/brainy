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
      category: 'Soft Skills',
      skills: [
        { name: 'Leadership', percentage: 85, currentPercentage: 0 },
        { name: 'Self Improvement', percentage: 90, currentPercentage: 0 },
        { name: 'Problem Solving', percentage: 95, currentPercentage: 0 }
      ]
    },
    {
      category: 'Product Skills',
      skills: [
        { name: 'Product Strategy', percentage: 90, currentPercentage: 0 },
        { name: 'Agile', percentage: 85, currentPercentage: 0 },
        { name: 'User Research', percentage: 70, currentPercentage: 0 }
      ]
    },
    {
      category: 'Frontend',
      skills: [
        { name: 'AngularJs', percentage: 90, currentPercentage: 0 },
        { name: 'Angular', percentage: 85, currentPercentage: 0 },
        { name: 'React', percentage: 70, currentPercentage: 0 }
      ]
    },
    {
      category: 'Backend',
      skills: [
        { name: 'RestFull APIs', percentage: 90, currentPercentage: 0 },
        { name: 'Spring Boot', percentage: 90, currentPercentage: 0 },
        { name: 'SQL', percentage: 98, currentPercentage: 0 }
      ]
    },
    {
      category: 'Programming Languages',
      skills: [
        { name: 'Java', percentage: 90, currentPercentage: 0 },
        { name: 'JavaScript', percentage: 90, currentPercentage: 0 },
        { name: 'TypeScript', percentage: 98, currentPercentage: 0 },
      ]
    },
    {
      category: 'Tools & Technologies',
      skills: [
        { name: 'Docker', percentage: 60, currentPercentage: 0 },
        { name: 'GitLab DevOps', percentage: 55, currentPercentage: 0 },
        { name: 'GCP', percentage: 65, currentPercentage: 0 }
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
