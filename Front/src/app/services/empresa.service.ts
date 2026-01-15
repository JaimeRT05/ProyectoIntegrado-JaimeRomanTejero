import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { perfilEmpresa } from '../models/perfilEmpresa.model';

export interface CandidatoDTO {
  idPostulacion: number;
  nombreCiudadano: string;
  idCurriculum?: number;
  fechaPostulacion?: string;
  estado: 'PENDIENTE' | 'CONTRATADO' | 'RECHAZADO';
}

export interface OfertaDTO {
  idOferta: number;
  titulo: string;
  estado: 'ABIERTA' | 'CERRADA';
}

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private apiUrl = 'http://localhost:8080/api/empresas';

  private ofertaUrl = 'http://localhost:8080/api/ofertas'

  private postulacionURL = 'http://localhost:8080/api/postulaciones'

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getEmpresaPorNombreUsuario(nombreUsuario: string): Observable<perfilEmpresa> {
    return this.http.get<perfilEmpresa>(`${this.apiUrl}/buscarEmpresaPorNombreUsuario/${nombreUsuario}`, { headers: this.getHeaders() });
  }

  actualizarEmpresa(idEmpresa: number, datos: Partial<perfilEmpresa>): Observable<perfilEmpresa> {
    return this.http.put<perfilEmpresa>(`${this.apiUrl}/actualizarEmpresa/${idEmpresa}`, datos, { headers: this.getHeaders() });
  }

  listarCandidatos(idOferta: number): Observable<CandidatoDTO[]> {
    return this.http.get<CandidatoDTO[]>(`${this.postulacionURL}/oferta/${idOferta}/candidatos`, { headers: this.getHeaders() });
  }

  actualizarEstado(idPostulacion: number, estado: 'CONTRATADO' | 'RECHAZADO'): Observable<any> {
    return this.http.put(`${this.postulacionURL}/candidatos/estado/${idPostulacion}?estado=${estado}`, {}, { headers: this.getHeaders() });
  }

  listarOfertasConPostulantes(): Observable<OfertaDTO[]> {
    return this.http.get<OfertaDTO[]>(
      `${this.ofertaUrl}/ofertasConPostulaciones`,
      { headers: this.getHeaders() }
    );
  }
}
