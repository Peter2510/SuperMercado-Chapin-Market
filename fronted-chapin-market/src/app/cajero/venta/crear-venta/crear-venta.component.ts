import { Component, Input } from '@angular/core';
import { VentaService } from '../service/venta.service';

@Component({
  selector: 'app-crear-venta',
  templateUrl: './crear-venta.component.html',
  styleUrls: ['./crear-venta.component.css']
})
export class CrearVentaComponent {

  @Input() nit:string;
  nombre:String;

  constructor(private validarService:VentaService){
    this.validarService.validarCliente(this.nit).subscribe(clienteHallado=>{
        this.nombre = clienteHallado.nombre;
    })
  }


}
