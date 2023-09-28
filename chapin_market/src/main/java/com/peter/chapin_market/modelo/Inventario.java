package com.peter.chapin_market.modelo;

public class Inventario {

	private int codigo_sucursal;
	private int codigo_producto;
	private String nombre_producto;
	private int numero_pasillo;
	private int numero_estante;
	private int cantidad_producto_inventario;
	private int cantidad_producto_bodega;
	
	public int getCodigo_producto() {
		return codigo_producto;
	}
	
	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
	public String getNombre_producto() {
		return nombre_producto;
	}
	
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	
	public int getNumero_pasillo() {
		return numero_pasillo;
	}
	
	public void setNumero_pasillo(int numero_pasillo) {
		this.numero_pasillo = numero_pasillo;
	}
	
	public int getNumero_estante() {
		return numero_estante;
	}
	
	public void setNumero_estante(int numero_estante) {
		this.numero_estante = numero_estante;
	}
	
	public int getCodigo_sucursal() {
		return codigo_sucursal;
	}
	
	public void setCodigo_sucursal(int codigo_sucursal) {
		this.codigo_sucursal = codigo_sucursal;
	}

	public int getCantidad_producto_inventario() {
		return cantidad_producto_inventario;
	}

	public void setCantidad_producto_inventario(int cantidad_producto_inventario) {
		this.cantidad_producto_inventario = cantidad_producto_inventario;
	}

	public int getCantidad_producto_bodega() {
		return cantidad_producto_bodega;
	}

	public void setCantidad_producto_bodega(int cantidad_producto_bodega) {
		this.cantidad_producto_bodega = cantidad_producto_bodega;
	}
	
}
