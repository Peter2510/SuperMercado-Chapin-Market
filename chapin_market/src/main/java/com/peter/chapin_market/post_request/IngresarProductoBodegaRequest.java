package com.peter.chapin_market.post_request;

public class IngresarProductoBodegaRequest {
	
	private int codigo_producto;
	private int codigo_sucursal;
	private int cantidad;
	
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
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
