package com.peter.chapin_market.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peter.chapin_market.dao.InventarioDAO;
import com.peter.chapin_market.dao.VentaDAO;
import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Producto;

@RestController
@RequestMapping("/")
public class CajeroContralador {

	@Autowired VentaDAO venta;
	@Autowired InventarioDAO inventario;
	
	@GetMapping(value = "/chapinMarket/generar-venta", produces = "application/json")
	public ResponseEntity<Integer> generarVenta(@RequestParam String codigo_cajero, @RequestParam String nit_cliente,@RequestParam String total_venta, @RequestParam String codigo_sucursal ) {

	
		int codigoCajero = Integer.parseInt(codigo_cajero);
		double totalVenta  = Double.parseDouble(total_venta);
		LocalDate fechaActual = LocalDate.now();
		int codigoSucursal = Integer.parseInt(codigo_sucursal);
		
		
		// se obtienen los productos en bodega de una sucursal
		int code = venta.registarVenta(codigoSucursal,nit_cliente,totalVenta,codigoCajero,fechaActual);

		
		return ResponseEntity.ok(code);
		

	}
	
	@GetMapping(value = "/chapinMarket/productos-venta", produces = "application/json")
	public ResponseEntity<List<Producto>> productosAlaVenta(@RequestParam int codigo_sucursal ) {

	
		//int codigoSucursal = Integer.parseInt(codigo_sucursal);

		List<Producto> productos = inventario.productosVenta(codigo_sucursal);
		
		if(productos!=null) {
			return ResponseEntity.ok(productos);
		}else {
			return ResponseEntity.notFound().build();
		}
		

		

	}
	
	
	
	
}
