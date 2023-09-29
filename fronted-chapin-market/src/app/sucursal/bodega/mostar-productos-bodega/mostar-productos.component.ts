import { Component } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import { Bodega } from '../Bodega';
import { BodegaService } from '../productos/service/bodega.service';

@Component({
  selector: 'app-mostar-productos-bodega',
  templateUrl: './mostar-productos.component.html',
  styleUrls: ['./mostar-productos.component.css']
})
export class MostarProductosBodegaComponent {


  bodega:Bodega[];
  sucursal:string = this.loginService.getSucursal();

  constructor(private bodegaService: BodegaService, private loginService:LoginService) {  }

  ngOnInit(): void {
    this.obtenerProductosBodega();
  }
  
  private obtenerProductosBodega(){
      this.bodegaService.obtenerProductosBodega(this.sucursal).subscribe(data=>{
      this.bodega = data;
    })
  }

}
