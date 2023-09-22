import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmpleadosComponent } from './administrador/empleados/empleados.component';
import { LoginComponent } from './login/login/login.component';
import { Route, RouterModule } from '@angular/router';
import { AdministracionComponent } from './administrador/administracion/administracion.component';
import { VentaComponent } from './cajero/venta/venta.component';
import { InventarioComponent } from './sucursal/inventario/inventario/inventario.component';
import { BodegaComponent } from './sucursal/bodega/bodega/bodega.component';
import { ReporteVentasComponent } from './administrador/reporteVentas/reporte-ventas/reporte-ventas.component';
import {HttpClientModule} from '@angular/common/http'

const routes: Route[] = [
  { path: "", redirectTo:'login',pathMatch:'full' },
  { path: "login", component:LoginComponent },
  { path: "administracion", component:AdministracionComponent },
  { path: "bodega", component:BodegaComponent },
  { path: "inventario", component:InventarioComponent },
  { path: "cajero", component:VentaComponent },
  { path: "**", redirectTo: "login" }
  
];

@NgModule({
  declarations: [
    AppComponent,
    EmpleadosComponent,
    LoginComponent,
    AdministracionComponent,
    VentaComponent,
    InventarioComponent,
    BodegaComponent,
    ReporteVentasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
