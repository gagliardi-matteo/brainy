import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private http: HttpClient) {}

  login(): void {
    // Redirige l'utente al flusso OAuth2
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }
}