package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.chapin_market.modelo.Producto;


@Service
public class ProductoDAO {

	@Autowired
	private DataSource dataSource;
	
	public Producto existeProducto(int codigo) {
				
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT * FROM productos.producto WHERE codigo = ?;"; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo);
			resultSet = preST.executeQuery();
			
			Producto producto = null;

			while (resultSet.next()) {
				
				producto = new Producto();
				producto.setCodigo( resultSet.getInt("codigo"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setNombre(resultSet.getString("nombre"));
				producto.setPrecio(resultSet.getDouble("precio"));

			}

			resultSet.close();
			connection.close();
			
			if(producto!=null) {
				return producto;
			}else {
				return null;
			}
			

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
		
		
	}
	
}
