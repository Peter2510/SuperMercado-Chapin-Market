package com.peter.chapin_market.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.peter.chapin_market.modelo.Empleado;

@RestController
@RequestMapping("/")

public class EmpleadoControlador {

	@Autowired
    private DataSource dataSource; // Inyecta el DataSource "configuracion de la base de datos"
	
	
	@GetMapping(value = "/chapinMarket/empleados", produces = "application/json")
	public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
		
        List<Empleado> empleados = new ArrayList<Empleado>();
        
        try {
        	
        	Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados.empleado");

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigo(resultSet.getInt("codigo"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setRol(resultSet.getInt("codigo_sucursal"));
                empleado.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
                empleados.add(empleado);
            }
        
            return ResponseEntity.ok(empleados);
            
        } catch (SQLException e) {
            // Manejo de excepciones
        	return ResponseEntity.badRequest().build();
        }
        
    }
	
}
