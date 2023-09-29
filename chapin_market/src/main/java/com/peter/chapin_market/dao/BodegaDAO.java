package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Producto;

@Service
public class BodegaDAO {

	@Autowired
	private DataSource dataSource; // Inyecta el DataSource "configuracion de la base de datos"
	

 	public List<Bodega> getProductos(int codigo_sucursal) {
		
		List<Bodega> productos = new ArrayList<Bodega>();
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT codigo_sucursal, codigo_producto, nombre, cantidad_producto, precio FROM sucursales.bodega INNER JOIN productos.producto ON productos.producto.codigo = sucursales.bodega.codigo_producto WHERE sucursales.bodega.codigo_sucursal = ?;"; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo_sucursal);
			resultSet = preST.executeQuery();
			

			while (resultSet.next()) {
				Bodega bodega = new Bodega();
				bodega.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				bodega.setCodigo_producto(resultSet.getInt("codigo_producto"));
				bodega.setNombre_producto(resultSet.getString("nombre"));
				bodega.setCantidad_producto(resultSet.getInt("cantidad_producto"));
				bodega.setPrecio_unitario(resultSet.getInt("precio"));

				productos.add(bodega);
			}

			resultSet.close();
			connection.close();

			return productos;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
	}
	
	/*
	public Empleado verificarCredenciales(String code, String contrasenia) {
		
		int codigo = Integer.parseInt(code);
		Connection connection;
		ResultSet resultSet;

		try {
			
			Empleado empleado = new Empleado();
			String query = "SELECT codigo,nombre,codigo_sucursal,rol,caja FROM empleados.empleado WHERE codigo = ? AND contrasenia =?";
			connection = dataSource.getConnection();		
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo);
			preST.setString(2,contrasenia);
			resultSet = preST.executeQuery();
			
			while (resultSet.next()) {
				empleado.setCodigo(resultSet.getInt("codigo"));
				empleado.setNombre(resultSet.getString("nombre"));
				empleado.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				empleado.setCodigo_rol(resultSet.getInt("rol"));
				empleado.setCaja(resultSet.getInt("caja"));
			}

			resultSet.close();
			connection.close();

			return empleado;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
		
	} 
 
 * */	
	
}
