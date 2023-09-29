import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bodega } from '../../Bodega';

@Injectable({
  providedIn: 'root'
})
export class BodegaService {

  constructor(private httpClient: HttpClient) { }
  
  //Obteniendo productos de bodega segun sucursal
  private baseURL = "http://localhost:5400/chapinMarket/bodega";
   
  //Obtener productos bodega
  obtenerProductosBodega(sucursal: string): Observable<Bodega[]> {
     
  // Crear un objeto HttpParams para incluir el parámetro de consulta
  const params = new HttpParams().set('sucursal', sucursal);
  
  // Agregar los parámetros a la URL usando el objeto HttpParams
    return this.httpClient.get<Bodega[]>(`${this.baseURL}`, { params });
  }
  
  
}
