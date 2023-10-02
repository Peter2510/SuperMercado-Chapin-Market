import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from 'src/app/sucursal/bodega/Producto';
import { Inventario } from '../../Inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  
    //URL QUE OBTIENE EL LISTADO DE TODOS los PRODUCTOS SEGUN SUCURSAL
    private baseURL = "http://localhost:5400/chapinMarket";

    constructor(private httpClient: HttpClient) { }

    //Obtener productos inventario
    obtenerProductosInventario(sucursal: string): Observable<Inventario[]> {
      
      // Crear un objeto HttpParams para incluir el parámetro de consulta
      const params = new HttpParams().set('sucursal', sucursal);
  
      // Agregar los parámetros a la URL usando el objeto HttpParams
      return this.httpClient.get<Inventario[]>(`${this.baseURL}/inventario`, { params });
    }


    productoDisponiblesParaAgregar(sucursal:number): Observable<Producto[]> {
      const params = new HttpParams().set('sucursal', sucursal);
  
      return this.httpClient.get<Producto[]>(`${this.baseURL}/disponibles-inventario`, { params });
  
    }

    agregarProductoInventario(inventario:any){

      return this.httpClient.post(`${this.baseURL}/agregar-producto-inventario`,inventario);
  
    }

    cantidadProductoBodega(codigo_producto:any, codigo_sucursal:any):Observable<Number>{

      const params = new HttpParams().set('codigo_sucursal', codigo_sucursal).
      set('codigo_producto',codigo_producto);
  
      // Agregar los parámetros a la URL usando el objeto HttpParams
      return this.httpClient.get<Number>(`${this.baseURL}/cantidad-en-bodega`, { params});

    }

    obtenerProductoInventario(codigoProducto:any,codigoSucursal:number): Observable<Inventario>{
      let cProducto:string = codigoProducto;
      const params = new HttpParams()
      .set('codigo_producto', cProducto)
      .set('codigo_sucursal', codigoSucursal);
  
      return this.httpClient.get<Inventario>(`${this.baseURL}/obtener-producto-inventario`, { params });
    }

    actualizarInventario(codigo_producto: any, codigo_sucursal: any, cantidad_producto_inventario:any, cantidad_producto_bodega:any) {
      const request = {
        codigo_producto: codigo_producto,
        codigo_sucursal: codigo_sucursal,
        cantidad_producto_inventario: cantidad_producto_inventario,
        cantidad_producto_bodega: cantidad_producto_bodega
      };

      console.log(request);
    
      return this.httpClient.post(`${this.baseURL}/actualizar-inventario-producto`, request);
    }
 
}
