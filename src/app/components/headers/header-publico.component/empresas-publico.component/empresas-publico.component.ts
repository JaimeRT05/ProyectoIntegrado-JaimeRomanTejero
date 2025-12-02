import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmpresaPublicoService, Empresa } from '../../../../services/empresa-publico.service';

@Component({
  selector: 'app-empresas-publico',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './empresas-publico.component.html',
  styleUrls: ['./empresas-publico.component.css']
})
export class EmpresasPublicoComponent implements OnInit {

  empresas: Empresa[] = [];
  empresasFiltradas: Empresa[] = [];
  cargando = true;
  error = '';

  filtroNombre = '';
  filtroActividad = '';

  constructor(private empresaService: EmpresaPublicoService) {}

  ngOnInit(): void {
    console.log('EmpresasPublicoComponent cargado');
    this.empresaService.listarEmpresas().subscribe({
      next: (res) => {
        this.empresas = res;
        this.empresasFiltradas = res; // Inicializamos las filtradas
        this.cargando = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Error al cargar las empresas.';
        this.cargando = false;
      }
    });
  }

  aplicarFiltro() {
    this.empresasFiltradas = this.empresas.filter(e =>
      e.nombreCompleto.toLowerCase().includes(this.filtroNombre.toLowerCase()) &&
      e.actividadEmpresa.toLowerCase().includes(this.filtroActividad.toLowerCase())
    );
  }
}
