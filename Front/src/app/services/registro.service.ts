import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {
  private baseUrl = 'http://localhost:8080/api'; // <-- Aquí el puerto de Spring Boot

  constructor(private http: HttpClient) { }

  crearCiudadano(data: FormData) {
    return this.http.post(`${this.baseUrl}/ciudadanos/crearCiudadano`, data);
  }

  crearEmpresa(data: FormData) {
    return this.http.post(`${this.baseUrl}/empresas/crearEmpresa`, data);
  }

  crearAdmin(adminData: any) {
    return this.http.post(`${this.baseUrl}/admin/crear`, adminData, { headers: { 'Content-Type': 'application/json' }});
  }
}