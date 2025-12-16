import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { perfilEmpresa } from '../models/perfilEmpresa.model';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private apiUrl = 'http://localhost:8080/api/empresas';

  constructor(private http: HttpClient) {}

  getEmpresaPorNombreUsuario(nombreUsuario: string): Observable<perfilEmpresa> {
    return this.http.get<perfilEmpresa>(`${this.apiUrl}/buscarEmpresaPorNombreUsuario/${nombreUsuario}`);
  }

  actualizarEmpresa(idEmpresa: number, datos: Partial<perfilEmpresa>): Observable<perfilEmpresa> {
    return this.http.put<perfilEmpresa>(`${this.apiUrl}/actualizarEmpresa/${idEmpresa}`,datos,);
  }

}
