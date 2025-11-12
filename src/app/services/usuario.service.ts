// usuario.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface Usuario {
  nombreUsuario: string;
  perfil: 'CIUDADANO' | 'EMPRESA' | 'ADMIN';
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private usuarioSubject = new BehaviorSubject<Usuario | null>(null);
  usuario$ = this.usuarioSubject.asObservable();

  constructor() {
    const usuarioJSON = localStorage.getItem('usuario');
    if (usuarioJSON) {
      this.usuarioSubject.next(JSON.parse(usuarioJSON));
    }
  }

  setUsuario(usuario: Usuario) {
    localStorage.setItem('usuario', JSON.stringify(usuario));
    this.usuarioSubject.next(usuario);
  }

  clearUsuario() {
    localStorage.removeItem('usuario');
    this.usuarioSubject.next(null);
  } 
}
