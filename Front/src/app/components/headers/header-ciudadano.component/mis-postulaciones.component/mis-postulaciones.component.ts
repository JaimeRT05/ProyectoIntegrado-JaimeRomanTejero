import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostulacionService } from '../../../../services/postulaciones.service';

@Component({
  selector: 'app-mis-postulaciones',
  imports: [CommonModule],
  templateUrl: './mis-postulaciones.component.html',
  styleUrls: ['./mis-postulaciones.component.css']
})
export class MisPostulacionesComponent implements OnInit {

  postulaciones: any[] = [];
  mensajeError: string = '';

  constructor(private postulacionService: PostulacionService) { }

  ngOnInit(): void {
    this.cargarPostulaciones();
  }

  cargarPostulaciones() {
    this.postulacionService.misPostulaciones().subscribe({
      next: data => this.postulaciones = data,
      error: err => this.mensajeError = 'No se pudieron cargar las postulaciones'
    });
  }

  eliminarPostulacion(idPostulacion: number) {
    this.postulacionService.eliminarPostulacion(idPostulacion).subscribe({
      next: () => {
        this.postulaciones = this.postulaciones.filter(p => p.idPostulacion !== idPostulacion);
      },
      error: err => this.mensajeError = err.error || 'No se pudo eliminar la postulación'
    });
  }

}
