package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.peter.chapin_market.modelo.Cliente;
import com.peter.chapin_market.modelo.Producto;

@Service
public class ProductoDAO {

	@Autowired
	private DataSource dataSource;

	public Boolean existeProducto(String nombre) {

		Connection connection;
		ResultSet resultSet;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT 1 FROM productos.producto WHERE nombre = ? LIMIT 1;"; 
			
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setString(1, nombre);
			resultSet = preST.executeQuery();

			boolean productoExiste = resultSet.next(); // Se verifica que haya al menos una fila

			resultSet.close();
			connection.close();

			return productoExiste;

		} catch (SQLException e) {
			System.out.print(e);
			return false;
		}

	}

	public int agregarProducto(Producto producto) {

		Connection connection;
		ResultSet resultSet;

		try {
		    connection = dataSource.getConnection();
		    String query = "INSERT INTO productos.producto (nombre, descripcion, precio) VALUES (?, ?, ?) RETURNING codigo"; // Reemplaza "codigo" por el nombre de tu columna serial
		    PreparedStatement preST = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		    preST.setString(1, producto.getNombre());
		    preST.setString(2, producto.getDescripcion());
		    preST.setDouble(3, producto.getPrecio());

		    int insertado = preST.executeUpdate();

		    if (insertado > 0) {
		        resultSet = preST.getGeneratedKeys();
		        if (resultSet.next()) {
		            int idGenerado = resultSet.getInt(1); // Obtiene el valor del campo serial generado
		            resultSet.close();
		            connection.close();
		            return idGenerado;
		        } else {
		            connection.close();
		            return -1; // Indica que no se pudo obtener el valor generado
		        }
		    } else {
		        connection.close();
		        return -1; // Indica que no se pudo insertar el registro
		    }
		} catch (SQLException e) {
		    System.out.print(e);
		    return -1; // Indica que ocurrió un error
		}
	}

}
