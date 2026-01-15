import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CiudadanoService } from '../../../../services/ciudadano.service';
import { UsuarioService } from '../../../../services/usuario.service';
import { Usuario } from '../../../../models/usuario.model';
import { Ciudadano } from '../../../../models/ciudadano.model';

@Component({
  selector: 'app-editar-perfil-ciudadano',
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-perfil-ciudadano.component.html',
  styleUrls: ['./editar-perfil-ciudadano.component.css']
})

export class EditarPerfilCiudadanoComponent implements OnInit {
  ciudadano: Ciudadano | null = null;
  cargando = true;

  constructor(
    private ciudadanoService: CiudadanoService,
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const usuario = this.usuarioService.getUsuarioValue();
    if (!usuario) {
      this.router.navigate(['/']);
      return;
    }

    this.ciudadanoService.getCiudadanoPorNombreUsuario(usuario.nombreUsuario).subscribe({
      next: (data) => {
        this.ciudadano = data;
        this.cargando = false;
      },
      error: () => {
        alert('Error al cargar datos del ciudadano');
        this.cargando = false;
      }
    });
  }

  guardar() {
  if (!this.ciudadano) return;

    this.ciudadanoService.actualizarCiudadano(this.ciudadano.idCiudadano, {
    nombre: this.ciudadano.nombre,
    apellidos: this.ciudadano.apellidos,
    calle: this.ciudadano.calle,
    numero: this.ciudadano.numero,
    localidad: this.ciudadano.localidad,
    codigoPostal: this.ciudadano.codigoPostal,
    telefono: this.ciudadano.telefono,
    profesion: this.ciudadano.profesion
    }).subscribe({
    next: res => {
      alert('Perfil actualizado correctamente');
      this.router.navigate(['/ciudadano/perfil']);
    },
    error: err => console.error(err)
    });
  }
  
  volverPerfil() {
  this.router.navigate(['/ciudadano/perfil']);
  }

  archivoSeleccionado: string | null = null;

  ArchivoSeleccionado(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.archivoSeleccionado = file.name;
    }
  }
}