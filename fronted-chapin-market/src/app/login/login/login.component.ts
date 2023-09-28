import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { LoginService } from '../login-service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginData = {
    codigo: '',
    contrasenia: '',
  };

  codigoNull = false;
  contraseniaNull = false;
  credencialesIncorrectas = true;

  constructor(private loginService: LoginService,private router:Router) {
    this.loginService.logOut();
  }

  login() {
    
    this.validarCredenciales();
    if (!this.codigoNull && !this.contraseniaNull) {
      //validar si existe el empleado
      this.loginService.verificarCredenciales(this.loginData).subscribe(
        (data: any) => {
          this.loginService.sesion(data);

          if (
            this.loginService.getUsuario().codigo != 0 &&
            this.loginService.getUsuario().codigo_rol != 0
          ) {
            switch (this.loginService.getRol()) {
              //cajero
              case 1:
                this.router.navigate(['cajero']);
                this.loginService.loginStatusSubject.next(true);
                break;
              //bodega
              case 2:
                this.router.navigate(['bodega']);
                this.loginService.loginStatusSubject.next(true);
                break;
              //Inventario
              case 3:
                this.router.navigate(['inventario']);
                this.loginService.loginStatusSubject.next(true);
                break;
              //Adminstrador
              case 4:
                this.router.navigate(['administracion']);
                this.loginService.loginStatusSubject.next(true);
                break;
            }
          } else {
            this.credencialesIncorrectas = false;
          }
        },
        (error) => {
          // console.log(error);
        }
      );
    }
  }

  validarCredenciales() {
    if (this.loginData.codigo == '') {
      this.codigoNull = true;
    } else {
      this.codigoNull = false;
    }

    if (this.loginData.contrasenia == '') {
      this.contraseniaNull = true;
    } else {
      this.contraseniaNull = false;
    }
  }
}
