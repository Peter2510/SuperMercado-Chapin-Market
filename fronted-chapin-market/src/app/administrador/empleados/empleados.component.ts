import { Component, OnInit } from '@angular/core';
import { Empleado } from './Empleado';
import { EmpleadoService } from './service/empleado.service';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})

export class EmpleadosComponent implements OnInit{
  
  empleados:Empleado[];

  constructor(private empleadoServicio:EmpleadoService){

  }
  
  ngOnInit(): void {
    this.obtenerEmpleados();
  }

  private obtenerEmpleados()  {
    this.empleadoServicio.obtenerListaEmpleados().subscribe(dato=>{
      this.empleados = dato;
    }
      
    )
  }

}
