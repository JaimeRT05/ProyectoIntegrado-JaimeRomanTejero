import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { OfertaCiudadanoService } from '../../../../services/oferta-ciudadano.service';
import { OfertaDetalle } from '../../../../models/oferta.model';
import { PostulacionService } from '../../../../services/postulaciones.service';

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
    private router: Router,
    private postulacionService: PostulacionService
  ) {}

  ngOnInit(): void {

    const usuarioStr = localStorage.getItem('usuario');

    if (usuarioStr) {
      const usuario = JSON.parse(usuarioStr);
      this.idCiudadano = usuario.idPerfil; // IMPORTANTE: esto debe ser el idCiudadano
    }

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

  inscribirse() {
    this.postulacionService.postular(this.oferta.idOferta).subscribe({
      next: (mensaje) => {
        alert(mensaje);
      },
      error: (err) => {
        console.error('Error al inscribirse:', err);
        alert("Error al inscribirse. Confirma que tienes el curriculum subido y no estas inscrito");
      }
    });
  }

  volver() {
    this.router.navigate(['/ciudadano/ofertas']);
  }
}
