import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ciudadano } from '../models/ciudadano.model';

@Injectable({
  providedIn: 'root'
})
export class CiudadanoService {
  private apiUrl = 'http://localhost:8080/api/ciudadanos';

  constructor(private http: HttpClient) {}

  getCiudadanoPorNombreUsuario(nombreUsuario: string): Observable<Ciudadano> {
    return this.http.get<Ciudadano>(`${this.apiUrl}/buscarCiudadanoPorNombreUsuario/${nombreUsuario}`);
  }

  actualizarCiudadano(idCiudadano: number, datos: Partial<Ciudadano>): Observable<Ciudadano> {
    return this.http.put<Ciudadano>(`${this.apiUrl}/actualizarCiudadano/${idCiudadano}`, datos);
  }
}
