package com.peter.chapin_market.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.peter.chapin_market.dao.ClienteDAO;
import com.peter.chapin_market.modelo.Cliente;


@RestController
@RequestMapping("/")
public class ClienteControlador {

	@Autowired ClienteDAO cliente;
	
	@GetMapping(value = "/chapinMarket/obtener-cliente",produces="application/json")
	public ResponseEntity<Cliente> obtenerCliente(@RequestParam String nit) {
		
		//se obtienen los productos en bodega de una sucursal  		  		
  		Cliente clienteHallado = cliente.obtenerClienteNit(nit);
  	
		if(clienteHallado!=null) {
			
			return ResponseEntity.ok(clienteHallado);	
			
					
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping(value = "/chapinMarket/crear-cliente",produces="application/json")
	public ResponseEntity<Boolean> crearCliente(@RequestBody Cliente clienteR){
		
				
		boolean seAgrego = cliente.agregarCliente(clienteR);
		
		if(seAgrego) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.ok(false);
		}
		
	}
	
}
