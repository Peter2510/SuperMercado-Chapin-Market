import { Component, Input } from '@angular/core';
import { LoginService } from 'src/app/login/login-service/login.service';
import { Producto } from 'src/app/sucursal/bodega/Producto';
import { BodegaService } from 'src/app/sucursal/bodega/productos/service/bodega.service';
import { Inventario } from 'src/app/sucursal/inventario/Inventario';
import { InventarioService } from 'src/app/sucursal/inventario/productos/service/inventario.service';
import { Cliente } from '../Cliente';
import { ProductoEspecifico } from '../ProductoEspecifico';
import { VentaService } from '../service/venta.service';

@Component({
  selector: 'app-crear-venta',
  templateUrl: './crear-venta.component.html',
  styleUrls: ['./crear-venta.component.css'],
})
export class CrearVentaComponent {
  
  @Input() nit: string;
  nombre: string;
  
  cliente: any = {
    nombre: '',
    puntos: 0,
    nit: '',
    compras: 0,
    tipo_tarjeta: 0,
  };

  cantidad = 0;
  codigo = 0;

  cantidadNull = false;
  codigoNull = false;
  
  productos: Producto[];
  productosAgregados: ProductoEspecifico[] = [];

  constructor(private ventaService: VentaService,private inventarioService:InventarioService,private login:LoginService,private bodega:BodegaService) {}

  ngOnInit() {

    //validadando cliente
    if (this.nit != null) {
      this.ventaService
        .validarCliente(this.nit)
        .toPromise()
        .then((clienteHallado) => {
          this.cliente = clienteHallado;
        });
    } else {
      this.cliente.nit = 'CF';
      this.cliente.nombre = 'Consumidor Final';
    }

  //mostar productos
   this.ventaService.ProductosVenta(this.login.getSucursal()).subscribe(productosInv=>{
    this.productos = productosInv;
  })


  }

  agregarProducto() {


    this.validarCampos()
    console.log(this.codigo)
    console.log(this.cantidad)

    


  }

  finalizarVenta() {
    

  
    
  }

  validarCampos(){
    
    if(this.cantidad == 0 || this.cantidad < 0 || !Number.isInteger(this.cantidad)){
      this.cantidadNull = true;
    }else{
      this.cantidadNull = false; 
    }

    if(this.codigo == 0){
      this.codigoNull = true;
    }else{
      this.codigoNull = false; 
    }    

  }
  
}
