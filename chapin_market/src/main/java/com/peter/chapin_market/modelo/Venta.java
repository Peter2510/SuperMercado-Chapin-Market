package com.peter.chapin_market.modelo;

import java.time.LocalDate;

public class Venta {

	private int codigo;
	private int codigo_cajero;
	private String nit_cliente;
	private double total_venta;
	private LocalDate fecha_venta;
	private int codigo_sucursal;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo_cajero() {
		return codigo_cajero;
	}
	
	public void setCodigo_cajero(int codigo_cajero) {
		this.codigo_cajero = codigo_cajero;
	}
	
	public String getNit_cliente() {
		return nit_cliente;
	}
	
	public void setNit_cliente(String nit_cliente) {
		this.nit_cliente = nit_cliente;
	}
	
	public double getTotal_venta() {
		return total_venta;
	}
	
	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}
	
	public LocalDate getFecha_venta() {
		return fecha_venta;
	}
	
	public void setFecha_venta(LocalDate fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	
	public int getCodigo_sucursal() {
		return codigo_sucursal;
	}
	
	public void setCodigo_sucursal(int codigo_sucursal) {
		this.codigo_sucursal = codigo_sucursal;
	}

	@Override
	public String toString() {
		return "Venta [codigo=" + codigo + ", codigo_cajero=" + codigo_cajero + ", nit_cliente=" + nit_cliente
				+ ", total_venta=" + total_venta + ", fecha_venta=" + fecha_venta + ", codigo_sucursal="
				+ codigo_sucursal + "]";
	}
	
}
