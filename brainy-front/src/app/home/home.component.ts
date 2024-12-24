import { Component, HostListener } from '@angular/core';
import { NoteService } from '../note/note.service';
import { ExpirienceComponent } from "../expirience/expirience.component";

@Component({
  selector: 'app-home',
  imports: [ExpirienceComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  notes: any[] = [];
  isAtTop = true; // Indica se l'utente è in cima alla pagina
  rotationAngle = 0; // Rotazione del pulsante

  constructor(private noteService: NoteService) {}

  ngOnInit(): void {
    this.noteService.getNotes().subscribe((data) => {
      this.notes = data;
    });
  }
  
  handleButtonClick() {
    if (this.isAtTop) {
      // Scorri verso il basso
      const element = document.querySelector('#target-section');
      if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    } else {
      // Torna all'inizio
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // Ruota il pulsante
    this.rotationAngle += 180; // Aggiungi 180° alla rotazione
    this.isAtTop=!this.isAtTop;
  }

}
