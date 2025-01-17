import { Component } from '@angular/core';
import emailjs from 'emailjs-com';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contacts',
  imports: [FormsModule, CommonModule],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent {

  emailValid: boolean = true;

  form = {
    name: '',
    email: '',
    message: ''
  };

  isEmailCorrect(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(this.form.email);
  }

  onEmailBlur(emailField: any) {
    if (emailField.touched) {
      this.emailValid = this.isEmailCorrect(this.form.email);
    }
  }

  onSubmit(contactForm: any) {

    if (!this.isEmailCorrect(this.form.email)) {
      alert('Insert a valid Email');
      return;
    }

    const formData = {
      from_name: this.form.name,
      from_email: this.form.email,
      message: this.form.message
    };

    emailjs
      .send('service_mvgxpbq', 'template_69qfxla', formData, 'RqbEE9s2b9k-3duaF')
      .then(
        (response) => {
          alert('Email inviata con successo!');
        },
        (error) => {
          alert('Errore durante l\'invio dell\'email.');
        }
      );
  }

}
