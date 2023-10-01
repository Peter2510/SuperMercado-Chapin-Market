import { Component } from '@angular/core';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent {

  nombre:String;
  precio:Number;
  descripcion:Number;
  cantidad:Number;

  nombreNull = false;
  precioNull = false;
  descripcionNull = false;
  cantidadNull = false;

  editarProducto(){
    
  }

}
