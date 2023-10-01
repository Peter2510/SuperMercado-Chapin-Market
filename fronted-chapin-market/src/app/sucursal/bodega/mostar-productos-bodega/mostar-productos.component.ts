import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/login-service/login.service';
import { Bodega } from '../Bodega';
import { BodegaService } from '../productos/service/bodega.service';

@Component({
  selector: 'app-mostar-productos-bodega',
  templateUrl: './mostar-productos.component.html',
  styleUrls: ['./mostar-productos.component.css']
})

export class MostarProductosBodegaComponent implements OnInit{


  bodega:Bodega[];
  sucursal:string = this.loginService.getSucursal();
  codigoProducto:any = 0;
  tipoOpcion:Number =0;

  constructor(private bodegaService: BodegaService, private loginService:LoginService, private router:Router) {  }

  ngOnInit(): void {
    this.obtenerProductosBodega();
   
  }
  
  private obtenerProductosBodega(){
      this.bodegaService.obtenerProductosBodega(this.sucursal).subscribe(data=>{
      this.bodega = data;
    })
  }

  public accion(tipoAccion:Number,codigo:any){

    if(tipoAccion==1){

      this.router.navigate(['stock-Producto'],codigo);


    }else if(tipoAccion==2){
      
      this.router.navigate(['editar-Producto'],codigo);


    }

  }


}
