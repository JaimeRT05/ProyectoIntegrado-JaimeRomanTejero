import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginRequest {
  nombreUsuario: string;
  contrasenaUsuario: string;
}

interface LoginResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/api/auth'; // Cambia según tu endpoint

  constructor(private http: HttpClient) {}

  login(credentials: { nombreUsuario: string, contrasenaUsuario: string }): Observable<{ token: string, nombreUsuario: string, perfil: string }> {
  return this.http.post<{ token: string, nombreUsuario: string, perfil: string }>(
    `${this.apiUrl}/login`,
    credentials
  );
}
}
