import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bodega } from '../../Bodega';
import { Producto } from '../../Producto';

@Injectable({
  providedIn: 'root',
})
export class BodegaService {
  constructor(private httpClient: HttpClient) {}

  //Obteniendo productos de bodega segun sucursal
  private baseURL = 'http://localhost:5400/chapinMarket';

  //Obtener productos bodega
  obtenerProductosBodega(sucursal: string): Observable<Bodega[]> {
    // Crear un objeto HttpParams para incluir el parámetro de consulta
    const params = new HttpParams().set('sucursal', sucursal);

    // Agregar los parámetros a la URL usando el objeto HttpParams
    return this.httpClient.get<Bodega[]>(`${this.baseURL}/bodega`, { params });
  }

  existeProducto(nombre: string): Observable<Boolean> {
    const params = new HttpParams().set('nombre', nombre);

    return this.httpClient.get<Boolean>(`${this.baseURL}/producto`, { params });
  }

  //crear producto
  crearProducto(producto: any, bodega: any) {
    const request = {
      producto: producto,
      bodega: bodega,
    };

    console.log(request)

    return this.httpClient.post(`${this.baseURL}/crear-producto`, request);
  }


  productoDisponiblesParaAgregar(sucursal:number): Observable<Producto[]> {
    const params = new HttpParams().set('sucursal', sucursal);

    return this.httpClient.get<Producto[]>(`${this.baseURL}/disponibles`, { params });

  }

  agregarProductoSucursal(bodega:any){

    return this.httpClient.post(`${this.baseURL}/agregar-producto-sucursal`, bodega);

  }

  obtenerProductoBodega(codigoProducto:any,codigoSucursal:number): Observable<Bodega>{
    let cProducto:string = codigoProducto;
    const params = new HttpParams()
    .set('codigo_producto', cProducto)
    .set('codigo_sucursal', codigoSucursal);

    return this.httpClient.get<Bodega>(`${this.baseURL}/obtener-producto-bodega`, { params });
  }

  actualizarStock(codigo_producto: any, codigo_sucursal: any, cantidad_producto: any) {
    const request = {
      codigo_producto: codigo_producto,
      codigo_sucursal: codigo_sucursal,
      cantidad_producto: cantidad_producto
    };
  
    return this.httpClient.post(`${this.baseURL}/actualizar-stock-producto`, request);
  }

  obtenerProducto(codigoProducto:any): Observable<Producto>{
    let cProducto:string = codigoProducto;
    const params = new HttpParams()
    .set('codigo', cProducto);

    return this.httpClient.get<Producto>(`${this.baseURL}/obtener-producto`, { params });
  }

  actualizarProducto(codigo_producto: any, nombre:any, descripcion: any, precio:any) {
    const request = {
      codigo: codigo_producto,
      nombre: nombre,
      descripcion: descripcion,
      precio:precio
    };
  
    console.log(request);
  
    return this.httpClient.post(`${this.baseURL}/actualizar-producto`, request);
  }


}
