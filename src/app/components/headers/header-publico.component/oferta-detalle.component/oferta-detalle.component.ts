import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfertaPublicService, OfertaDetalle } from '../../../../services/oferta-publico.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-oferta-detalle',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './oferta-detalle.component.html',
  styleUrls: ['./oferta-detalle.component.css']
})
export class OfertaDetalleComponent implements OnInit {

  oferta!: OfertaDetalle;
  cargando = true;

  constructor(
    private route: ActivatedRoute,
    private ofertaService: OfertaPublicService,
     private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.ofertaService.obtenerDetalle(id).subscribe({
      next: data => {
        this.oferta = data;
        this.cargando = false;
      },
      error: err => {
        console.error('Error cargando detalle', err);
        this.cargando = false;
      }
    });
  }

  volver() {
  this.router.navigate(['/ofertasPublico']);
  }

}
