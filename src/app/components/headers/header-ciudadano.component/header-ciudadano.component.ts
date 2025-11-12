import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-ciudadano',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header-ciudadano.component.html',
  styleUrls: ['./header-ciudadano.component.css']
})
export class HeaderCiudadanoComponent {
  @Input() usuario: any;
  @Output() logout = new EventEmitter<void>();

  constructor(private router: Router) {
    
  }

  cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    this.logout.emit();
  }

  verPerfil() {
  // Evitar error si no hay usuario
  if (!this.usuario) return;

  // Comprobar el perfil
  if (this.usuario.perfil === 'CIUDADANO') {
    // Navegar al perfil de ciudadano
    this.router.navigate(['/ciudadano/perfil']);
  } else {
    // Otras comprobaciones opcionales si quieres reutilizar el método
    console.warn('Perfil no es ciudadano');
  }
}

}
