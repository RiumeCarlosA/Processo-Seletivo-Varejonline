import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Gerente } from '../models/gerente';

@Injectable({
  providedIn: 'root'
})
export class GerenteService {

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<Gerente>{
    return this.http.get<Gerente>(`${API_CONFIG.baseUrl}/gerente/${id}`)
  }
  
  findAll(): Observable<Gerente[]> {
    return this.http.get<Gerente[]>(`${API_CONFIG.baseUrl}/gerente`)
  }

  create(gerente: Gerente): Observable<Gerente> {
    return this.http.post<Gerente>(`${API_CONFIG.baseUrl}/gerente`, gerente);
  }

  update(gerente: Gerente): Observable<Gerente> {
    return this.http.put<Gerente>(`${API_CONFIG.baseUrl}/gerente/${gerente.id}`, gerente);
  }

  delete(id: any): Observable<Gerente> {
    return this.http.delete<Gerente>(`${API_CONFIG.baseUrl}/gerente/${id}`)
  }
}
