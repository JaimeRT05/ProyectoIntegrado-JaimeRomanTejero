import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../../../services/admin.service'
import {Ciudadano} from '../../../../models/admin.model'

@Component({
  selector: 'app-admin-ciudadanos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-ciudadanos.component.html',
  styleUrls: ['./admin-ciudadanos.component.css']
})
export class AdminCiudadanosComponent implements OnInit {

  ciudadanos: Ciudadano[] = [];
  mensaje: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.cargarCiudadanos();
  }

  cargarCiudadanos(): void {
    this.adminService.getCiudadanos().subscribe({
      next: (data) => this.ciudadanos = data,
      error: (err) => console.error('Error al cargar ciudadanos:', err)
    });
  }

  validarCiudadano(ciudadano: Ciudadano): void {
    this.adminService.validarCiudadano(ciudadano.idCiudadano).subscribe({
      next: () => {
        this.mensaje = `Ciudadano ${ciudadano.nombre} validado correctamente`;
        this.cargarCiudadanos();
      },
      error: (err) => {
        console.error(err);
        this.mensaje = `Error al validar ciudadano ${ciudadano.nombre}`;
      }
    });
  }

  eliminarCiudadano(ciudadano: Ciudadano): void {
  if (!confirm(`¿Eliminar ciudadano ${ciudadano.nombre}? Se eliminará su cuenta, todos sus datos relacionados y todas las postulaciones activas.`)) {
    return;
  }

  this.adminService.eliminarCiudadano(ciudadano.idCiudadano).subscribe({
    next: () => {
      this.mensaje = `Ciudadano ${ciudadano.nombre} eliminado correctamente`;
      this.cargarCiudadanos();
    },
    error: (err) => {
      console.error(err);
      this.mensaje = `Error al eliminar ciudadano ${ciudadano.nombre}`;
    }
  });
}


}
