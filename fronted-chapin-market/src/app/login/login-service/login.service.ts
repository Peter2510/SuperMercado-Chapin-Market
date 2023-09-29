import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  private baseURL = "http://localhost:5400/chapinMarket";

  constructor(private http:HttpClient) { }

  public loginStatusSubject = new Subject<boolean>;

  public verificarCredenciales(loginData: any){

    return this.http.post(`${this.baseURL}/login`,loginData);

  }

  public sesion(usuario:any){
    //Guarda el objeto retornado en el local storage
    localStorage.setItem('usuario',JSON.stringify(usuario));
  }

  public estaLoggeado(){
    let userStorage = localStorage.getItem('usuario');

    if(userStorage==undefined || userStorage == '' || userStorage == null){
      return false;
    }else{
      return true;
    }

  }

  public logOut(){
    localStorage.removeItem('usuario');
    return true;
  }

  public getUsuario(){
    let userStorage = localStorage.getItem('usuario');
    if(userStorage!=null){
      return JSON.parse(userStorage);
    }else{
      this.logOut();
      return null;
    }
  }

  public getRol(){
    let userStorage = this.getUsuario();
    if(userStorage!=null){
      return userStorage.codigo_rol;
    }else{
      this.logOut();
      return null;
    }
  }

  public getSucursal(){
    let userStorage = this.getUsuario();
    if(userStorage!=null){
      return userStorage.codigo_sucursal;
    }else{
      this.logOut();
      return null;
    }
  }
  
  
  public getCodigo(){
    let userStorage = this.getUsuario();
    if(userStorage!=null){
      return userStorage.codigo;
    }else{
      this.logOut();
      return null;
    }
  }

  public getCaja(){
    let userStorage = this.getUsuario();
    if(userStorage!=null){
      return userStorage.caja;
    }else{
      this.logOut();
      return null;
    }
  }

  public getNombre(){
    let userStorage = this.getUsuario();
    if(userStorage!=null){
      return userStorage.nombre;
    }else{
      this.logOut();
      return null;
    }
  }


}
