import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Inventario } from '../../Inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  
    //URL QUE OBTIENE EL LISTADO DE TODOS los PRODUCTOS SEGUN SUCURSAL
    private baseURL = "http://localhost:5400/chapinMarket/inventario";

    constructor(private httpClient: HttpClient) { }

    //Obtener productos inventario
    obtenerProductosInventario(sucursal: string): Observable<Inventario[]> {
      
      // Crear un objeto HttpParams para incluir el parámetro de consulta
      const params = new HttpParams().set('sucursal', sucursal);
  
      // Agregar los parámetros a la URL usando el objeto HttpParams
      return this.httpClient.get<Inventario[]>(`${this.baseURL}`, { params });
    }



}
