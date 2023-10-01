import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { BodegaService } from '../service/bodega.service';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent {


  nombre:String;
  codigo:any;
  precio:Number;
  descripcion:String;

  nombreNull = false;
  descripcionNull = false;
  precioNull = false;
  
  @ViewChild('codigoProducto') codigoProducto: ElementRef;
  @ViewChild('nombreProducto') nombreProducto: ElementRef;
  @ViewChild('descripcionProducto') descripcionProducto: ElementRef;
  @ViewChild('precioProducto') precioProducto: ElementRef;

  constructor(private bodegaService:BodegaService,private router:Router,private login:LoginService){
    this.codigo = this.router.getCurrentNavigation()?.extras;
  }


  ngAfterViewInit(){
    
    this.bodegaService.obtenerProducto(this.codigo,).subscribe(prod=>{
      
      this.codigoProducto.nativeElement.textContent = prod.codigo;
      this.nombreProducto.nativeElement.value = prod.nombre;
      this.descripcionProducto.nativeElement.value = prod.descripcion;
      this.precioProducto.nativeElement.value = prod.precio;
      
    })
   

  }

  editarProducto(){
    
    this.validarCampos();
    
    if(this.nombreNull==false && this.descripcionNull==false && this.precioNull==false){

      let codigoPro = this.codigoProducto.nativeElement.textContent;
      let nombre = this.nombreProducto.nativeElement.value;
      let descripcion = this.descripcionProducto.nativeElement.value;
      let precio = this.precioProducto.nativeElement.value;

      this.bodegaService.actualizarProducto(codigoPro,nombre,descripcion,precio).subscribe((confirmacion)=>{
        
        if (confirmacion) {
          swal({
            title: 'Se actualizo correctamente el producto',
            type: 'success',
            confirmButtonText: 'Continuar',
          }).then(()=>{
            this.router.navigate(['bodega']);
          });
        } else {
          swal({
            title: 'Cliente',
            text: 'No se pudo actualizar el producto, intenta nuevamente',
            type: 'warning',
            confirmButtonText: 'Continuar',
          }).then( ()=>{
            this.router.navigate(['bodega']);
          }
          )
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
    
    if(this.precio <= 0){
      this.precioNull = true;
    }else{
      this.precioNull = false;
    }

  }


}
