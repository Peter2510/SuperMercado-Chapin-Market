package com.peter.chapin_market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.chapin_market.modelo.Bodega;
import com.peter.chapin_market.modelo.Inventario;
import com.peter.chapin_market.modelo.Producto;

@Service
public class InventarioDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BodegaDAO bodega;

	public List<Inventario> obtenerInventario(int codigoSucursal) {

		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT\r\n" + "    inventario.codigo_sucursal,\r\n" + "    inventario.codigo_producto,\r\n"
					+ "    producto.nombre AS nombre_producto,\r\n" + "    inventario.numero_estante,\r\n"
					+ "    inventario.numero_pasillo,\r\n" + "    inventario.cantidad AS cantidad_en_inventario,\r\n"
					+ "    bodega.cantidad_producto AS cantidad_en_bodega\r\n" + "FROM\r\n"
					+ "    sucursales.inventario inventario\r\n" + "LEFT JOIN\r\n"
					+ "    sucursales.bodega bodega ON inventario.codigo_producto = bodega.codigo_producto AND inventario.codigo_sucursal = bodega.codigo_sucursal\r\n"
					+ "INNER JOIN\r\n"
					+ "    productos.producto producto ON inventario.codigo_producto = producto.codigo\r\n"
					+ "WHERE\r\n" + "    inventario.codigo_sucursal = ?";
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1, codigoSucursal);
			resultSet = preST.executeQuery();

			List<Inventario> inventario = new ArrayList<Inventario>();

			while (resultSet.next()) {
				Inventario producto_inventario = new Inventario();
				producto_inventario.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				producto_inventario.setNumero_pasillo(resultSet.getInt("numero_pasillo"));
				producto_inventario.setNumero_estante(resultSet.getInt("numero_estante"));
				producto_inventario.setCodigo_producto(resultSet.getInt("codigo_producto"));
				producto_inventario.setNombre_producto(resultSet.getString("nombre_producto"));
				producto_inventario.setCantidad_producto_inventario(resultSet.getInt("cantidad_en_inventario"));
				producto_inventario.setCantidad_producto_bodega(resultSet.getInt("cantidad_en_bodega"));
				inventario.add(producto_inventario);
			}

			resultSet.close();
			connection.close();

			return inventario;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}

	}

	public List<Producto> productosDisponiblesParaAgregar(int codigo_sucursal) {

		List<Producto> productos = new ArrayList<Producto>();
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT p.* FROM productos.producto p WHERE p.codigo NOT IN ( SELECT pi.codigo_producto FROM sucursales.inventario pi WHERE pi.codigo_sucursal = ?)";

			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1, codigo_sucursal);
			resultSet = preST.executeQuery();

			while (resultSet.next()) {
				Producto producto = new Producto();

				producto.setCodigo(resultSet.getInt("codigo"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setNombre(resultSet.getString("nombre"));
				producto.setPrecio(resultSet.getDouble("precio"));
				productos.add(producto);
			}

			resultSet.close();
			connection.close();

			return productos;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}

	}

	public Boolean agregarProductoInventario(Inventario inventario) {

		Connection connection;

		try {

			connection = dataSource.getConnection();

			bodega.descargarStockProducto(inventario.getCodigo_producto(), inventario.getCodigo_sucursal(),
					inventario.getCantidad_producto_inventario());
			String queryInventario = "INSERT INTO sucursales.inventario (codigo_sucursal,numero_estante,numero_pasillo,codigo_producto,cantidad) VALUES (?,?,?,?,?)";

			PreparedStatement preST = connection.prepareStatement(queryInventario);

			preST.setInt(1, inventario.getCodigo_sucursal());
			preST.setInt(2, inventario.getNumero_estante());
			preST.setInt(3, inventario.getNumero_pasillo());
			preST.setInt(4, inventario.getCodigo_producto());
			preST.setInt(5, inventario.getCantidad_producto_inventario());

			int actualizado = preST.executeUpdate();

			if (actualizado > 0) {
				connection.close();
				return true;

			} else {

				connection.close();
				return false;
			}

		} catch (SQLException e) {

			System.out.print(e);
			return false;
		}

	}

	public Inventario productoInventarioSucursal(int codigo_producto, int codigo_sucursal) {

		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT codigo_sucursal, codigo_producto, nombre, cantidad\r\n"
					+ "FROM sucursales.inventario\r\n"
					+ "INNER JOIN productos.producto ON productos.producto.codigo = sucursales.inventario.codigo_producto\r\n"
					+ "WHERE sucursales.inventario.codigo_sucursal = ?\r\n" + "AND productos.producto.codigo = ?";

			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1, codigo_sucursal);
			preST.setInt(2, codigo_producto);

			resultSet = preST.executeQuery();

			Inventario inventarioProducto = new Inventario();

			while (resultSet.next()) {

				inventarioProducto.setCodigo_producto(resultSet.getInt("codigo_producto"));
				inventarioProducto.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				inventarioProducto.setCantidad_producto_inventario(resultSet.getInt("cantidad"));
				inventarioProducto.setNombre_producto(resultSet.getString("nombre"));
			}

			resultSet.close();
			connection.close();

			return inventarioProducto;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}

	}

	public Boolean actualizarInventarioProducto(Inventario inventario) {

		Connection connection;

		System.out.println(inventario.toString());

		try {

			connection = dataSource.getConnection();

			bodega.descargarStockProducto(inventario.getCodigo_producto(), inventario.getCodigo_sucursal(),
					inventario.getCantidad_producto_bodega());
			String query = "UPDATE sucursales.inventario SET cantidad = ? WHERE codigo_producto  = ? AND codigo_sucursal = ?";

			PreparedStatement preST = connection.prepareStatement(query);

			preST.setInt(1, inventario.getCantidad_producto_inventario());
			preST.setInt(2, inventario.getCodigo_producto());
			preST.setInt(3, inventario.getCodigo_sucursal());

			int actualizado = preST.executeUpdate();

			if (actualizado > 0) {
				connection.close();
				return true;

			} else {

				connection.close();
				return false;
			}

		} catch (SQLException e) {

			System.out.print(e);
			return false;
		}

	}

	public List<Producto> productosVenta(int sucursal) {

		List<Producto> productos = new ArrayList<Producto>();
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT\r\n" + " 	    i.codigo_producto AS codigo_producto,\r\n"
					+ "  p.nombre AS nombre_producto,\r\n" + "  p.precio AS precio_producto\r\n"
					+ "  FROM\r\n" + "   sucursales.inventario i\r\n" + ""
					+ "  INNER JOIN\r\n"
					+ "  productos.producto p ON i.codigo_producto = p.codigo\r\n" + " 	WHERE\r\n"
					+ "  i.codigo_sucursal = ?";

			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1, sucursal);
			resultSet = preST.executeQuery();

			while (resultSet.next()) {
				Producto producto = new Producto();

				producto.setCodigo(resultSet.getInt("codigo_producto"));
				producto.setNombre(resultSet.getString("nombre_producto"));
				producto.setPrecio(resultSet.getDouble("precio_producto"));
				productos.add(producto);
			}

			resultSet.close();
			connection.close();

			return productos;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}

	}

}
