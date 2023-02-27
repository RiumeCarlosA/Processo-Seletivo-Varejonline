import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Operador } from '../models/operador';

@Injectable({
  providedIn: 'root'
})
export class OperadorService {

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<Operador>{
    return this.http.get<Operador>(`${API_CONFIG.baseUrl}/operador/${id}`)
  }
  
  findAll(): Observable<Operador[]> {
    return this.http.get<Operador[]>(`${API_CONFIG.baseUrl}/operador`)
  }

  create(operador: Operador): Observable<Operador> {
    return this.http.post<Operador>(`${API_CONFIG.baseUrl}/operador`, operador);
  }

  update(operador: Operador): Observable<Operador> {
    return this.http.put<Operador>(`${API_CONFIG.baseUrl}/operador/${operador.id}`, operador);
  }

  delete(id: any): Observable<Operador> {
    return this.http.delete<Operador>(`${API_CONFIG.baseUrl}/operador/${id}`)
  }
}
