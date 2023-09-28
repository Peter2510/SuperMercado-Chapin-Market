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
import com.peter.chapin_market.modelo.Inventario;


@Service
public class InventarioDAO {

	@Autowired
	private DataSource dataSource;
	
	public List<Inventario> obtenerInventario(int codigoSucursal) {
		
		Connection connection;
		ResultSet resultSet;


		try {

			connection = dataSource.getConnection();
			String query = "SELECT\r\n"
					+ "    inventario.codigo_sucursal,\r\n"
					+ "    inventario.codigo_producto,\r\n"
					+ "    producto.nombre AS nombre_producto,\r\n"
					+ "    inventario.numero_estante,\r\n"
					+ "    inventario.numero_pasillo,\r\n"
					+ "    inventario.cantidad AS cantidad_en_inventario,\r\n"
					+ "    bodega.cantidad_producto AS cantidad_en_bodega\r\n"
					+ "FROM\r\n"
					+ "    sucursales.inventario inventario\r\n"
					+ "LEFT JOIN\r\n"
					+ "    sucursales.bodega bodega ON inventario.codigo_producto = bodega.codigo_producto AND inventario.codigo_sucursal = bodega.codigo_sucursal\r\n"
					+ "INNER JOIN\r\n"
					+ "    productos.producto producto ON inventario.codigo_producto = producto.codigo\r\n"
					+ "WHERE\r\n"
					+ "    inventario.codigo_sucursal = ?"; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigoSucursal);
			resultSet = preST.executeQuery();
			
			List<Inventario> inventario = new ArrayList<Inventario>();

			while (resultSet.next()) {
				Inventario producto_inventario = new Inventario(); 
				producto_inventario.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				producto_inventario.setNumero_pasillo(resultSet.getInt("numero_pasillo"));
				producto_inventario.setNumero_estante(resultSet.getInt("numero_estante"));
				producto_inventario.setCodigo_producto(resultSet.getInt("codigo_producto"));
				producto_inventario.setNombre_producto(resultSet.getString("nombre_producto"));
				producto_inventario.setCantidad_producto_inventario(resultSet.getInt("cantidad_en_inventario"));
				producto_inventario.setCantidad_producto_bodega(resultSet.getInt("cantidad_en_bodega"));
				inventario.add(producto_inventario);
			}

			resultSet.close();
			connection.close();
					
			return inventario;
			
		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
		
		
	}
	
	
}
