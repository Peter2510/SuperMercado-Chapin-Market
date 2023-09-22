import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empleado } from '../Empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  //URL QUE OBTIENE EL LISTADO DE TODOS LOS EMPLEADOS DE BACKEND
  private baseURL = "http://localhost:5400/chapinMarket/empleados";

  constructor(private httpClient: HttpClient) { }

  //Obtener los empleados 
  obtenerListaEmpleados():Observable<Empleado[]>{
    return this.httpClient.get<Empleado[]>(`${this.baseURL}`);
  }

}
