import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { InventarioService } from '../service/inventario.service';

@Component({
  selector: 'app-editar-inventario',
  templateUrl: './editar-inventario.component.html',
  styleUrls: ['./editar-inventario.component.css']
})
export class EditarInventarioComponent {

  nombre:String;
  codigo:any;
  cantidadInventario:Number;
  cantidadBodega:Number;
  cantidad:Number = 0;
  cantidadNull = false;

  @ViewChild('codigoProducto') codigoProducto: ElementRef;
  @ViewChild('nombreProducto') nombreProducto: ElementRef;
  @ViewChild('cantidadProductoBodega') cantidadProductoBodegaElement: ElementRef;
  @ViewChild('cantidadProductoInventario') cantidadProductoInventarioElement: ElementRef;
  
  constructor(private inventarioService:InventarioService,private router:Router,private login:LoginService){
    const parametros = history.state;
    if (parametros) {
      this.cantidadBodega = parametros.bodega;
      this.codigo = parametros.codigo;
    }
  }

  ngAfterViewInit(){
    
    this.inventarioService.obtenerProductoInventario(this.codigo,this.login.getSucursal()).subscribe(prod=>{
      this.cantidadInventario = prod.cantidad_producto_inventario;
      this.codigoProducto.nativeElement.textContent = prod.codigo_producto;
      this.nombreProducto.nativeElement.textContent = prod.nombre_producto;
      this.cantidadProductoBodegaElement.nativeElement.textContent = this.cantidadBodega;
      this.cantidadProductoInventarioElement.nativeElement.textContent = prod.cantidad_producto_inventario;     
    })
   

  }

  editarInventarioProducto(){
        
    this.validarCampos();

    if(!this.cantidadNull){

      let nuevoInventario = Number(this.cantidad) + Number(this.cantidadInventario);

      if(this.cantidad > this.cantidadBodega){

        swal({
          title: 'La cantidad a agregar al inventario excede al disponible en la bodega ',
          text: 'La cantidad en bodega es de ' + this.cantidadBodega,
          type: 'error',
          confirmButtonText: 'Continuar',
        })

      }else{

        let codigoPro = this.codigoProducto.nativeElement.textContent;
        let codigoSuc = this.login.getSucursal();
        let nuevaCantidadInventario = nuevoInventario;
        let descartarBodega = this.cantidad;
  
        this.inventarioService.actualizarInventario(codigoPro,codigoSuc,nuevaCantidadInventario,descartarBodega).subscribe((confirmacion)=>{
          
          if (confirmacion) {
            swal({
              title: 'Se actualizo correctamente el inventario',
              type: 'success',
              confirmButtonText: 'Continuar',
            }).then(()=>{
              this.router.navigate(['inventario']);
            });
          } else {
            swal({
              title: 'Cliente',
              text: 'No se pudo actualizar el producto, intenta nuevamente',
              type: 'warning',
              confirmButtonText: 'Continuar',
            }).then( ()=>{
              this.router.navigate(['inventario']);
            }
            )
          }
  
        })

      }
      


    }




  }

  validarCampos(){
    
    if(this.cantidad == 0 || this.cantidad < 0 || Number.isInteger(this.cantidad)){
      this.cantidadNull = true;
    }else{
      this.cantidadNull = false; 
    }

  }



}
