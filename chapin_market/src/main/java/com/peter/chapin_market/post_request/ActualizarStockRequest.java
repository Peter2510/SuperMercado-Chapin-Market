package com.peter.chapin_market.post_request;

public class ActualizarStockRequest {

	private String codigo_sucursal;
	private String codigo_producto;
	private String cantidad_producto;

	public String getCodigo_sucursal() {
		return codigo_sucursal;
	}
	
	public void setCodigo_sucursal(String codigo_sucursal) {
		this.codigo_sucursal = codigo_sucursal;
	}
	
	public String getCodigo_producto() {
		return codigo_producto;
	}
	
	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
	public String getCantidad_producto() {
		return cantidad_producto;
	}
	
	public void setCantidad_producto(String cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}
	

}
