package com.peter.chapin_market.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peter.chapin_market.dao.InventarioDAO;
import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Inventario;
import com.peter.chapin_market.modelo.Producto;
import com.peter.chapin_market.post_request.ActualizarStockRequest;
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
	
	@GetMapping(value = "/chapinMarket/disponibles-inventario", produces = "application/json")
	public ResponseEntity<List<Producto>> productosDisponiblesParaAgregar(@RequestParam String sucursal) {

		// se obtienen los productos en bodega disponibles para agregar al inventario de una sucursal
		int codigo = Integer.parseInt(sucursal);

		List<Producto> productos = inventario.productosDisponiblesParaAgregar(codigo);

		if (productos != null) {

			return ResponseEntity.ok(productos);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping(value = "/chapinMarket/agregar-producto-inventario", produces = "application/json")
	public ResponseEntity<Boolean> agregarProductoBodega(@RequestBody Inventario inventarioR) {

		boolean seAgregoAbodega = inventario.agregarProductoInventario(inventarioR);

		if (seAgregoAbodega) {

			return ResponseEntity.ok(seAgregoAbodega);

		} else {
			return ResponseEntity.ok(false);
		}

	}
	
	// Se usa para mostrar el producto a editar el stock
	@GetMapping(value = "/chapinMarket/obtener-producto-inventario", produces = "application/json")
	public ResponseEntity<Inventario> obtenerProductoBodega(@RequestParam String codigo_producto,
			@RequestParam String codigo_sucursal) {

		// se obtienen un producto en inventario de una sucursal
		int codigoSucursal = Integer.parseInt(codigo_sucursal);
		int codigoProducto = Integer.parseInt(codigo_producto);
		Inventario productoHallado = inventario.productoInventarioSucursal(codigoProducto, codigoSucursal);

		if (productoHallado != null) {

			return ResponseEntity.ok(productoHallado);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping(value = "/chapinMarket/actualizar-inventario-producto", produces = "application/json")
	public ResponseEntity<Boolean> actualizarStockProducto(@RequestBody Inventario request) {

		
		boolean seActualizo = inventario.actualizarInventarioProducto(request);

		if (seActualizo) {
			return ResponseEntity.ok(seActualizo);
		} else {
			return ResponseEntity.ok(false);
		}

	}
	
	
	
	
	
	
}
