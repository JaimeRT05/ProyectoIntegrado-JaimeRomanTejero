import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
  
  getCiudadanos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/listarCiudadanos`, { headers: this.getHeaders() });
  }

  validarCiudadano(idCiudadano: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/ValidarCiudadano/${idCiudadano}`, {}, { headers: this.getHeaders() });
  }

  eliminarCiudadano(idCiudadano: number): Observable<{mensaje: string}> {
    return this.http.delete<{mensaje: string}>(`${this.baseUrl}/eliminarCiudadanos/${idCiudadano}`, { headers: this.getHeaders() });
  }

  listarEmpresas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/listarEmpresas`, { headers: this.getHeaders() });
  }

  validarEmpresa(idEmpresa: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/ValidarEmpresas/${idEmpresa}`, {}, { headers: this.getHeaders() });
  }

  eliminarEmpresa(idEmpresa: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminarEmpresa/${idEmpresa}`, { headers: this.getHeaders() });
  }

  listarOfertas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/listarOfertas`, { headers: this.getHeaders() });
  }

  eliminarOferta(idOferta: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminarOfertas/${idOferta}`, { headers: this.getHeaders() });
  }
}
