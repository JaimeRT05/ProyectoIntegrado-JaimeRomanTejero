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
  @Input() usuario: any;
  @Output() logout = new EventEmitter<void>();

  idOfertaSeleccionada!: number; // <-- agrega esto si tienes la oferta seleccionada

  constructor(private router: Router) {}

  cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    this.logout.emit();
  }

  verPerfil() {
    if (!this.usuario) return;
    if (this.usuario.perfil === 'EMPRESA') {
      this.router.navigate(['/empresa/perfil']);
    } else {
      console.warn('No corresponde con empresa');
    }
  }

  irACandidatos() {
    this.router.navigate(['/empresa/candidatos']);
  }
}