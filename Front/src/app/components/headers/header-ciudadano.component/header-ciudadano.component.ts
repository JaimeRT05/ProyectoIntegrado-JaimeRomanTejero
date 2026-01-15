import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../../../services/usuario.service';
import { Usuario } from '../../../models/usuario.model';

@Component({
  selector: 'app-header-ciudadano',
  templateUrl: './header-ciudadano.component.html',
  styleUrls: ['./header-ciudadano.component.css']
})
export class HeaderCiudadanoComponent implements OnInit {
  usuario: Usuario | null = null;

  constructor(private router: Router, private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.usuarioService.usuario$.subscribe(u => this.usuario = u);
  }

  irPerfil() {
    this.router.navigate(['/ciudadano/perfil']);
  }

  cerrarSesion() {
    this.usuarioService.clearUsuario();
    this.router.navigate(['/']); 
  }

  irOfertas() {
  this.router.navigateByUrl('/ciudadano/ofertas');
  }

  irPostulaciones(){ 
    this.router.navigateByUrl('/ciudadano/mis-postulaciones')
  }

  debug() {
  console.log('Click en Postulaciones, token:', localStorage.getItem('token'));
}

}
