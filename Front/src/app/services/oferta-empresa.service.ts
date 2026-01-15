import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OfertaDTO,OfertaEditarDTO, CrearOferta } from '../models/oferta.model';

@Injectable({ providedIn: 'root' })
export class OfertaEmpresaService {

  private baseUrl = 'http://localhost:8080/api/ofertas';

  constructor(private http: HttpClient) { }

  // Crear oferta
  crearOferta(oferta: CrearOferta): Observable<OfertaDTO> {
    const token = localStorage.getItem('token');

    console.log('Token antes de enviar la petición:', token);

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post<OfertaDTO>(`${this.baseUrl}/crearOferta`, oferta, { headers });
  }

  listarOfertasEmpresa(): Observable<OfertaDTO[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<OfertaDTO[]>(`${this.baseUrl}/listarOfertasDeUnaEmpresa`, { headers });
  }

  modificarOferta(idOferta: number, dto: OfertaEditarDTO): Observable<OfertaDTO> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.put<OfertaDTO>(`${this.baseUrl}/modificarOferta/${idOferta}`, dto, { headers });
  }

  eliminarOferta(idOferta: number): Observable<{mensaje: string}> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<{mensaje: string}>(`${this.baseUrl}/eliminarOferta/${idOferta}`, { headers });
  }

  finalizarOferta(idOferta: number): Observable<string> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.put(`${this.baseUrl}/finalizarOferta/${idOferta}`, {}, { headers, responseType : 'text' });
  }

  ofertasPendientes(): Observable<OfertaDTO[]> {
    return this.http.get<OfertaDTO[]>(
      `${this.baseUrl}/empresa/ofertas-pendientes`
    );  
  }

}
