import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../../../services/admin.service';
import { Oferta } from '../../../../models/admin.model';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-ofertas.component.html',
  styleUrls: ['./admin-ofertas.component.css']
})
export class AdminOfertasComponent implements OnInit {

  ofertas: Oferta[] = [];
  mensaje = '';

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.cargarOfertas();
  }

  cargarOfertas() {
    this.adminService.listarOfertas().subscribe({
      next: data => this.ofertas = data,
      error: () => this.mensaje = 'Error al cargar las ofertas'
    });
  }

  eliminarOferta(oferta: Oferta) {
    if (oferta.estado === 'ELIMINADA') return;

    this.adminService.eliminarOferta(oferta.idOferta).subscribe({
      next: () => {
        this.mensaje = 'Oferta eliminada correctamente';
        this.cargarOfertas();
      },
      error: () => {
        this.mensaje = 'No se pudo eliminar la oferta';
      }
    });
  }
}
