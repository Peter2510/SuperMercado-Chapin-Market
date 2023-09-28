package com.peter.chapin_market.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peter.chapin_market.dao.ClienteDAO;
import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Cliente;
import com.peter.chapin_market.post_request.ClienteRequest;
import com.peter.chapin_market.post_request.SucursalRequest;

@RestController
@RequestMapping("/")
public class ClienteControlador {

	@Autowired ClienteDAO cliente;
	
	@GetMapping(value = "/chapinMarket/obtener-cliente",produces="application/json")
	public ResponseEntity<Cliente> obtenerCliente(@RequestBody ClienteRequest clienteR) {

		//se obtienen los productos en bodega de una sucursal  		  		
  		Cliente clienteHallado = cliente.obtenerClienteNit(clienteR.getNit());
  	
		if(clienteHallado!=null) {
			return ResponseEntity.ok(clienteHallado);		
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
