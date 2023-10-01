import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/login-service/login.service';
import swal from 'sweetalert2';
import { Cliente } from '../../Cliente';
import { VentaService } from '../../service/venta.service';

@Component({
  selector: 'app-validar-cliente',
  templateUrl: './validar.component.html',
  styleUrls: ['./validar.component.css'],
})
export class ValidarComponent {
  cliente: Cliente;
  nit: string;
  sucursal: string = this.loginService.getSucursal();
  crearCliente = false;
  crearVenta = false;
  validarNit = true;

  constructor(
    private ventaService: VentaService,
    private loginService: LoginService,
  ) {}

  public validarCliente() {

    if (this.nit == null || this.nit == 'cf' || this.nit == '' || parseInt(this.nit)<0) {
      
      //GENERAR VENTA
      this.crearVenta=true;
      this.crearCliente = false;
      this.validarNit=false;

    } else {
      
        this.ventaService.validarCliente(this.nit).subscribe((clienteH) => {
        this.cliente = clienteH;
  
        if (this.cliente.nombre != null || this.cliente.nombre != undefined) {
          
          //GENERAR VENTA
          this.crearVenta=true;
          this.crearCliente = false;
          this.validarNit=false;
          

        } else {
          
          swal({
            title: 'Cliente',
            text: 'El cliente no se encuentra registrado, desea registrarlo',
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Crear',
            cancelButtonText:'No',
            
          }).then((result) => {
            if (result.value) {
             
                //CREAR CLIENTE
                this.crearCliente= true;
                this.crearVenta = false;
                this.validarNit=false;
              
            }
          })
        }
      });
    }
  }
}
