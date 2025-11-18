import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { OfertaPublicService, Oferta } from '../../../..//services/oferta-publico.service';

@Component({
  selector: 'app-ofertas-publico',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './ofertas-publico.component.html',
  styleUrls: ['./ofertas-publico.component.css']
})
export class OfertasPublicoComponent implements OnInit {

  ofertas: Oferta[] = [];
  cargando = false;

  tipo: string = '';
  palabraClave: string = '';
  
  constructor(private ofertaService: OfertaPublicService) {}

  ngOnInit(): void {
    this.buscar();
  }

  buscar() {
    this.cargando = true;

    this.ofertaService.listarOfertas(this.tipo, this.palabraClave).subscribe({
      next: (data) => {
        this.ofertas = data;
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error cargando ofertas', err);
        this.cargando = false;
      }
    });
  }
}