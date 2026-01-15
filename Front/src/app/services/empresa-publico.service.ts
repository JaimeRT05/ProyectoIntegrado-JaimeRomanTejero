import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Empresa {
  idEmpresa: number;
  nombreCompleto: string;
  cif: string;
  actividadEmpresa: string;
  calle: string;
  numero: number;
  poligono?: string;
  localidad: string;
  codigoPostal: number;
  telefono: string;
  email: string;
  direccionWeb?: string;
}

@Injectable({
  providedIn: 'root'
})
export class EmpresaPublicoService {

  private apiUrl = 'http://localhost:8080/api/empresas'; 

  constructor(private http: HttpClient) {}

  listarEmpresas(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(`${this.apiUrl}/listarEmpresas`);
  }
}
