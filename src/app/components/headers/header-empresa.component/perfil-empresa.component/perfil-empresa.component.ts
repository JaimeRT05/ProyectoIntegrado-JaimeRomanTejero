import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EmpresaService } from '../../../../services/empresa.service';
import { UsuarioService } from '../../../../services/usuario.service';
import { perfilEmpresa } from '../../../../models/perfilEmpresa.model';

@Component({
  selector: 'app-perfil-empresa',
  imports: [CommonModule],
  templateUrl: './perfil-empresa.component.html',
  styleUrls: ['./perfil-empresa.component.css']
})
export class PerfilEmpresaComponent implements OnInit {

  empresa: perfilEmpresa | null = null;
  cargando = true;

  constructor(
    private empresaService: EmpresaService,
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  ngOnInit(): void {

    const usuario = this.usuarioService.getUsuarioValue();
    if (!usuario) {
      this.router.navigate(['/']);
      return;
    }

    this.empresaService.getEmpresaPorNombreUsuario(usuario.nombreUsuario).subscribe({
      next: (data) => {
        this.empresa = data;
        this.cargando = false;
      },
      error: () => {
        alert("Error al cargar los datos de la empresa.");
        this.cargando = false;
      }
    });
  }

  editar() {
    this.router.navigate(['/empresa/editarPerfil']);
  }
}
