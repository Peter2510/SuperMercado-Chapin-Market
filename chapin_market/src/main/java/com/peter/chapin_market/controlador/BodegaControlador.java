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
	public ResponseEntity<Boolean> obtenerProducto(@RequestParam String nombre) {

		// se obtienen los productos en bodega de una sucursal
		Boolean productoHallado = producto.existeProducto(nombre);

		if (productoHallado != null) {

			return ResponseEntity.ok(productoHallado);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping(value = "/chapinMarket/crear-producto", produces = "application/json")
	public ResponseEntity<Boolean> crearProducto(@RequestBody BodegaRequest bodegaR) {

		int codigoProducto = producto.agregarProducto(bodegaR.getProducto());
		System.out.println("El id generado fue" + codigoProducto);

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

}
