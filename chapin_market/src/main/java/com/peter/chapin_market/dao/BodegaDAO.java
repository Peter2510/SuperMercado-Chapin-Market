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
import com.peter.chapin_market.modelo.Producto;

@Service
public class BodegaDAO {

	@Autowired
	private DataSource dataSource; // Inyecta el DataSource "configuracion de la base de datos"
	

 	public List<Bodega> getProductos(int codigo_sucursal) {
		
		List<Bodega> productos = new ArrayList<Bodega>();
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT codigo_sucursal, codigo_producto, nombre, cantidad_producto, precio FROM sucursales.bodega INNER JOIN productos.producto ON productos.producto.codigo = sucursales.bodega.codigo_producto WHERE sucursales.bodega.codigo_sucursal = ?"; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo_sucursal);
			resultSet = preST.executeQuery();
			

			while (resultSet.next()) {
				Bodega bodega = new Bodega();
				bodega.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				bodega.setCodigo_producto(resultSet.getInt("codigo_producto"));
				bodega.setNombre_producto(resultSet.getString("nombre"));
				bodega.setCantidad_producto(resultSet.getInt("cantidad_producto"));
				bodega.setPrecio_unitario(resultSet.getInt("precio"));

				productos.add(bodega);
			}

			resultSet.close();
			connection.close();

			return productos;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
	}
 	
 	public Boolean insertarBodega(Bodega bodega) {
		
		Connection connection;

		try {

			System.out.println(bodega.toString());
			
			connection = dataSource.getConnection();
			String query = "INSERT INTO sucursales.bodega (codigo_producto,cantidad_producto,codigo_sucursal) VALUES (?,?,?)" ; 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,bodega.getCodigo_producto());
			preST.setInt(2,bodega.getCantidad_producto());
			preST.setInt(3,bodega.getCodigo_sucursal());

			
			int insertado = preST.executeUpdate();
			
			if(insertado>0) {			
				connection.close();
				return true;
				
			}else {
				
				connection.close();
				return false;
			}

									
		} catch (SQLException e) {

			System.out.print(e);
			return false;
		}
		
	}
 	
 	
 	public List<Producto> productosDisponiblesParaAgregar(int codigo_sucursal) {
		
 		List<Producto> productos = new ArrayList<Producto>();
		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT p.*\r\n"
					+ "FROM productos.producto p\r\n"
					+ "WHERE p.codigo NOT IN (\r\n"
					+ "    SELECT pb.codigo_producto\r\n"
					+ "    FROM sucursales.bodega pb\r\n"
					+ "    WHERE pb.codigo_sucursal = ?"
					+ ")"; 
			
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo_sucursal);
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
 	
 	public Bodega productoBodegaSucursal(int codigo_producto, int codigo_sucursal) {
		
 		Connection connection;
		ResultSet resultSet;

		try {

			connection = dataSource.getConnection();
			String query = "SELECT codigo_sucursal, codigo_producto, nombre, cantidad_producto, precio \r\n"
					+ "FROM sucursales.bodega \r\n"
					+ "INNER JOIN productos.producto ON productos.producto.codigo = sucursales.bodega.codigo_producto \r\n"
					+ "WHERE sucursales.bodega.codigo_sucursal = ? \r\n"
					+ "AND productos.producto.codigo = ?"; 
			 
			PreparedStatement preST = connection.prepareStatement(query);
			preST.setInt(1,codigo_sucursal);
			preST.setInt(2,codigo_producto);
			

			resultSet = preST.executeQuery();
			
			Bodega bodegaProducto = new Bodega();
			
			while (resultSet.next()) {
								
				bodegaProducto.setCodigo_producto(resultSet.getInt("codigo_producto"));
				bodegaProducto.setCodigo_sucursal(resultSet.getInt("codigo_sucursal"));
				bodegaProducto.setCantidad_producto(resultSet.getInt("cantidad_producto"));
				bodegaProducto.setNombre_producto(resultSet.getString("nombre"));
			}

			resultSet.close();
			connection.close();

			return bodegaProducto;

		} catch (SQLException e) {

			System.out.print(e);
			return null;
		}
 	
 	}
 	
 	
 	public Boolean actualizarStockProducto(int codigo_producto, int codigo_sucursal, int cantidadProducto) {
 		
		Connection connection;

		try {

			connection = dataSource.getConnection();
			String query = "UPDATE sucursales.bodega SET cantidad_producto = ? WHERE codigo_producto  = ? AND codigo_sucursal = ?"; 
			
			PreparedStatement preST = connection.prepareStatement(query);
			
			preST.setInt(1,cantidadProducto);
			preST.setInt(2,codigo_producto);
			preST.setInt(3,codigo_sucursal);
			
			int actualizado = preST.executeUpdate();
			
			if(actualizado>0) {			
				connection.close();
				return true;
				
			}else {
				
				connection.close();
				return false;
			}

									
		} catch (SQLException e) {

			System.out.print(e);
			return false;
		}
 		
 	}
	
		
	
}
