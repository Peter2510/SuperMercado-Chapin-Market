import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { VentaService } from '../service/venta.service';

@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.css'],
})
export class CrearClienteComponent {
  nombre: String = "";
  tarjetaPuntos: boolean = false;
  @Input() nit: string;
  nombreNull = false;
  nitNull = false;

  constructor(private ventaService: VentaService) {}

  crearCliente() {
    this.validarCampos();

    let nitValor: String = new String(this.nit);

    if (!this.nombreNull && !this.nitNull) {
      // Utiliza this.nitValor en lugar de this.nit
      if (nitValor.length >= 9 && nitValor.length <= 10) {
        //crear el cliente
       
        let cliente = {
          nombre: this.nombre,
          puntos: 0,
          nit: nitValor,
          compras: 0,
          tipo_tarjeta: 0,
        };

        if(this.tarjetaPuntos){
          cliente.tipo_tarjeta = 1;
        }

        console.log(cliente);

        this.ventaService.crearCliente(cliente).subscribe((confirmacion) => {
          if (confirmacion) {
            swal({
              title: 'Se agrego correctamente el cliente',
              type: 'success',
              confirmButtonText: 'Continuar',
            });
          } else {
            swal({
              title: 'Cliente',
              text: 'El cliente ya se encuentra registrado',
              type: 'warning',
              confirmButtonText: 'Continuar',
            }).then( ()=>{
              window.location.reload();
            }
            )
          }
        });
      } else {
        swal(
          'Nit inválido',
          'El nit debe contener entre 9 y 10 dígitos',
          'error'
        );
      }
    }
  }

  validarCampos() {
    if (this.nombre == "") {
      this.nombreNull = true;
    } else {
      this.nombreNull = false;
    }
    if (this.nit == null || parseInt(this.nit)<0) {
      this.nitNull = true;
    } else {
      this.nitNull = false;
    }
  }
}
