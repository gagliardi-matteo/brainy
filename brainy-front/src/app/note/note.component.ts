import { Component } from '@angular/core';
import { NoteService } from './note.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-note',
  imports: [FormsModule],
  templateUrl: './note.component.html',
  styleUrl: './note.component.css'
})
export class NoteComponent {

  note = '';

  constructor(private noteService: NoteService) {}

  onSubmit() {
    if (this.note) {
      this.noteService.addNote(this.note).subscribe(
        (response) => {
          alert('Nota aggiunta con successo!');
          this.resetForm();
        },
        (error) => {
          alert("Errore durante l'aggiunta della nota.");
          console.error(error);
        }
      );
    } else {
      alert('Compila tutti i campi prima di inviare!');
    }
  }

  private resetForm() {
    this.note = '';
  }

}
