import { Component } from '@angular/core';

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})
export class CrearEmpleadoComponent {

  tipoEmpleado:Number = 0;

  cambiarTipoEmpleado(event: any) {
    this.tipoEmpleado = event.target.value;
  }


}
