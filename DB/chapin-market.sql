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
    nombre VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY(codigo)
);

--Tabla inventario
CREATE TABLE sucursales.inventario(
    numero_estante INT NOT NULL,
    numero_pasillo INT NOT NULL,
    codigo_producto SERIAL NOT NULL,
    codigo_sucursal SERIAL NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    FOREIGN KEY (codigo_producto) REFERENCES productos.producto(codigo),
    PRIMARY KEY(codigo_sucursal,codigo_producto,numero_estante,numero_pasillo)
);

--Tabla bodega
CREATE TABLE sucursales.bodega(
    codigo SERIAL NOT NULL,
    codigo_producto SERIAL NOT NULL,
    cantidad_producto INT NOT NULL,
    codigo_sucursal SERIAL NOT NULL,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    FOREIGN KEY (codigo_producto) REFERENCES productos.producto(codigo),
    PRIMARY KEY(codigo_producto,codigo_sucursal)
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
    caja INT,
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursales.sucursal(codigo),
    PRIMARY KEY(codigo)
);

--Esquema clientes
CREATE SCHEMA clientes;
--Tabla cliente
CREATE TABLE clientes.cliente(
    nombre VARCHAR(120) NOT NULL,
    puntos INT NOT NULL,
    nit VARCHAR(10) NOT NULL,
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
   nit_cliente VARCHAR(10) NOT NULL,
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
   fecha_descuento DATE NOT NULL,  
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

-- Otorgar permiso para utilizar la secuencia SERIAL

GRANT USAGE ON SEQUENCE sucursales.sucursal_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE sucursales.sucursal_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE sucursales.sucursal_codigo_seq TO adminDataBase;


GRANT USAGE ON SEQUENCE  productos.producto_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE productos.producto_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE productos.producto_codigo_seq TO adminDataBase;


GRANT USAGE ON SEQUENCE  sucursales.bodega_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE sucursales.bodega_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE sucursales.bodega_codigo_seq TO adminDataBase;


GRANT USAGE ON SEQUENCE  empleados.empleado_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE empleados.empleado_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE empleados.empleado_codigo_seq TO adminDataBase;


GRANT USAGE ON SEQUENCE  ventas.venta_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE ventas.venta_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE ventas.venta_codigo_seq TO adminDataBase;

GRANT USAGE ON SEQUENCE  ventas.detalle_venta_codigo_seq TO adminDataBase;
GRANT SELECT ON SEQUENCE ventas.detalle_venta_codigo_seq TO adminDataBase;
GRANT UPDATE ON SEQUENCE ventas.detalle_venta_codigo_seq TO adminDataBase;


--Insertando las sucursales
INSERT INTO sucursales.sucursal (nombre) VALUES
('Central'),
('Norte'),
('Sur');

--Insertando cajeros
INSERT INTO empleados.empleado(nombre,rol,codigo_sucursal,contrasenia,caja) VALUES
--cajeros
('Luis Alberto Villatoro Cano','1','1','123a',1),
('Jose Carlos Lopez Mainz','1','1','123a',2),
('Olga Estela Siguenza Lopez','1','1','123a',3),
('Herson Daniel Hernandez Dante','1','1','123a',4),
('Jessie Torres Marcos','1','1','123a',5),
('Marta Patricia Ortega Arcos','1','1','123a',6),
('Edgar Guillermo Lara Valencia','1','2','123a',1),
('Monica Karina Tovar Solorzano','1','2','123a',2),
('Jose Andres Cardenas Molina','1','2','123a',3),
('Hilda Yohana Mendonza Navarro','1','2','123a',4),
('Maria Fernanda Franco Valarez','1','2','123a',5),
('Ana del Rocio Rios Rosales','1','2','123a',6),
('Fernando Roberto Segovia Garcia','1','3','123a',1),
('Cecilia Elizabeth Suarez Moreira','1','3','123a',2),
('Ericka Paola Prado Ortiz','1','3','123a',3),
('Xavier German Yanez Cruz','1','3','123a',4),
('Pablo Efren Navarro Vera','1','3','123a',5),
('Linda Azucena Luna Veliz','1','3','123a',6);

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

--Insertando Productos
INSERT INTO productos.producto (nombre, descripcion, precio)
VALUES
  ('Arroz integral', 'Arroz integral precocido de alta calidad', 5.25),
  ('Aceite de Oliva Virgen Extra', 'Aceite de oliva premium', 7),
  ('Lentejas', 'Lentejas secas de granja', 3.56),
  ('Salmon fresco', 'Filetes de salmon fresco', 59.99),
  ('Cereales de desayuno', 'Cereales de desayuno variados', 2.99),
  ('Pasta de trigo integral', 'Pasta de trigo integral', 12.86),
  ('Leche organica', 'Leche organica de vacas felices', 3.49),
  ('Horno de microondas Panasonic', 'Horno de microondas con multiples funciones', 1449.50),
  ('Plancha de vapor Philips', 'Plancha de vapor con sistema antigoteo', 149.20),
  ('Ventilador de torre Honeywell', 'Ventilador de torre con control remoto', 79.99),
  ('Cafetera Nespresso', 'Cafetera de capsulas con espumador de leche', 199.99),
  ('Yogur griego', 'Yogur griego natural sin azucar', 2.79),
  ('Cafe molido', 'Cafe molido de origen colombiano', 8.49),
  ('Zanahorias', 'Zanahorias organicas', 2.29),
  ('Papas', 'Papas frescas cultivadas localmente', 1.99),
  ('Pan integral', 'Pan integral recien horneado', 3.29),
  ('Huevos organicos', 'Huevos organicos de gallinas libres', 4.99),
  ('Fresas', 'Fresas frescas y jugosas', 3.99),
  ('Juego de utensilios de cocina', 'Juego de utensilios de cocina de acero inoxidable', 49.99),
  ('Caja de herramientas Craftsman', 'Caja de herramientas con multiples compartimentos', 129.75),
  ('Bicicleta de montaña Trek', 'Bicicleta de montaña con suspension total', 699.99),
  ('Monopatin electrico Razor', 'Monopatin electrico con motor de alta potencia', 299.25),
  ('Juego de maletas Samsonite', 'Juego de maletas con ruedas giratorias', 199.99),
  ('Te verde', 'Te verde de calidad premium', 3.79),
  ('Sopa de tomate', 'Sopa de tomate casera', 2.49),
  ('Pavo ahumado', 'Pavo ahumado de calidad', 7.99),
  ('Tomate Verde', 'Tomate para ensaladas verdes', 2.99),
  ('Altavoces Bluetooth Bose', 'Altavoces Bluetooth portatiles de alta calidad', 149.75),
  ('Auriculares inalambricos Sony', 'Auriculares inalambricos con cancelacion de ruido', 199.00),
  ('Impresora Epson', 'Impresora de inyeccion de tinta de alto rendimiento', 119.99),
  ('Mesa de comedor moderna', 'Mesa de comedor de diseño contemporaneo', 499.00),
  ('Cereal de avena', 'Cereal de avena organica', 44.49),
  ('Almendras', 'Almendras tostadas y saladas', 6.99),
  ('Miel de abeja', 'Miel de abeja pura y natural', 25.49),
  ('Sardinas en lata', 'Sardinas en aceite de oliva', 21.99),
  ('Cortadora de cesped Honda', 'Cortadora de cesped autopropulsada de gas', 349.99),
  ('Barbacoa Weber', 'Barbacoa de carbon con superficie de coccion grande', 199.00),
  ('Tienda de campaña Coleman', 'Tienda de campaña para 4 personas', 89.99),
  ('Tofu', 'Tofu organico para platos vegetarianos', 30.99),
  ('Aceitunas', 'Aceitunas rellenas de pimiento', 21.99),
  ('Mantequilla de mani', 'Mantequilla de mani natural', 15.49),
  ('Yogur de vainilla', 'Yogur de vainilla con frutas', 22.79),
  ('Uvas', 'Uvas rojas y verdes', 3.49),
  ('Pan multigrano', 'Pan multigrano recien horneado', 23.99),
  ('Cafe descafeinado', 'Cafe descafeinado de alta calidad', 6.00),
  ('Mantequilla', 'Mantequilla de leche de pastoreo', 14.99),
  ('Hamburguesas de res', 'Hamburguesas de res 100% magra', 27.99),
  ('Pimientos', 'Pimientos rojos y verdes frescos', 12.99),
  ('Cereal de arroz', 'Cereal de arroz sin gluten', 13.99),
  ('Queso mozzarella', 'Queso mozzarella para pizzas', 24.25),
  ('Salsa de tomate', 'Salsa de tomate casera', 22.99),
  ('Licuadora Oster', 'Licuadora de alta potencia con cuchillas de acero inoxidable', 79.25),
  ('Robot de cocina KitchenAid', 'Robot de cocina con multiples accesorios', 349.99),
  ('Aspiradora Roomba', 'Aspiradora robot con mapeo inteligente', 299.99),
  ('Pavo deli', 'Pavo deli en lonchas finas', 86.99),
  ('Lavadora Whirlpool', 'Lavadora de carga frontal con capacidad de 10 kg', 599.25),
  ('Secadora LG', 'Secadora de ropa con tecnologia de secado rapido', 499.99),
  ('Tostadora Cuisinart', 'Tostadora de 4 rebanadas con funciones de descongelado', 39.99),
  ('Smartphone Samsung Galaxy', 'Smartphone con pantalla OLED y camara de alta resolucion', 699.75),
  ('Portatil HP', 'Portatil con procesador de ultima generacion', 799.99),
  ('Televisor Sony 4K', 'Televisor LED 4K con HDR y Android TV', 899.25),
  ('Tablet Apple iPad', 'Tablet con pantalla Retina y potente rendimiento', 499.99),
  ('Camara Canon DSLR', 'Camara reflex digital con lentes intercambiables', 599.75),
  ('Reproductor de Blu-ray LG', 'Reproductor de Blu-ray con streaming en linea', 69.25),
  ('Sofa seccional de cuero', 'Sofa seccional de cuero genuino', 899.99),
  ('Silla de oficina ergonomica', 'Silla de oficina ajustable con soporte lumbar', 199.25),
  ('Mesa de centro de vidrio', 'Mesa de centro de vidrio templado con base metalica', 299.25),
  ('Lampara de pie LED', 'Lampara de pie LED regulable en intensidad', 79.99),
  ('Set de golf Callaway', 'Set de golf completo con bolsa de transporte', 399.75),
  ('Mesa de ping pong Stiga', 'Mesa de ping pong plegable con accesorios', 299.99),
  ('Piscina inflable Intex', 'Piscina inflable con capacidad para 4 personas', 69.99),
  ('Tobogan de agua Little Tikes', 'Tobogan de agua para niños', 79.99),
  ('Caja de bloques LEGO', 'Caja de bloques de construccion creativos', 49.25),
  ('Pelota de futbol Adidas', 'Pelota de futbol oficial de la Copa del Mundo', 19.45),
  ('Bicicleta estatica NordicTrack', 'Bicicleta estatica con pantalla tactil', 349.99),
  ('Patinete electrico Xiaomi', 'Patinete electrico plegable con frenos de disco', 399.85),
  ('Cereal de maiz', 'Cereal de maiz crujiente', 52.29),
  ('Leche de almendras', 'Leche de almendras sin azucar', 24.90),
  ('Brocoli', 'Brocoli fresco y saludable', 2.79),
  ('Aguacates', 'Aguacates maduros y deliciosos', 2.50),
  ('Pan Frances', 'Pan de tipo frances', 23.25),
  ('Fideo sauce', 'Fideo de tipo americano', 13.99),
  ('Sopa de lentejas', 'Sopa de lentejas casera', 12.75),
  ('Pechuga de pavo', 'Pechuga de pavo en lonchas', 24.00),
  ('Casco de bicicleta Giro', 'Casco de bicicleta con tecnologia de proteccion avanzada', 59.20),
  ('Mochila North Face', 'Mochila resistente al agua para actividades al aire libre', 78.76),
  ('Manzanas', 'Manzanas frescas y crujientes', 5.20),
  ('Chocolate negro', 'Chocolate negro de alta calidad', 14.49),
  ('Pollo fresco', 'Pollo fresco de corral', 22.40),
  ('Queso cheddar', 'Queso cheddar en bloque', 55.00),
  ('Set de herramientas de jardin', 'Set de herramientas de jardin de alta calidad', 519.35),
  ('Cartuchera escolar', 'Cartuchera de alta calidad', 120.00),
  ('Cargador tipo C', 'Cargador con entrada tipo C', 51.52),
  ('Uva', 'Libra de uva nacional', 12.25),
  ('Balon de Futbol', 'Balon de futbol numero 5', 120.75),
  ('Balon de Beisbol ', 'Balon de beisbol semiprofesional', 850.35),
  ('Balon de Voleibol', 'Balon de Voleibol semiprofesional', 152.50),
  ('Alcohol en gel ', 'Alcohol en gel para manos', 12.50),
  ('Balon de Baloncesto ', 'Balon de Baloncesto profesional', 1152.25),
  ('Guitarra Clasica ', 'Guitarra clasica electoacustica', 985.75),
  ('Colchon de espuma viscoelastica', 'Colchon de espuma viscoelastica con soporte lumbar', 12.5);


--Insertando en la bodega de la sucursal 1
INSERT INTO sucursales.bodega (codigo_producto,cantidad_producto,codigo_sucursal) VALUES
('1','8','1'),
('2','15','1'),
('3','45','1'),
('4','60','1'),
('5','30','1'),
('6','72','1'),
('7','22','1'),
('8','63','1'),
('9','12','1'),
('10','41','1'),
('11','54','1'),
('12','7','1'),
('13','3','1'),
('14','67','1'),
('15','48','1'),
('16','1','1'),
('17','19','1'),
('18','59','1'),
('19','10','1'),
('20','79','1'),
('21','35','1'),
('22','56','1'),
('23','67','1'),
('24','20','1'),
('25','76','1'),
('26','13','1'),
('27','60','1'),
('28','50','1'),
('29','8','1'),
('30','41','1'),
('31','8','1'),
('32','11','1'),
('33','42','1'),
('34','58','1'),
('35','28','1'),
('36','66','1'),
('37','9','1'),
('38','49','1'),
('39','1','1'),
('40','51','1'),
('41','76','1'),
('42','6','1'),
('43','36','1'),
('44','68','1'),
('45','43','1'),
('46','16','1'),
('47','37','1'),
('48','75','1'),
('49','26','1'),
('50','75','1'),
('51','14','1'),
('52','79','1'),
('53','70','1'),
('54','37','1'),
('55','45','1'),
('56','55','1'),
('57','12','1'),
('58','47','1'),
('59','62','1'),
('60','10','1'),
('61','38','1'),
('62','53','1'),
('63','18','1'),
('64','78','1'),
('65','15','1'),
('66','47','1'),
('67','34','1'),
('68','1','1'),
('69','33','1'),
('70','63','1'),
('71','24','1'),
('72','75','1'),
('73','37','1'),
('74','68','1'),
('75','54','1'),
('76','31','1'),
('77','8','1'),
('78','21','1'),
('79','52','1'),
('80','72','1'),
('81','69','1'),
('82','5','1'),
('83','70','1'),
('84','64','1'),
('85','71','1'),
('86','30','1'),
('87','62','1'),
('88','27','1'),
('89','47','1'),
('90','1','1'),
('91','75','1'),
('92','23','1'),
('93','76','1'),
('94','15','1'),
('95','58','1'),
('96','44','1'),
('97','79','1'),
('98','31','1'),
('99','39','1'),
('100','57','1');

--Insertando en la bodega de la sucursal 2
INSERT INTO sucursales.bodega (codigo_producto,cantidad_producto,codigo_sucursal) VALUES
('1','8','2'),
('2','115','2'),
('3','45','2'),
('4','60','2'),
('5','340','2'),
('6','72','2'),
('7','22','2'),
('8','63','2'),
('9','142','2'),
('10','41','2'),
('11','54','2'),
('12','17','2'),
('13','38','2'),
('14','67','2'),
('15','48','2'),
('16','12','2'),
('17','119','2'),
('18','589','2'),
('19','160','2'),
('20','749','2'),
('21','3','2'),
('22','52','2'),
('23','6','2'),
('24','20','2'),
('25','76','2'),
('26','1','2'),
('27','60','2'),
('28','502','2'),
('29','8','2'),
('30','41','2'),
('31','8','2'),
('32','1','2'),
('33','42','2'),
('34','58','2'),
('35','10','2'),
('36','66','2'),
('37','9','2'),
('38','4','2'),
('39','1','2'),
('40','11','2'),
('41','76','2'),
('42','6','2'),
('43','36','2'),
('44','25','2'),
('45','43','2'),
('46','16','2'),
('47','37','2'),
('48','75','2'),
('49','26','2'),
('50','22','2'),
('76','31','2'),
('77','8','2'),
('78','21','2'),
('79','52','2'),
('80','72','2'),
('81','12','2'),
('82','5','2'),
('83','70','2'),
('84','64','2'),
('85','71','2'),
('86','3','2'),
('87','62','2'),
('88','27','2'),
('89','44','2'),
('90','1','2'),
('91','75','2'),
('92','23','2'),
('93','76','2'),
('94','15','2'),
('95','518','2'),
('96','44','2'),
('97','79','2'),
('98','341','2'),
('99','39','2'),
('100','57','2');


--Insertando en la bodega de la sucursal 3
INSERT INTO sucursales.bodega (codigo_producto,cantidad_producto,codigo_sucursal) VALUES
('1', '9', '3'),
('2', '33', '3'),
('3', '78', '3'),
('4', '23', '3'),
('5', '56', '3'),
('6', '14', '3'),
('7', '110', '3'),
('8', '49', '3'),
('9', '37', '3'),
('10', '120', '3'),
('11', '41', '3'),
('12', '6', '3'),
('13', '88', '3'),
('14', '58', '3'),
('26', '53', '3'),
('27', '101', '3'),
('28', '136', '3'),
('29', '30', '3'),
('30', '89', '3'),
('31', '66', '3'),
('32', '45', '3'),
('33', '79', '3'),
('34', '47', '3'),
('35', '76', '3'),
('36', '134', '3'),
('37', '77', '3'),
('38', '84', '3'),
('39', '38', '3'),
('40', '69', '3'),
('41', '108', '3'),
('42', '93', '3'),
('43', '73', '3'),
('44', '109', '3'),
('45', '54', '3'),
('46', '22', '3'),
('47', '116', '3'),
('48', '115', '3'),
('49', '95', '3'),
('50', '75', '3'),
('51', '110', '3'),
('52', '8', '3'),
('53', '102', '3'),
('54', '103', '3'),
('55', '124', '3'),
('56', '7', '3'),
('57', '31', '3'),
('58', '85', '3'),
('59', '64', '3'),
('71', '82', '3'),
('72', '130', '3'),
('73', '126', '3'),
('74', '60', '3'),
('75', '46', '3'),
('76', '50', '3'),
('77', '86', '3'),
('78', '51', '3'),
('79', '131', '3'),
('80', '21', '3'),
('81', '32', '3'),
('82', '57', '3'),
('83', '100', '3'),
('84', '39', '3'),
('85', '92', '3'),
('86', '59', '3'),
('87', '85', '3'),
('88', '585', '3'),
('89', '150', '3'),
('90', '123', '3'),
('91', '105', '3'),
('92', '31', '3'),
('93', '3', '3'),
('94', '89', '3'),
('95', '12', '3'),
('96', '45', '3'),
('97', '25', '3');


--INSERTANDO CLIENTES
INSERT INTO clientes.cliente (nombre, nit, puntos,compras,tarjeta) VALUES 
('Consumidor Final','CF',0,0,0),
('Eduardo Jose Lopez Aguirre','153698741',0,0,0),
('Fernanda Elisa Smith Lara','259048741',0,0,0),
('Melida Anahi Abreu Escobar','398745684',0,0,0),
('Manuel Alberto Arcos Diaz','965097451',0,0,0),
('Ricardo Manuel Palma','536986145',0,0,0),
('Farrokh Bulsara','125698705',0,0,0),
('Brian Harold May ','156987468',0,0,0),
('Roger Meddows Taylor','459869874',0,0,0),
('John Richard Deacon','485963015',0,0,0);



INSERT INTO sucursales.inventario (codigo_sucursal,numero_estante,numero_pasillo,codigo_producto,cantidad) VALUES 
(1,1,1,1,45),
(1,2,1,2,18),
(1,3,1,3,55),

(2,1,1,1,12),
(2,2,1,2,5),
(2,3,1,3,25),

(3,1,1,1,78),
(3,2,1,2,61),
(3,3,1,3,25);
