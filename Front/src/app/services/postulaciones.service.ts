import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PostulacionDTO {
  idPostulacion: number;
  idOferta: number;
  tituloOferta: string;
  nombreEmpresa: string;
  fechaPostulacion: string;
}

@Injectable({
  providedIn: 'root'
})
export class PostulacionService {

  private apiUrl = 'http://localhost:8080/api/postulaciones';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  misPostulaciones(): Observable<PostulacionDTO[]> {
    return this.http.get<PostulacionDTO[]>(`${this.apiUrl}/mis-postulaciones`, { headers: this.getHeaders() });
  }

  postular(idOferta: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/postular/${idOferta}`, {}, { headers: this.getHeaders(), responseType: 'text' });
  }

  eliminarPostulacion(idPostulacion: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/eliminarPostulacion?idPostulacion=${idPostulacion}`, 
      { headers: this.getHeaders(), responseType: 'text' }
    );
  }

}
