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
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './administrador/dashboard/dashboard.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { NavegacionComponent } from './administrador/navegacion/navegacion.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu'

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
    ReporteVentasComponent,
    DashboardComponent,
    NavegacionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
