    import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oferta, OfertaDetalle, CrearOferta } from '../models/oferta.model';

@Injectable({providedIn: 'root'})
export class OfertaEmpresaService {

  private apiUrl = 'http://localhost:8080/api/ofertas'; 

  constructor(private http: HttpClient) { }

  crearOferta(oferta: CrearOferta): Observable<OfertaDetalle> {
    return this.http.post<OfertaDetalle>(`${this.apiUrl}/crearOferta`, oferta);
  }

 
}
