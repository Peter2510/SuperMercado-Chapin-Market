
--Creando la base de datos
CREATE DATABASE chapin_market;

\c chapin_market;

--Esquema sucursales
CREATE SCHEMA sucursales;
--Tabla sucursal
CREATE TABLE sucursales.sucursal(
    codigo SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(codigo)
);

--Esquema productos
CREATE SCHEMA productos;
--Tabla producto
CREATE TABLE productos.producto(
    codigo SERIAL NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(7,2) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(codigo)
);

--Tabla inventario
CREATE TABLE sucursales.inventario(
    numero_estante INT NOT NULL,
    codigo_producto SERIAL NOT NULL,
    codigo_sucursal SERIAL NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    FOREIGN KEY (codigo_producto) REFERENCES productos.producto(codigo),
    PRIMARY KEY(codigo_sucursal,codigo_producto)
);

--Tabla bodega
CREATE TABLE sucursales.bodega(
    codigo SERIAL NOT NULL,
    codigo_producto SERIAL NOT NULL,
    cantidad_producto INT NOT NULL,
    codigo_sucursal SERIAL NOT NULL,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    FOREIGN KEY (codigo_producto) REFERENCES productos.producto(codigo),
    PRIMARY KEY(codigo_producto,cantidad_producto)
);

--Esquema empleados
CREATE SCHEMA empleados;
--Tabla empleado
CREATE TABLE empleados.empleado(
    codigo SERIAL NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    rol INT NOT NULL,
    codigo_sucursal SERIAL NOT NULL,
    contrasenia VARCHAR(50) NOT NULL,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    PRIMARY KEY(codigo)
);

--Tabla caja
CREATE TABLE sucursales.caja(
    numero_caja INT NOT NULL,
    codigo_empleado SERIAL NOT NULL,
    FOREIGN KEY (codigo_empleado) REFERENCES empleados.empleado(codigo),
    PRIMARY KEY(numero_caja,codigo_empleado)
);

--Esquema clientes
CREATE SCHEMA clientes;
--Tabla cliente
CREATE TABLE clientes.cliente(
    nombre VARCHAR(120) NOT NULL,
    puntos INT NOT NULL,
    nit INT NOT NULL,
    compras DECIMAL(11,2) NOT NULL,
    tarjeta INT NOT NULL,
    PRIMARY KEY(nit)
);

SET DATESTYLE TO 'European';
--Esquema ventas
CREATE SCHEMA ventas;
--Tabla de ventas o compras
CREATE TABLE ventas.venta(
   codigo SERIAL NOT NULL,
   codigo_cajero SERIAL NOT NULL,
   nit_cliente INT NOT NULL,
   total_venta DECIMAL(11, 2) NOT NULL,
   fecha_venta DATE NOT NULL,  
   codigo_sucursal SERIAL NOT NULL,
   FOREIGN KEY (codigo_cajero) REFERENCES empleados.empleado(codigo),
   FOREIGN KEY (nit_cliente) REFERENCES clientes.cliente(nit),
   FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
   PRIMARY KEY(codigo)
);
--Tabla detalles venta
CREATE TABLE ventas.detalle_venta(
   codigo SERIAL NOT NULL,
   codigo_venta SERIAL NOT NULL,
   subtotal DECIMAL(10, 2) NOT NULL,
   cantidad INT NOT NULL,
   precio_unitario DECIMAL(10, 2) NOT NULL,
   codigo_producto SERIAL NOT NULL,
   FOREIGN KEY (codigo_venta) REFERENCES ventas.venta(codigo),
   FOREIGN KEY (codigo_producto) REFERENCES productos.producto(codigo),
   PRIMARY KEY(codigo)
);

SET DATESTYLE TO 'European';
--Tabla detalles venta
CREATE TABLE ventas.descuento(
   codigo_venta SERIAL NOT NULL,
   total DECIMAL(10, 2) NOT NULL,
   FOREIGN KEY (codigo_venta) REFERENCES ventas.venta(codigo),
   PRIMARY KEY(codigo_venta)
);

--Crear Rol
CREATE ROLE adminDataBase;
--Permiso para poder conectarse a la base de datos
GRANT CONNECT ON DATABASE  chapin_market TO adminDataBase;
--Permiso para usar los esquemas 
GRANT USAGE ON SCHEMA sucursales TO adminDataBase;
GRANT USAGE ON SCHEMA clientes TO adminDataBase;
GRANT USAGE ON SCHEMA empleados TO adminDataBase;
GRANT USAGE ON SCHEMA ventas TO adminDataBase;
GRANT USAGE ON SCHEMA productos TO adminDataBase;

--Permiso para poder ejecutar comandos
GRANT SELECT ON TABLE sucursales.sucursal TO adminDataBase;
GRANT INSERT ON TABLE sucursales.sucursal TO adminDataBase;
GRANT UPDATE ON TABLE sucursales.sucursal TO adminDataBase;
GRANT DELETE ON TABLE sucursales.sucursal TO adminDataBase;

GRANT SELECT ON TABLE sucursales.inventario TO adminDataBase;
GRANT INSERT ON TABLE sucursales.inventario TO adminDataBase;
GRANT UPDATE ON TABLE sucursales.inventario TO adminDataBase;
GRANT DELETE ON TABLE sucursales.inventario TO adminDataBase;

GRANT SELECT ON TABLE sucursales.bodega TO adminDataBase;
GRANT INSERT ON TABLE sucursales.bodega TO adminDataBase;
GRANT UPDATE ON TABLE sucursales.bodega TO adminDataBase;
GRANT DELETE ON TABLE sucursales.bodega TO adminDataBase;

GRANT SELECT ON TABLE sucursales.caja TO adminDataBase;
GRANT INSERT ON TABLE sucursales.caja TO adminDataBase;
GRANT UPDATE ON TABLE sucursales.caja TO adminDataBase;
GRANT DELETE ON TABLE sucursales.caja TO adminDataBase;

GRANT SELECT ON TABLE clientes.cliente TO adminDataBase;
GRANT INSERT ON TABLE clientes.cliente TO adminDataBase;
GRANT UPDATE ON TABLE clientes.cliente TO adminDataBase;
GRANT DELETE ON TABLE clientes.cliente TO adminDataBase;

GRANT SELECT ON TABLE empleados.empleado TO adminDataBase;
GRANT INSERT ON TABLE empleados.empleado TO adminDataBase;
GRANT UPDATE ON TABLE empleados.empleado TO adminDataBase;
GRANT DELETE ON TABLE empleados.empleado TO adminDataBase;

GRANT SELECT ON TABLE productos.producto TO adminDataBase;
GRANT INSERT ON TABLE productos.producto TO adminDataBase;
GRANT UPDATE ON TABLE productos.producto TO adminDataBase;
GRANT DELETE ON TABLE productos.producto TO adminDataBase;

GRANT SELECT ON TABLE ventas.venta TO adminDataBase;
GRANT INSERT ON TABLE ventas.venta TO adminDataBase;
GRANT UPDATE ON TABLE ventas.venta TO adminDataBase;
GRANT DELETE ON TABLE ventas.venta TO adminDataBase;

GRANT SELECT ON TABLE ventas.detalle_venta TO adminDataBase;
GRANT INSERT ON TABLE ventas.detalle_venta TO adminDataBase;
GRANT UPDATE ON TABLE ventas.detalle_venta TO adminDataBase;
GRANT DELETE ON TABLE ventas.detalle_venta TO adminDataBase;

GRANT SELECT ON TABLE ventas.descuento TO adminDataBase;
GRANT INSERT ON TABLE ventas.descuento TO adminDataBase;
GRANT UPDATE ON TABLE ventas.descuento TO adminDataBase;
GRANT DELETE ON TABLE ventas.descuento TO adminDataBase;

-- Otorgar permiso para utilizar la secuencia SERIAL
GRANT USAGE ON SCHEMA sucursales TO adminDataBase;
GRANT USAGE ON SCHEMA empleados TO adminDataBase;
GRANT USAGE ON SCHEMA productos TO adminDataBase;
GRANT USAGE ON SCHEMA ventas TO adminDataBase;

-- Creando usuario 
CREATE USER admin_chapin WITH PASSWORD '1chapinMarket2';

--Asignando Rol
GRANT adminDataBase TO admin_chapin;


--Insertando las sucursales
INSERT INTO sucursales.sucursal (nombre) VALUES
('Central'),
('Norte'),
('Sur');

--Insertando cajeros
INSERT INTO empleados.empleado(nombre,rol,codigo_sucursal,contrasenia) VALUES
--cajeros
('Luis Alberto Villatoro Cano','1','1','123a'),
('Jose Carlos Lopez Mainz','1','1','123a'),
('Olga Estela Siguenza Lopez','1','1','123a'),
('Herson Daniel Hernandez Dante','1','1','123a'),
('Jessie Torres Marcos','1','1','123a'),
('Marta Patricia Ortega Arcos','1','1','123a'),
('Edgar Guillermo Lara Valencia','1','2','123a'),
('Monica Karina Tovar Solorzano','1','2','123a'),
('Jose Andres Cardenas Molina','1','2','123a'),
('Hilda Yohana Mendonza Navarro','1','2','123a'),
('Maria Fernanda Franco Valarez','1','2','123a'),
('Ana del Rocio Rios Rosales','1','2','123a'),
('Fernando Roberto Segovia Garcia','1','3','123a'),
('Cecilia Elizabeth Suarez Moreira','1','3','123a'),
('Ericka Paola Prado Ortiz','1','3','123a'),
('Xavier German Yanez Cruz','1','3','123a'),
('Pablo Efren Navarro Vera','1','3','123a'),
('Linda Azucena Luna Veliz','1','3','123a');

--Insertando bodega
INSERT INTO empleados.empleado(nombre,rol,codigo_sucursal,contrasenia) VALUES
('Luis Ernesto Gutierrez Lopez','2','1','123b'),
('Ernesto David  Lucero Gramajo','2','2','123b'),
('Fernanda Abigail Gonzalez Lima','2','3','123b');
--Insertando Inventario
INSERT INTO empleados.empleado(nombre,rol,codigo_sucursal,contrasenia) VALUES
('Emma Johnson', '3', '1','123c'),
('Liam Smith', '3', '1','123c'),
('Olivia Brown', '3', '1','123c'),
('Noah Davis', '3', '1','123c'),
('Ava Miller', '3', '2','123c'),
('Sophia Wilson', '3', '2','123c'),
('Isabella Moore', '3', '2','123c'),
('Mia Taylor', '3', '2','123c'),
('James Anderson', '3', '3','123c'),
('Oliver Jackson', '3', '3','123c'),
('Benjamin Clark', '3', '3','123c'),
('Lucas White', '3', '3','123c');
--Administrador
INSERT INTO empleados.empleado (nombre,rol,codigo_sucursal,contrasenia) VALUES
('Virginia Azucena Loarca Elizalde','4','1','123d');
