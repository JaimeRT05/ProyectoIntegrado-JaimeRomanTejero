import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioSubject = new BehaviorSubject<any>(this.getUsuarioActual());
  usuario$ = this.usuarioSubject.asObservable();

  // Guarda token y extrae perfil del JWT
  login(token: string) {
    localStorage.setItem('token', token);

    const decoded: any = jwtDecode(token);
    const usuario = {
      nombreUsuario: decoded.sub,
      perfil: decoded.perfil
    };

    localStorage.setItem('usuario', JSON.stringify(usuario));
    this.usuarioSubject.next(usuario);
  }

  getUsuarioActual() {
    const data = localStorage.getItem('usuario');
    return data ? JSON.parse(data) : null;
  }

  logout() {
    localStorage.clear();
    this.usuarioSubject.next(null);
  }
}
