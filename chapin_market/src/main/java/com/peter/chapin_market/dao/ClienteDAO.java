package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.chapin_market.modelo.Cliente;
import com.peter.chapin_market.modelo.Producto;

@Service
public class ClienteDAO {

	@Autowired
	private DataSource dataSource;
	
	public Cliente obtenerClienteNit(String nit) {
		
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT * FROM clientes.cliente WHERE nit = ?;"; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setString(1,nit);
			resultSet = preST.executeQuery();
			
			Cliente clienteHallado=null;
			
			while (resultSet.next()) {
				clienteHallado = new Cliente();
				clienteHallado.setNombre(resultSet.getString("nombre"));
				clienteHallado.setPuntos(resultSet.getInt("puntos"));
				clienteHallado.setNit(resultSet.getString("nit"));
				clienteHallado.setTipo_tarjeta(resultSet.getInt("tarjeta"));
			}

			resultSet.close();
			connection.close();
					
			return clienteHallado;
	
		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
		
		
	}
	
}
