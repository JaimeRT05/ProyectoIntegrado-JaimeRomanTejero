import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class CurriculumService {

  private api = 'http://localhost:8080/api/curriculums';

  constructor(private http: HttpClient) {}

  subirCurriculum(file: File) {
    const formData = new FormData();
    formData.append('archivo', file);

    const token = localStorage.getItem('token') || '';

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${this.api}/subir`, formData, { headers });
  }

  descargarCurriculum(idCurriculum: number) {
    const token = localStorage.getItem('token') || '';
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get(`${this.api}/descargar/${idCurriculum}`, {
      headers,
      responseType: 'blob'
    });
  }
}
