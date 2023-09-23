import { Component } from '@angular/core';

@Component({
  selector: 'app-descuentos-fechas',
  templateUrl: './descuentos-fechas.component.html',
  styleUrls: ['./descuentos-fechas.component.css']
})
export class DescuentosFechasComponent {

  fechaInicio: string;
  fechaFin: string;

  obtenerFechas() {
    // Accede a las fechas desde las variables locales
    
    alert('Fecha de inicio: ' + this.fechaInicio + " " + 'Fecha de fin: ' + this.fechaFin)
    
  }


}
