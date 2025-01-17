import { Component } from '@angular/core';
import emailjs from 'emailjs-com';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contacts',
  imports: [FormsModule],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent {

  form = {
    name: '',
    email: '',
    message: ''
  };

  captchaToken: string | null = null;

  onCaptchaResolved(token: string | null) {
    this.captchaToken = token; // Salva il token CAPTCHA
  }

  onSubmit(contactForm: any) {

    if (!this.captchaToken) {
      alert('Verifica CAPTCHA richiesta.');
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
