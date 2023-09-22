package com.peter.chapin_market.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
                empleado.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
                switch (empleado.getCodigo_sucursal()) {
				case 1: 
				empleado.setNombre_sucursal("Central");
				break;
				case 2: 
				empleado.setNombre_sucursal("Norte");
				break;
				case 3: 
				empleado.setNombre_sucursal("Sur");
				break;
				}                    
                empleado.setCodigo_rol(resultSet.getInt("rol"));
                switch (empleado.getCodigo_rol()) {
				case 1: 
				empleado.setNombre_rol("Cajero");
				break;
				case 2: 
				empleado.setNombre_rol("Bodega");
				break;
				case 3: 
				empleado.setNombre_rol("Inventario");
				break;
				case 4: 
				empleado.setNombre_rol("Administrador");
				break;
				}
                
                


                empleados.add(empleado);
            }
        
            return ResponseEntity.ok(empleados);
            
        } catch (SQLException e) {
            // Manejo de excepciones
        	return ResponseEntity.badRequest().build();
        }
        
    }
	
}
