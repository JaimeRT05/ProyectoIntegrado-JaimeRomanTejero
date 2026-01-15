import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oferta, OfertaDetalle } from '../models/oferta.model';

@Injectable({ providedIn: 'root' })
export class OfertaCiudadanoService {
  
  private apiUrlListar = 'http://localhost:8080/api/ofertas/listarOfertas';
  private apiUrlDetalle = 'http://localhost:8080/api/ofertas/obtenerDetalleOferta';
  private apiUrlPostulaciones = 'http://localhost:8080/api/postulaciones';

  constructor(private http: HttpClient) {}

  listarOfertas(tipo: string, palabraClave: string): Observable<Oferta[]> {
    let params = new HttpParams();
    if (tipo?.trim()) params = params.set('tipo', tipo);
    if (palabraClave?.trim()) params = params.set('palabraClave', palabraClave);
    return this.http.get<Oferta[]>(this.apiUrlListar, { params });
  }

  obtenerDetalle(id: number): Observable<OfertaDetalle> {
    return this.http.get<OfertaDetalle>(`${this.apiUrlDetalle}/${id}`);
  }

  inscribirse(idOferta: number, idCiudadano: number): Observable<any> {
    return this.http.post(`${this.apiUrlPostulaciones}/postulaciones`, { idOferta, idCiudadano });
  }
}
