package com.peter.chapin_market.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.peter.chapin_market.dao.BodegaDAO;
import com.peter.chapin_market.dao.ProductoDAO;
import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Cliente;
import com.peter.chapin_market.modelo.Producto;
import com.peter.chapin_market.post_request.ActualizarStockRequest;
import com.peter.chapin_market.post_request.BodegaRequest;
import com.peter.chapin_market.post_request.IngresarProductoBodegaRequest;
import com.peter.chapin_market.post_request.ProductoRequest;
import com.peter.chapin_market.post_request.SucursalRequest;

@RestController
@RequestMapping("/")
public class BodegaControlador {

	@Autowired
	private BodegaDAO bodega;

	@Autowired
	private ProductoDAO producto;

	@GetMapping(value = "/chapinMarket/bodega", produces = "application/json")
	public ResponseEntity<List<Bodega>> getProductosBodega(@RequestParam String sucursal) {

		int codigo = Integer.parseInt(sucursal);
		// se obtienen los productos en bodega de una sucursal
		List<Bodega> lista = bodega.getProductos((codigo));

		if (lista != null) {
			return ResponseEntity.ok(lista);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping(value = "/chapinMarket/producto", produces = "application/json")
	public ResponseEntity<Boolean> exitesteProductoPorNombre(@RequestParam String nombre) {

		// se obtienen los productos en bodega de una sucursal
		Boolean productoHallado = producto.existeProductoPorNombre(nombre);

		if (productoHallado != null) {

			return ResponseEntity.ok(productoHallado);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping(value = "/chapinMarket/crear-producto", produces = "application/json")
	public ResponseEntity<Boolean> crearProducto(@RequestBody BodegaRequest bodegaR) {

		int codigoProducto = producto.agregarProducto(bodegaR.getProducto());
		if (codigoProducto > 0) {

			bodegaR.getBodega().setCodigo_producto(codigoProducto);
			boolean seAgregoAbodega = bodega.insertarBodega(bodegaR.getBodega());

			return ResponseEntity.ok(seAgregoAbodega);

		} else {
			return ResponseEntity.ok(false);
		}

	}

	@GetMapping(value = "/chapinMarket/disponibles", produces = "application/json")
	public ResponseEntity<List<Producto>> productosDisponiblesParaAgregar(@RequestParam String sucursal) {

		// se obtienen los productos en bodega de una sucursal
		int codigo = Integer.parseInt(sucursal);

		List<Producto> productos = bodega.productosDisponiblesParaAgregar(codigo);

		if (productos != null) {

			return ResponseEntity.ok(productos);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping(value = "/chapinMarket/agregar-producto-sucursal", produces = "application/json")
	public ResponseEntity<Boolean> agregarProductoBodega(@RequestBody Bodega bodegaR) {

		boolean seAgregoAbodega = bodega.insertarBodega(bodegaR);

		if (seAgregoAbodega) {

			return ResponseEntity.ok(seAgregoAbodega);

		} else {
			return ResponseEntity.ok(false);
		}

	}
	
	@GetMapping(value = "/chapinMarket/obtener-producto", produces = "application/json")
	public ResponseEntity<Producto> obtenerProductoPorId(@RequestParam String codigo) {

		// se obtienen los productos en bodega de una sucursal
		Producto productoHallado = producto.obtenerProductoPorId(codigo);

		if (productoHallado != null) {

			return ResponseEntity.ok(productoHallado);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	//Se usa para mostrar el producto a editar el stock
	@GetMapping(value = "/chapinMarket/obtener-producto-bodega", produces = "application/json")
	public ResponseEntity<Bodega> obtenerProductoBodega(@RequestParam String codigo_producto, @RequestParam String codigo_sucursal) {

		// se obtienen un producto en bodega de una sucursal
		int codigoSucursal = Integer.parseInt(codigo_sucursal);
		int codigoProducto = Integer.parseInt(codigo_producto);
		Bodega productoHallado = bodega.productoBodegaSucursal(codigoProducto,codigoSucursal);

		if (productoHallado != null) {

			return ResponseEntity.ok(productoHallado);

		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping(value = "/chapinMarket/actualizar-stock-producto", produces = "application/json")
	public ResponseEntity<Boolean> actualizarStockProducto(@RequestBody ActualizarStockRequest request){
				
        int codigoProducto =   Integer.parseInt(request.getCodigo_producto());
        int codigoSucursal =   Integer.parseInt(request.getCodigo_sucursal());
        int cantidadProducto = Integer.parseInt(request.getCantidad_producto());
		
		boolean seActualizo = bodega.actualizarStockProducto(codigoProducto, codigoSucursal, cantidadProducto);
		
		if(seActualizo) {
			return ResponseEntity.ok(seActualizo);
		}else {
			return ResponseEntity.ok(false);
		}
		
		
	}
	
	@PostMapping(value = "/chapinMarket/actualizar-producto", produces = "application/json")
	public ResponseEntity<Boolean> actualizarProducto(@RequestBody Producto request){
				
		boolean seActualizo = producto.actualizarProducto(request);
		
		if(seActualizo) {
			return ResponseEntity.ok(seActualizo);
		}else {
			return ResponseEntity.ok(false);
		}
			
		
	}
	
	
	

}
