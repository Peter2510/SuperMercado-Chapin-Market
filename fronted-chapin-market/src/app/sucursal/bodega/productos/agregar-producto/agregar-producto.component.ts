import { Component } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { BodegaService } from '../service/bodega.service';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrls: ['./agregar-producto.component.css']
})
export class AgregarProductoComponent {

  nombre:string = "";
  descripcion:String="";
  precio:Number;
  cantidad:number;

  nombreNull = false;
  descripcionNull = false;
  precioNull = false;
  cantidadNull = false;
  
  constructor(private crearProductoService:BodegaService,private login:LoginService){ }

  public crearProducto(){

    this.validarCampos();

    if(this.nombreNull==false && this.descripcionNull==false && this.precioNull==false){
      
        this.crearProductoService.existeProducto(this.nombre).subscribe((confirmacion)=>{
          if (confirmacion) {
            swal({
              title: 'El producto ya existe ',
              type: 'warning',
              confirmButtonText: 'Continuar',
            });
          } else {
            
            let producto = {
              nombre: this.nombre,
              descripcion: this.descripcion,
              precio: this.precio
            };

            let bodega = {
              codigo_sucursal : this.login.getSucursal(),
              cantidad: this.cantidad
            }

            this.crearProductoService.crearProducto(producto,bodega).subscribe((confirmacion)=>{
              if (confirmacion) {
                swal({
                  title: 'Se agrego correctamente el producto',
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
        })
      
    }

  }

  validarCampos(){

    if(this.nombre == ""){
      this.nombreNull = true;
    }else{
      this.nombreNull = false;
    }

    if(this.descripcion == ""){
      this.descripcionNull = true;
    }else{
      this.descripcionNull = false;
    }
    
    if(this.precio == null){
      this.precioNull = true;
    }else{
      this.precioNull = false;
    }

    if(this.cantidad == null || !Number.isInteger(this.cantidad)){
      this.cantidadNull = true;
    }else{
      this.cantidadNull = false;
    }

  }
}
