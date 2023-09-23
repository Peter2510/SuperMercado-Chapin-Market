import { Component } from '@angular/core';

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})
export class CrearEmpleadoComponent {

  tipoEmpleado:Number = 0;
  nombre:String;
  rol:Number;
  sucursal:Number;
  contrasenia:String;
  caja:Number;
  
  nombreNull = false;
  rolNull = false;
  sucursalNull = false;
  constraseniaNull = false;
  cajaNull = false;
  

  cambiarTipoEmpleado(event: any) {
    this.tipoEmpleado = event.target.value;
  }

  crearEmpleado(){
   
    this.validarCampos();

    if(this.nombreNull==false && this.rolNull==false && this.sucursalNull==false && this.constraseniaNull==false && this.cajaNull==false ){
        console.log("Crear empleado");
    }

  }

  validarCampos(){

    if(this.nombre == null){
      this.nombreNull = true;
    }else{
      this.nombreNull = false;
    }

    if(this.rol == null){  
      this.rolNull = true;
    }else{
      this.rolNull = false;
    }

    if(this.sucursal == null){
      this.sucursalNull = true;
    }else{
      this.sucursalNull = false;
    }

    if(this.contrasenia == null){
      this.constraseniaNull = true;
    }else{
      this.constraseniaNull = false;
    }

    if(this.tipoEmpleado == 1 && this.caja == null){
      this.cajaNull = true;
    }else{
      this.cajaNull = false;
    }

  }


}
