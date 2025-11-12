import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpresaPublicoService, Empresa } from '../../../../services/empresa-publico.service';

@Component({
  selector: 'app-empresas-publico',
  standalone: true,
  imports: [CommonModule],
  template: `<h2>Listado de empresas públicas</h2>`,
  templateUrl: './empresas-publico.component.html',
  styleUrls: ['./empresas-publico.component.css']
})
export class EmpresasPublicoComponent implements OnInit {

  empresas: Empresa[] = [];
  cargando = true;
  error = '';

  constructor(private empresaService: EmpresaPublicoService) {}

  ngOnInit(): void {
    console.log('EmpresasPublicoComponent cargado');
    this.empresaService.listarEmpresas().subscribe({
      next: (res) => {
        this.empresas = res;
        this.cargando = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Error al cargar las empresas.';
        this.cargando = false;
      }
    });
  }
}
