import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../login-service/login.service';

@Injectable({
  providedIn: 'root'
})
export class BodegaGuard implements CanActivate {
  constructor(private loginService:LoginService,private router:Router){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if(this.loginService.estaLoggeado()&& this.loginService.getRol()==2){
        return true;
      }

      this.router.navigate(['login']);
      return false;

  }
}
