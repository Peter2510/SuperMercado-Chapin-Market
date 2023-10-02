import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { LoginService } from 'src/app/login/login-service/login.service';

@Component({
  selector: 'app-navegacion-cajero',
  templateUrl: './navegacion.component.html',
  styleUrls: ['./navegacion.component.css']
})
export class NavegacionComponentCajero {

  opcion:Number = 1;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,public loginService:LoginService) {}

  public cerrarSesion(){
    this.loginService.logOut();
    window.location.reload();
  }

  public Validaropcion(opcionHallada: Number) {
    switch (opcionHallada) {
      case 1:
        this.opcion = 1;
        break;
      case 2:
        this.opcion = 2;
        break;
      case 3:
        this.opcion = 3;
        break;
    }
  }

}
