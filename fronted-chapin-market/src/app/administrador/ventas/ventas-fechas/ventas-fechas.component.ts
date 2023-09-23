import { Component } from '@angular/core';

@Component({
  selector: 'app-ventas-fechas',
  templateUrl: './ventas-fechas.component.html',
  styleUrls: ['./ventas-fechas.component.css']
})
export class VentasFechasComponent {

  fechaInicio: string;
  fechaFin: string;

  obtenerFechas() {
   
    // Accede a las fechas desde las variables locales


    alert('Fecha de inicio: ' + this.fechaInicio + " " + 'Fecha de fin: ' + this.fechaFin)


  }


}
