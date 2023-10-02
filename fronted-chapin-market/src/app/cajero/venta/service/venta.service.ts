import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from 'src/app/sucursal/bodega/Producto';
import { Cliente } from '../Cliente';

@Injectable({
  providedIn: 'root',
})
export class VentaService {
  constructor(private httpClient: HttpClient) {}

  private baseURL = 'http://localhost:5400/chapinMarket';

  //Validar cliente
  validarCliente(nit: string): Observable<Cliente> {

    const params = new HttpParams().set('nit', nit);
    
    return this.httpClient.get<Cliente>(`${this.baseURL}/obtener-cliente`, { params });
  }

  crearCliente(cliente:any){

    return this.httpClient.post(`${this.baseURL}/crear-cliente`,cliente);

  }

  ProductosVenta(codigo_sucursal: any): Observable<Producto[]>{

    const params = new HttpParams().set('codigo_sucursal', codigo_sucursal);
    
    return this.httpClient.get<Producto[]>(`${this.baseURL}/productos-venta`, { params });
  }

  generarVenta(nit:any,sucursal:any,total:any,cajero:any):Observable<Number>{

    const params = new HttpParams().set('codigo_sucursal', sucursal).
      set('codigo_cajero',cajero).
      set('nit_cliente',nit).
      set('total_venta',total);
  
      // Agregar los parámetros a la URL usando el objeto HttpParams
      return this.httpClient.get<Number>(`${this.baseURL}/generar-venta`, { params});

  }

}
