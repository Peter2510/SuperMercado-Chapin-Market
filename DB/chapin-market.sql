--Creando la base de datos
CREATE DATABASE chapin_market;

\c chapin_market;

--Esquema sucursales
CREATE SCHEMA sucursales;
--Tabla sucursal
CREATE TABLE sucursales.sucursal(
    codigo SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(codigo)
);
--Tabla inventario
CREATE TABLE sucursales.inventario(
    sucursal SERIAL NOT NULL,
    FOREIGN KEY (sucursal) REFERENCES sucursales.sucursal(codigo),
    PRIMARY KEY(sucursal)
);

--Tabla bodega
CREATE TABLE sucursales.bodega(
    codigo SERIAL NOT NULL,
    sucursal SERIAL NOT NULL,
    FOREIGN KEY (sucursal) REFERENCES sucursales.sucursal(codigo),
    PRIMARY KEY(codigo)
);

--Esquema clientes
CREATE SCHEMA clientes;
--Tabla cliente
CREATE TABLE clientes.cliente(
    nombre VARCHAR(120) NOT NULL,
    puntos INT NOT NULL,
    nit VARCHAR(12) NOT NULL,
    PRIMARY KEY(nit)
);

--Esquema empleados
CREATE SCHEMA empleados;
--Tabla empleado
CREATE TABLE empleados.empleado(
    nombre VARCHAR(120) NOT NULL,
    codigo SERIAL NOT NULL,
    rol VARCHAR(2) NOT NULL,
    sucursal SERIAL NOT NULL,
    FOREIGN KEY (sucursal) REFERENCES sucursales.sucursal(codigo),
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

SET DATESTYLE TO 'European';

--Esquema ventas
CREATE SCHEMA ventas;
--Tabla de ventas o compras
CREATE TABLE ventas.venta(
   codigo SERIAL NOT NULL,
   detalle_venta SERIAL NOT NULL,
   codigo_empleado SERIAL REFERENCES empleados.empleado(codigo),
   nit_cliente VARCHAR(12) REFERENCES clientes.cliente(nit),
   total_venta DECIMAL(10, 2) NOT NULL,
   fecha_venta DATE NOT NULL,  
   codigo_sucursal SERIAL NOT NULL REFERENCES sucursales.sucursal(codigo),
   PRIMARY KEY(codigo)
);
--Tabla detalles venta
CREATE TABLE ventas.detalle_venta(
   codigo SERIAL NOT NULL,
   codigo_venta SERIAL NOT NULL REFERENCES ventas.venta(codigo),
   codigo_producto SERIAL NOT NULL REFERENCES productos.producto(codigo),
   precio_unitario DECIMAL(10, 2) NOT NULL,
   cantidad INT NOT NULL,
   subtotal DECIMAL(10, 2) NOT NULL,
   PRIMARY KEY(codigo)
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


-- Otorgar permiso para utilizar la secuencia SERIAL
GRANT USAGE ON SEQUENCE sucursales.sucursal_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE sucursales.inventario_sucursal_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE sucursales.bodega_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE sucursales.sucursal_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE empleados.empleado_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE empleados.empleado_sucursal_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE productos.producto_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.venta_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.venta_detalle_venta_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.venta_codigo_sucursal_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.detalle_venta_codigo_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.detalle_venta_codigo_venta_seq TO adminDataBase;
GRANT USAGE ON SEQUENCE ventas.detalle_venta_codigo_producto_seq TO adminDataBase;

-- Creando usuario 
CREATE USER db_user_chapin WITH PASSWORD '1chapinMarket2';

--Asignando Rol
GRANT adminDataBase TO db_user_chapin;



--Insertando las sucursales
INSERT INTO sucursales.sucursal (nombre) VALUES
('Central'),
('Norte'),
('Sur');

--Insertando cajeros
INSERT INTO empleados.empleado (nombre,rol,sucursal) VALUES
('Luis Alberto Villatoro Cano','1','1'),
('Jose Carlos Lopez Mainz','1','1'),
('Olga Estela Siguenza Lopez ','1','1'),
('Herson Daniel Hernandez Dante','1','1'),
('Jessie Torres Marcos','1','1'),
('Marta Patricia Ortega Arcos','1','1'),
('Edgar Guillermo Lara Valencia','1','2'),
('Monica Karina Tovar Solorzano','1','2'),
('Jose Andres Cardenas Molina','1','2'),
('Hilda Yohana Mendonza Navarro','1','2'),
('Maria Fernanda Franco Valarez','1','2'),
('Ana del Rocio Rios Rosales','1','2'),
('Fernando Roberto Segovia Garcia','1','3'),
('Cecilia Elizabeth Suarez Moreira','1','3'),
('Ericka Paola Prado Ortiz','1','3'),
('Xavier German Yanez Cruz','1','3'),
('Pablo Efren Navarro Vera','1','3'),
('Linda Azucena Luna Veliz','1','3');

--Insertando bodega
--Insertando Inventario
--Administrador
INSERT INTO empleados.empleado (nombre,rol,sucursal) VALUES
('Virginia Azucena Loarca Elizalde','4','1');

