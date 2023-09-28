package com.peter.chapin_market.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.peter.chapin_market.dao.EmpleadoDAO;
import com.peter.chapin_market.modelo.Empleado;
import com.peter.chapin_market.post_request.Credenciales;


@RestController
@RequestMapping("/")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoDAO empleados; 
	
	@GetMapping(value = "/chapinMarket/empleados", produces = "application/json")
	@Cacheable("empleados")
	public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
			
		List<Empleado> lista = empleados.getEmpleados();

		if (lista!=null) {
			return ResponseEntity.ok(lista);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	

	@PostMapping(value = "/chapinMarket/login", consumes = "application/json")
	public ResponseEntity<Empleado> login(@RequestBody Credenciales credenciales) {

		//se obtiene el codigo y contraseña y se valida para generar el JWT o en codigo 401
		Empleado empleado = empleados.verificarCredenciales(credenciales.getCodigo(), credenciales.getContrasenia());
	
		if(empleado!=null) {

			return ResponseEntity.ok(empleado);
			
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	

}
