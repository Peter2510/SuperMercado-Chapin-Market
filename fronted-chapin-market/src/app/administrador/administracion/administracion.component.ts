import { Component } from '@angular/core';

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent {
  
  pestaniaActiva:String = "Empleados";
  
  opcionReporte(pestania:String){
    this.pestaniaActiva = pestania;
  }
}
