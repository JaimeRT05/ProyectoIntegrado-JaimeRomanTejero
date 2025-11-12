import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header-empresa',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header-empresa.component.html',
  styleUrls: ['./header-empresa.component.css']
})
export class HeaderEmpresaComponent {
  @Input() usuario: any; // Recibe información del usuario desde el contenedor
  @Output() logout = new EventEmitter<void>(); // Evento para cerrar sesión

  constructor(private router: Router) {
    
  }

  cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    this.logout.emit(); // Delegamos la acción al contenedor
  }

  verPerfil() {
  // Evitar error si no hay usuario
  if (!this.usuario) return;

  // Comprobar el perfil
  if (this.usuario.perfil === 'EMPRESA') {
    // Navegar al perfil de ciudadano
    this.router.navigate(['/empresa/perfil']);
  } else {
    // Otras comprobaciones opcionales si quieres reutilizar el método
    console.warn('No corresponde con empresa');
  }
}
}
