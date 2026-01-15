import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EmpresaService } from '../../../../services/empresa.service';
import { UsuarioService } from '../../../../services/usuario.service';
import { perfilEmpresa } from '../../../../models/perfilEmpresa.model';

@Component({
  selector: 'app-editar-perfil-empresa',
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-perfil-empresa.component.html',
  styleUrls: ['./editar-perfil-empresa.component.css']
})
export class EditarPerfilEmpresaComponent implements OnInit {
  empresa: perfilEmpresa | null = null;
  cargando = true;
  archivoSeleccionado: string | null = null;

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
        alert('Error al cargar datos de la empresa');
        this.cargando = false;
      }
    });
  }

  guardar() {
    if (!this.empresa) return;

      console.log('Datos a enviar:', {
    nombreCompleto: this.empresa.nombreCompleto,
    cif: this.empresa.cif,
    actividadEmpresa: this.empresa.actividadEmpresa,
    calle: this.empresa.calle,
    numero: this.empresa.numero,
    poligono: this.empresa.poligono,
    localidad: this.empresa.localidad,
    codigoPostal: this.empresa.codigoPostal,
    telefono: this.empresa.telefono,
    email: this.empresa.email,
    direccionWeb: this.empresa.direccionWeb
  });


    this.empresaService.actualizarEmpresa(this.empresa.idEmpresa, {
      nombreCompleto: this.empresa.nombreCompleto,
      cif: this.empresa.cif,
      actividadEmpresa: this.empresa.actividadEmpresa,
      calle: this.empresa.calle,
      numero: Number(this.empresa.numero),
      poligono: this.empresa.poligono,
      localidad: this.empresa.localidad,
      codigoPostal: Number(this.empresa.codigoPostal),
      telefono: this.empresa.telefono,
      email: this.empresa.email,
      direccionWeb: this.empresa.direccionWeb
    }).subscribe({
      next: res => {
        alert('Perfil actualizado correctamente');
        this.router.navigate(['/empresa/perfil']);
      },
      error: err => console.error(err)
    });
  }

  volverPerfil() {
    this.router.navigate(['/empresa/perfil']);
  }

}
