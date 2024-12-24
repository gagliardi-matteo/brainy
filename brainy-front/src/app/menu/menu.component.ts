import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-menu',
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  isScrolled = false; // Variabile per tracciare lo stato dello scroll
  menuOpened = false;

  // Listener per lo scroll della finestra
  @HostListener('window:scroll', [])
  onWindowScroll() {
    // Aggiunge una classe se l'utente ha scrollato verso il basso
    const scrollOffset = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    this.isScrolled = scrollOffset > 0;
  }

  openMenu(): void{
    if(!this.isScrolled){
      this.menuOpened = !this.menuOpened
    }    
  }

}
