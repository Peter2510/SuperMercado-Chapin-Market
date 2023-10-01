import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { Bodega } from '../../Bodega';
import { Producto } from '../../Producto';
import { BodegaService } from '../service/bodega.service';

@Component({
  selector: 'app-editar-stock',
  templateUrl: './editar-stock.component.html',
  styleUrls: ['./editar-stock.component.css']
})
export class EditarStockComponent{

  nombre:String;
  codigo:any;
  cantidad:Number;
  cantidadNull = false;
  
  @ViewChild('codigoProducto') codigoProducto: ElementRef;
  @ViewChild('nombreProducto') nombreProducto: ElementRef;
  @ViewChild('cantidadProducto') cantidadProducto: ElementRef;

  constructor(private bodegaService:BodegaService,private router:Router,private login:LoginService){
    this.codigo = this.router.getCurrentNavigation()?.extras;
  }


  ngAfterViewInit(){
    
    this.bodegaService.obtenerProductoBodega(this.codigo,this.login.getSucursal()).subscribe(prod=>{
      
      this.codigoProducto.nativeElement.textContent = prod.codigo_producto;
      this.nombreProducto.nativeElement.textContent = prod.nombre_producto;
      this.cantidadProducto.nativeElement.value = prod.cantidad_producto;
      
    })
   

  }

  editarStockProducto(){
    
    this.cantidad = parseInt(this.cantidadProducto.nativeElement.value, 10);
    
    if(this.cantidad>0 && Number.isInteger(this.cantidad)){

      let codigoPro = this.codigoProducto.nativeElement.textContent;
      let sucursalCod = this.login.getSucursal()+"";

      this.bodegaService.actualizarStock(codigoPro,sucursalCod,this.cantidad).subscribe((confirmacion)=>{
        
        if (confirmacion) {
          swal({
            title: 'Se actualizo correctamente el stock',
            type: 'success',
            confirmButtonText: 'Continuar',
          }).then(()=>{
            this.router.navigate(['bodega']);
          });
        } else {
          swal({
            title: 'Cliente',
            text: 'No se pudo actualizar el stock, intenta nuevamente',
            type: 'warning',
            confirmButtonText: 'Continuar',
          }).then( ()=>{
            this.router.navigate(['bodega']);
          }
          )
        }

      })


    }else{
      this.cantidadNull=true;
    }


  }



}
