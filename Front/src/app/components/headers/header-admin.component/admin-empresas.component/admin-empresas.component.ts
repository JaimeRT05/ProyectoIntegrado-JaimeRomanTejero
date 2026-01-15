import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../../../services/admin.service';
import { Empresa } from '../../../../models/admin.model';

@Component({
  selector: 'app-admin-empresas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-empresas.component.html',
  styleUrls: ['./admin-empresas.component.css']
})
export class AdminEmpresasComponent implements OnInit {

  empresas: Empresa[] = [];
  mensaje = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.cargarEmpresas();
  }

  cargarEmpresas(): void {
    this.adminService.listarEmpresas().subscribe({
      next: data => this.empresas = data,
      error: err => console.error(err)
    });
  }

  validarEmpresa(idEmpresa: number): void {
    this.adminService.validarEmpresa(idEmpresa).subscribe({
      next: () => {
        this.mensaje = 'Empresa validada correctamente';
        this.cargarEmpresas();
      },
      error: () => this.mensaje = 'Error al validar empresa'
    });
  }

  eliminarEmpresa(idEmpresa: number): void {
    if (!confirm('¿Seguro que deseas eliminar esta empresa?')) return;

    this.adminService.eliminarEmpresa(idEmpresa).subscribe({
      next: () => {
        this.mensaje = 'Empresa eliminada correctamente';
        
        this.empresas = this.empresas.filter(e => e.idEmpresa !== idEmpresa);

      },
      error: () => this.mensaje = 'Error al eliminar empresa'
    });
  }
}
