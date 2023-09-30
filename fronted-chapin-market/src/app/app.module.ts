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
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { ClientesTopComponent } from './administrador/clientes/clientes-top/clientes-top.component';
import { ArticulosTopComponent } from './administrador/articulos/articulos-top/articulos-top.component';
import { SucursalesTopComponent } from './administrador/sucursales/sucursales-top/sucursales-top.component';
import { ReporteVentasComponent } from './administrador/ventas/reporte-ventas/reporte-ventas.component';
import { ReporteDescuentosComponent } from './administrador/descuentos/reporte-descuentos/reporte-descuentos.component';
import { DescuentosFechasComponent } from './administrador/descuentos/descuentos-fechas/descuentos-fechas.component';
import { VentasFechasComponent } from './administrador/ventas/ventas-fechas/ventas-fechas.component';
import { FormsModule } from '@angular/forms';
import { CrearEmpleadoComponent } from './administrador/empleados/crear-empleado/crear-empleado.component';
import { AdministradorGuard } from './login/guard/administrador.guard';
import { BodegaGuard } from './login/guard/bodega.guard';
import { InventarioGuard } from './login/guard/inventario.guard';
import { CajeroGuard } from './login/guard/cajero.guard';
import { DashboardComponentAdmin } from './administrador/dashboard/dashboard.component';
import { NavegacionComponentAdmin } from './administrador/navegacion/navegacion.component';
import { DashboardComponentCajero } from './cajero/dashboard/dashboard.component';
import { NavegacionComponentCajero } from './cajero/navegacion/navegacion.component';
import { NavegacionComponentInventario } from './sucursal/inventario/navegacion/navegacion.component';
import { DashboardComponentInventario } from './sucursal/inventario/dashboard/dashboard.component';
import { NavegacionComponentBodega } from './sucursal/bodega/navegacion/navegacion.component';
import { DashboardComponentBodega } from './sucursal/bodega/dashboard/dashboard.component';
import { MostrarProductosInventarioComponent } from './sucursal/inventario/mostrar-productos-inventario/mostrar-productos.component';
import { MostarProductosBodegaComponent } from './sucursal/bodega/mostar-productos-bodega/mostar-productos.component';
import { ValidarComponent } from './cajero/venta/validar-cliente/validar/validar.component';
import { CrearClienteComponent } from './cajero/venta/crear-cliente/crear-cliente.component';
import { CrearVentaComponent } from './cajero/venta/crear-venta/crear-venta.component';
import { EditarProductoComponent } from './sucursal/bodega/productos/editar-producto/editar-producto.component';
import { AgregarProductoComponent } from './sucursal/bodega/productos/agregar-producto/agregar-producto.component';
import { ProductosDisponiblesAgregarComponent } from './sucursal/bodega/productos/productos-disponibles-agregar/productos-disponibles-agregar.component';

const routes: Route[] = [
  { path: "", redirectTo:'login',pathMatch:'full'},
  { path: "login", component:LoginComponent},
  { path: "administracion", component:AdministracionComponent,canActivate:[AdministradorGuard]},
  { path: "bodega", component:BodegaComponent,canActivate:[BodegaGuard]},
  { path: "inventario", component:InventarioComponent,canActivate:[InventarioGuard]},
  { path: "cajero", component:VentaComponent,canActivate:[CajeroGuard]},
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
    DashboardComponentAdmin,
    NavegacionComponentAdmin,
    DashboardComponentCajero,
    NavegacionComponentCajero,
    ClientesTopComponent,
    ArticulosTopComponent,
    SucursalesTopComponent,
    ReporteVentasComponent,
    ReporteDescuentosComponent,
    DescuentosFechasComponent,
    VentasFechasComponent,
    CrearEmpleadoComponent,
    NavegacionComponentInventario,
    DashboardComponentInventario,
    NavegacionComponentBodega,
    DashboardComponentBodega,
    MostrarProductosInventarioComponent,
    MostarProductosBodegaComponent,
    ValidarComponent,
    CrearClienteComponent,
    CrearVentaComponent,
    EditarProductoComponent,
    AgregarProductoComponent,
    ProductosDisponiblesAgregarComponent
    
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
    MatMenuModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
