import { Component } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import { Producto } from 'src/app/sucursal/bodega/Producto';
import swal from 'sweetalert2';
import { InventarioService } from '../service/inventario.service';

@Component({
  selector: 'app-disponibles-agregar-inventario',
  templateUrl: './disponibles-agregar-inventario.component.html',
  styleUrls: ['./disponibles-agregar-inventario.component.css']
})
export class DisponiblesAgregarInventarioComponent {

  cantidad: Number = 0;
  codigoProducto:Number = 0;
  estante:Number = 0;
  pasillo:Number = 0;

  productos:Producto[];
  
  cantidadNull = false;
  codigoProductoNull = false;
  estanteNull = false;
  pasilloNull = false;

  constructor(private inventarioService:InventarioService,private login:LoginService){}

  ngOnInit(): void {
    
    let nSucural:Number = this.login.getSucursal();
    let sucursal:number = nSucural.valueOf();

    this.inventarioService.productoDisponiblesParaAgregar(sucursal).subscribe(productos=>{
      this.productos = productos;
    })
  }

  public agregarProducto(){

    this.validarCampos();

    if(!this.cantidadNull && !this.codigoProductoNull){

      let inventario = {
        codigo_producto:this.codigoProducto,
        codigo_sucursal : this.login.getSucursal(),
        cantidad_producto_inventario: this.cantidad,
        numero_pasillo: this.pasillo,
        numero_estante:this.estante

      }


      //validar la cantidad en bodega 

      this.inventarioService.cantidadProductoBodega(this.codigoProducto,this.login.getSucursal()).subscribe(resultado=>{

        let cantidadEnBodega = resultado;

        if(this.cantidad>cantidadEnBodega){

          swal({
            title: 'La cantidad a agregar al inventario excede al disponible en la bodega ',
            text: 'La cantidad en bodega es de ' + cantidadEnBodega,
            type: 'error',
            confirmButtonText: 'Continuar',
          })
          

        }else{

          this.inventarioService.agregarProductoInventario(inventario).subscribe(confirmacion=>{

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
                title: 'No pudo agregarse el producto, el estante ' + this.estante +' del pasillo ' + this.pasillo+ ' esta en uso',
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

    if(this.pasillo == 0 || !Number.isInteger(this.pasillo)){
      this.pasilloNull = true;
    }else{
      this.pasilloNull = false;
    }

    if(this.estante == 0 || !Number.isInteger(this.estante)){
      this.estanteNull = true;
    }else{
      this.estanteNull = false;
    }

  }

}
