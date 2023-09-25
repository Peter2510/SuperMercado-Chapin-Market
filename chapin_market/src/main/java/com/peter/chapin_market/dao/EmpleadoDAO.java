package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Service;

import com.peter.chapin_market.modelo.Empleado;

@Service
public class EmpleadoDAO {

	@Autowired
	private DataSource dataSource; // Inyecta el DataSource "configuracion de la base de datos"	

	
	public List<Empleado> getEmpleados() {
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		Connection connection;
		Statement statement;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT codigo,nombre,codigo_sucursal,rol,caja FROM empleados.empleado");

			while (resultSet.next()) {
				Empleado empleado = new Empleado();
				empleado.setCodigo(resultSet.getInt("codigo"));
				empleado.setNombre(resultSet.getString("nombre"));
				empleado.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				empleado.setCodigo_rol(resultSet.getInt("rol"));
				empleado.setCaja(resultSet.getInt("caja"));
				empleados.add(empleado);
			}

			resultSet.close();
			statement.close();
			connection.close();

			return empleados;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
	}
	
	
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
	
/*	public UserDetails getEmpleadoById(String code) {
		
		int codigo = Integer.parseInt(code);
		Connection connection;
		ResultSet resultSet;

		try {
			
			Empleado empleado = new Empleado();
			String query = "SELECT codigo,nombre,codigo_sucursal,rol,caja FROM empleados.empleado WHERE codigo = ?";
			connection = dataSource.getConnection();		
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo);
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
		
		
	}*/


}
