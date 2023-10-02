package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDAO {

	@Autowired
	DataSource dataSource;
	
	public int registarVenta(int codigo_sucursal, String nit_cliente, double total, int codigo_cajero, LocalDate fecha ) {
		
		Connection connection;
		ResultSet resultSet;

		try {
			connection = dataSource.getConnection();
			
			
			String query = "INSERT INTO ventas.venta "
					+ "(codigo_cajero,nit_cliente,total_venta,fecha_venta,codigo_sucursal)\r\n"
					+ "VALUES (?,?,?,?,?)";
			
			PreparedStatement preST = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			
			preST.setInt(1, codigo_cajero);
			preST.setString(2, nit_cliente);
			preST.setDouble(3, total);
			preST.setObject(4,fecha);
			preST.setInt(5, codigo_sucursal);


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
				return -1; 
			}
		} catch (SQLException e) {
			System.out.print(e);
			return -1;
		}
		
		
	}
	
}
