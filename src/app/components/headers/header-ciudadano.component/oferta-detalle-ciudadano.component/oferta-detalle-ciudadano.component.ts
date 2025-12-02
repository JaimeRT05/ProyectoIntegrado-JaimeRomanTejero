import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OfertaCiudadanoService } from '../../../../services/oferta-ciudadano.service';
import { OfertaDetalle } from '../../../../models/oferta.model';

@Component({
  selector: 'app-oferta-detalle-ciudadano',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './oferta-detalle-ciudadano.component.html',
  styleUrls: ['./oferta-detalle-ciudadano.component.css']
})
export class OfertaDetalleCiudadanoComponent implements OnInit {

  oferta!: OfertaDetalle;
  cargando = true;
  idCiudadano!: number;

  constructor(
    private route: ActivatedRoute,
    private ofertaService: OfertaCiudadanoService,
    private router: Router
  ) {}

  ngOnInit(): void {

    /** =============================
     *  1. Obtener id del ciudadano
     * =============================*/
    const usuarioStr = localStorage.getItem('usuario');

    if (usuarioStr) {
      const usuario = JSON.parse(usuarioStr);
      this.idCiudadano = usuario.idPerfil; // IMPORTANTE: esto debe ser el idCiudadano
    }

    /** =============================
     *  2. Cargar oferta por ID
     * =============================*/
    const idOferta = Number(this.route.snapshot.paramMap.get('id'));

    this.ofertaService.obtenerDetalle(idOferta).subscribe({
      next: data => {
        this.oferta = data;
        this.cargando = false;
      },
      error: err => {
        console.error('Error cargando oferta', err);
        this.cargando = false;
      }
    });
  }

  /** =============================
   *  Inscribir al ciudadano
   * =============================*/
  inscribirse() {

    if (!this.idCiudadano) {
      alert("No se pudo obtener el ID del ciudadano.");
      return;
    }

    this.ofertaService.inscribirse(this.oferta.idOferta, this.idCiudadano).subscribe({
      next: () => {
        alert("¡Te has inscrito correctamente en esta oferta!");
      },
      error: (err) => {
        console.error(err);
        alert("Error al inscribirse. Quizás ya estabas inscrito.");
      }
    });
  }

  volver() {
    this.router.navigate(['/ciudadano/ofertas']);
  }
}
