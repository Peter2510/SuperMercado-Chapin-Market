package com.peter.chapin_market.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.peter.chapin_market.dao.EmpleadoDAO;

import com.peter.chapin_market.modelo.Empleado;
import com.peter.chapin_market.utils.JWTRequest;
import com.peter.chapin_market.utils.JWTUtil;
import com.peter.chapin_market.utils.JWTResponse;

@RestController
@RequestMapping("/")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoDAO empleados; // Inyecta el DataSource "configuracion de la base de datos"
	@Autowired
	private JWTUtil jwutil;
	
	@GetMapping(value = "/chapinMarket/empleados", produces = "application/json")
	@Cacheable("empleados")
	public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
			
		List<Empleado> lista = empleados.getEmpleados();

		if (lista!=null) {
			return ResponseEntity.ok(lista);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	

	@RequestMapping(value = "/chapinMarket/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest credenciales) {

		//se obtiene el codigo y contraseña y se valida para generar el JWT o en codigo 401
		Empleado empleado = empleados.verificarCredenciales(credenciales.getCodigo(), credenciales.getContrasenia());
	
		if(empleado!=null&&empleado.getNombre()!=null) {

			String token_string = jwutil.create(String.valueOf(empleado.getCodigo()),empleado.getNombre(),String.valueOf(empleado.getCodigo_rol()),String.valueOf(empleado.getCaja()),String.valueOf(empleado.getCodigo_sucursal())); 
			JWTResponse token = new JWTResponse();
			token.setToken(token_string);
			return ResponseEntity.ok(token);
			
		}else {
			return ResponseEntity.status(401).build();
		}
		
	}
	
	

}
