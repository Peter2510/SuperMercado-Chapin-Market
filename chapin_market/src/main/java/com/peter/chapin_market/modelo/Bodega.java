package com.peter.chapin_market.modelo;

public class Bodega {

	private int codigo_producto;
	private int codigo_sucursal;
	private String nombre_producto;
	private String descripcion_producto;
	private int cantidad_producto;
	private double precio_unitario;

	public int getCodigo_producto() {
		return codigo_producto;
	}
	
	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
	public int getCodigo_sucursal() {
		return codigo_sucursal;
	}
	
	public void setCodigo_sucursal(int codigo_sucursal) {
		this.codigo_sucursal = codigo_sucursal;
	}
	
	public String getNombre_producto() {
		return nombre_producto;
	}
	
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	
	public String getDescripcion_producto() {
		return descripcion_producto;
	}
	
	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}
	
	public int getCantidad_producto() {
		return cantidad_producto;
	}
	
	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}
	
	public double getPrecio_unitario() {
		return precio_unitario;
	}
	
	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	@Override
	public String toString() {
		return "Bodega [codigo_producto=" + codigo_producto + ", codigo_sucursal=" + codigo_sucursal
				+ ", nombre_producto=" + nombre_producto + ", descripcion_producto=" + descripcion_producto
				+ ", cantidad_producto=" + cantidad_producto + ", precio_unitario=" + precio_unitario + "]";
	}
		
		
}
