import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { OfertaCiudadanoService } from '../../../../services/oferta-ciudadano.service';
import { Oferta } from '../../../../models/oferta.model';

@Component({
  selector: 'app-ofertas-ciudadano',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './oferta-ciudadano.component.html',
  styleUrls: ['./oferta-ciudadano.component.css']
})
export class OfertaCiudadanoComponent implements OnInit {

  ofertas: Oferta[] = [];
  cargando = false;

  tipo: string = '';
  palabraClave: string = '';
  
  constructor(private ofertaService: OfertaCiudadanoService) {}

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

