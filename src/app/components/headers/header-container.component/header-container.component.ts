import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Usuario, UsuarioService } from '../../../services/usuario.service';
import { HeaderPublicoComponent } from '../header-publico.component/header-publico.component';
import { HeaderCiudadanoComponent } from '../header-ciudadano.component/header-ciudadano.component';
import { HeaderEmpresaComponent } from '../header-empresa.component/header-empresa.component';
import { HeaderAdminComponent } from '../header-admin.component/header-admin.component';


@Component({
  selector: 'app-header-container',
  standalone: true,
  imports: [
    CommonModule,
    HeaderCiudadanoComponent,
    HeaderEmpresaComponent,
    HeaderAdminComponent,
    HeaderPublicoComponent
  ],
  templateUrl: './header-container.component.html',
  styleUrls: ['./header-container.component.css']
})
export class HeaderContainerComponent implements OnInit {
  usuario: Usuario | null = null;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
    this.usuarioService.usuario$.subscribe(usuario => {
      this.usuario = usuario;
    });
  }

  cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    this.usuario = null;
  }
}