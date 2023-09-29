import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import { Inventario } from '../Inventario';
import { InventarioService } from '../productos/service/inventario.service';

@Component({
  selector: 'app-mostrar-productos-inventario',
  templateUrl: './mostrar-productos.component.html',
  styleUrls: ['./mostrar-productos.component.css']
})

export class MostrarProductosInventarioComponent implements OnInit {
  inventario:Inventario[];
  sucursal:string = this.loginService.getSucursal();

  constructor(private inventarioService: InventarioService, private loginService:LoginService) {  }

ngOnInit(): void {
  this.obtenerProductosInventario();
}

  private obtenerProductosInventario(){
    this.inventarioService.obtenerProductosInventario(this.sucursal).subscribe(data=>{
      this.inventario = data;
    })
  }

}
