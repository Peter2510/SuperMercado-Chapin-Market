import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  generarVenta(){

  }


}
