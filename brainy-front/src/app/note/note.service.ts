import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "../auth/auth.service";

@Injectable({
    providedIn: 'root',
  })
  export class NoteService {
    private apiUrl = 'http://localhost:8080/api/notes';
  
    constructor(private http: HttpClient, private authService: AuthService) {}
  
    getNotes() : Observable<any[]>{
      return this.http.get<any[]>(this.apiUrl);
    }
  
    addNote(note: string ) {
      const email: string | null = this.authService.getEmailFromToken();
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      if(email){
        const params = new HttpParams().set('email', email);
        return this.http.post(this.apiUrl, note, { headers, params });
      }
      else{
        const params = new HttpParams().set('email', '');
        console.log("email Mancante");
        return this.http.post(this.apiUrl, note, { headers, params });
      }
      
    }
  }