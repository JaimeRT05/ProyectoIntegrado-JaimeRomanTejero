import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

// Para la lista de ofertas (solo los datos necesarios)
export interface Oferta {
  idOferta: number;
  titulo: string;
  tipoOferta: string;
  perfilProfesional: string;
  fechaPublicacion: string;
}

// Para el detalle de una oferta (todos los datos + empresa)
export interface OfertaDetalle {
  idOferta: number;
  titulo: string;
  descripcion: string;
  perfilProfesional: string;
  tipoOferta: string;
  duracion: string;
  codigoOferta: string;
  fechaPublicacion: string;
  estado: string;
  nombreEmpresa: string;
  actividadEmpresa: string;
}


@Injectable({
  providedIn: 'root'
})
export class OfertaPublicService {

  // Endpoint para la lista de ofertas (con filtros)
  private apiUrlListar = 'http://localhost:8080/api/ofertas/listarOfertas';

  // Endpoint para el detalle de una oferta
  private apiUrlDetalle = 'http://localhost:8080/api/ofertas/obtenerDetalleOferta';

  constructor(private http: HttpClient) {}

  listarOfertas(tipo: string, palabraClave: string): Observable<Oferta[]> {

    let params = new HttpParams();

    if (tipo && tipo.trim() !== '') {
      params = params.set('tipo', tipo);
    }

    if (palabraClave && palabraClave.trim() !== '') {
      params = params.set('palabraClave', palabraClave);
    }

    return this.http.get<Oferta[]>(this.apiUrlListar, { params });
  }

  obtenerDetalle(id: number): Observable<OfertaDetalle> {
  return this.http.get<OfertaDetalle>(`${this.apiUrlDetalle}/${id}`);
  }

}
