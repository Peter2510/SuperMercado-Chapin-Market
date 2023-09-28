package com.peter.chapin_market.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peter.chapin_market.dao.BodegaDAO;
import com.peter.chapin_market.dao.ProductoDAO;
import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Producto;
import com.peter.chapin_market.post_request.ProductoRequest;
import com.peter.chapin_market.post_request.SucursalRequest;

@RestController
@RequestMapping("/")
public class BodegaControlador {
	

	@Autowired
	private BodegaDAO bodega;

	@Autowired
	private ProductoDAO producto;
	
	@GetMapping(value = "/chapinMarket/bodega", produces="application/json")
	public ResponseEntity<List<Bodega>> getProductosBodega(@RequestBody SucursalRequest sucursal) {

		//se obtienen los productos en bodega de una sucursal  		  		
  		List<Bodega> lista = bodega.getProductos(Integer.parseInt(sucursal.getSucursal()));
  	
		if(lista!=null) {
			return ResponseEntity.ok(lista);		
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
  	
  	@RequestMapping(value = "/chapinMarket/producto", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public ResponseEntity<Producto> obtenerProducto(@RequestBody ProductoRequest productoR) {

		//se obtienen los productos en bodega de una sucursal  		  		
  		System.out.println(productoR.getCodigo());
  		Producto productoHallado = producto.existeProducto(productoR.getCodigo());
  		
  		if(productoHallado!=null) {
  		
  			return ResponseEntity.ok(productoHallado);
  			
  		}else {
  			return ResponseEntity.notFound().build();
  		}
  	
		
		
	}
  	
  	
 
}
