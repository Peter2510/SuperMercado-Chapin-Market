package com.peter.chapin_market.modelo;

public class DetalleVenta {

	private int codigo;
	private int codigo_compra;
	private double subtotal;
	private int cantidad;
	private int precio_unitario;
	private int codigo_producto;
	
	
	public DetalleVenta() {
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo_compra() {
		return codigo_compra;
	}
	
	public void setCodigo_compra(int codigo_compra) {
		this.codigo_compra = codigo_compra;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getPrecio_unitario() {
		return precio_unitario;
	}
	
	public void setPrecio_unitario(int precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	
	public int getCodigo_producto() {
		return codigo_producto;
	}
	
	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
}
