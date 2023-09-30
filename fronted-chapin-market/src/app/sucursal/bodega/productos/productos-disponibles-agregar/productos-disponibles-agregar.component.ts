import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { Producto } from '../../Producto';
import { BodegaService } from '../service/bodega.service';

@Component({
  selector: 'app-productos-disponibles-agregar',
  templateUrl: './productos-disponibles-agregar.component.html',
  styleUrls: ['./productos-disponibles-agregar.component.css']
})

export class ProductosDisponiblesAgregarComponent implements OnInit{

  cantidad: Number = 0;
  codigoProducto:Number = 0;
  productos:Producto[];
  
  cantidadNull = false;
  codigoProductoNull = false;

  constructor(private bodegaService:BodegaService,private login:LoginService){}


  ngOnInit(): void {
    
    let nSucural:Number = this.login.getSucursal();
    let sucursal:number = nSucural.valueOf();

    this.bodegaService.productoDisponiblesParaAgregar(sucursal).subscribe(productos=>{
      this.productos = productos;
    })
  }

  public agregarProducto(){

    this.validarCampos();

    if(!this.cantidadNull && !this.codigoProductoNull){

      let bodega = {
        codigo_producto:this.codigoProducto,
        codigo_sucursal : this.login.getSucursal(),
        cantidad_producto: this.cantidad
      }

      this.bodegaService.agregarProductoSucursal(bodega).subscribe(confirmacion=>{

        if (confirmacion) {
          swal({
            title: 'Se agrego correctamente el producto con código ' + this.codigoProducto,
            type: 'success',
            confirmButtonText: 'Continuar',
          }).then(()=>{
            window.location.reload();
          });
        } else {
          swal({
            title: 'No pudo agregarse el producto, intenta nuevamente',
            type: 'warning',
            confirmButtonText: 'Continuar',
          })
          
        }

      })

      

    }

  }

  validarCampos(){

 
    if(this.codigoProducto == 0 ){
      this.codigoProductoNull = true;
    }else{
      this.codigoProductoNull = false;
    }

    if(this.cantidad == 0 || !Number.isInteger(this.cantidad)){
      this.cantidadNull = true;
    }else{
      this.cantidadNull = false;
    }

  }




}
