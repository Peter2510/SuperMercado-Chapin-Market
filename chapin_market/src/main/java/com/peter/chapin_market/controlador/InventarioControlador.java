package com.peter.chapin_market.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peter.chapin_market.dao.InventarioDAO;
import com.peter.chapin_market.modelo.Inventario;
import com.peter.chapin_market.post_request.InventarioRequest;

@RestController
@RequestMapping("/")
public class InventarioControlador {

	@Autowired
	private InventarioDAO inventario;
	
	@GetMapping(value = "/chapinMarket/inventario", produces = "application/json")
	public ResponseEntity<List<Inventario>> obtenerInventarioSucursal(@RequestParam String sucursal){
		
		int codigo_sucursal = Integer.parseInt(sucursal);
		
		List<Inventario> inventarioHallado = inventario.obtenerInventario(codigo_sucursal);
		
		if(inventarioHallado!=null) {
			return ResponseEntity.ok(inventarioHallado);
		}else {
			return ResponseEntity.notFound().build();
		}
				
	}
	
	
}
