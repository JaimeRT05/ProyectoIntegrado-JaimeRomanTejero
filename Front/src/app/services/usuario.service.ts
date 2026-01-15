import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Usuario } from '../models/usuario.model';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  public usuarioSubject = new BehaviorSubject<Usuario | null>(null);
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

  getUsuarioValue(): Usuario | null {
    return this.usuarioSubject.getValue();
  }

}
