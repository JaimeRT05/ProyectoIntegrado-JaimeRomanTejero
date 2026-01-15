import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CiudadanoService } from '../../../../services/ciudadano.service';
import { Ciudadano } from '../../../../models/ciudadano.model';
import { UsuarioService } from '../../../../services/usuario.service';
import { CurriculumService } from '../../../../services/curriculum.service';

@Component({
  selector: 'app-perfil-ciudadano',
  imports: [CommonModule],
  templateUrl: './perfil-ciudadano.component.html',
  styleUrls: ['./perfil-ciudadano.component.css']
})
export class PerfilCiudadanoComponent implements OnInit {
  ciudadano: Ciudadano | null = null;
  cargando = true;

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private ciudadanoService: CiudadanoService,
    private curriculumService: CurriculumService
  ) {}

  ngOnInit(): void {
    const usuario = this.usuarioService.usuarioSubject.getValue();
    if (usuario) {
      this.ciudadanoService.getCiudadanoPorNombreUsuario(usuario.nombreUsuario)
        .subscribe({
          next: res => {
            this.ciudadano = res;
            this.cargando = false;
          },
          error: err => {
            console.error(err);
            this.cargando = false;
          }
        });
    }
  }

  editarPerfil() {
    this.router.navigate(['/ciudadano/editarPerfil']);
  }

  archivo!: File;
  mensaje = '';



  onFileSelected(event: any) {
    this.archivo = event.target.files[0];
  }

  subirCurriculum() {
    this.curriculumService.subirCurriculum(this.archivo).subscribe({
      next: () => this.mensaje = 'Currículum subido correctamente',
      error: () => this.mensaje = 'Error al subir el currículum'
    });
  }

}
