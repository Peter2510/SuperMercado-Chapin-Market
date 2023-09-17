--Creando la base de datos
CREATE DATABASE chapin-market;

\c musicales;

--Esquema sucursales
CREATE SCHEMA sucursales;
--Tabla sucursal
CREATE TABLE sucursales.sucursal(
    Id SERIAL NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
);

--Esquema bodegas
CREATE SCHEMA bodegas;
--Tabla bodega
CREATE TABLE bodegas.bodega(
    Id SERIAL NOT NULL,
    Sucursal SERIAL NOT NULL,
    FOREIGN KEY (Sucursal) REFERENCES sucursales.sucursal(Id),
    PRIMARY KEY(Id)
);

--Esquema clientes
CREATE SCHEMA clientes;
--Tabla cliente
CREATE TABLE clientes.cliente(
    Nombre VARCHAR(120) NOT NULL,
    Puntos INT NOT NULL,
    Nit VARCHAR(12) NOT NULL,
    monto_compras DECIMAL(8,2) NOT NULL,
    Compra SERIAL NOT NULL,
    FOREIGN KEY (Compra) REFERENCES ventas.venta(Numero),
    PRIMARY KEY(Nit)
);

--Esquema empleados
CREATE SCHEMA empleados;
--Tabla empleado
CREATE TABLE empleados.empleado(
    Nombre VARCHAR(120) NOT NULL,
    Codigo SERIAL NOT NULL,
    Rol VARCHAR(2) NOT NULL,
    Sucursal SERIAL NOT NULL,
    FOREIGN KEY (Sucursal) REFERENCES sucursales.sucursal(Id),
    PRIMARY KEY(Codigo)
);


--Esquema ventas
CREATE SCHEMA ventas;

--Tabla de ventas o compras

--Esquema productos
CREATE SCHEMA productos;
--Tabla producto
CREATE TABLE productos.producto(
    codigo_producto SERIAL NOT NULL,
    Descripcion TEXT NOT NULL,
    Precio DECIMAL(7,2) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Unidades INT NOT NULL,    
    Marca VARCHAR(50) NOT NULL,
    PRIMARY KEY(codigo_producto)
);





