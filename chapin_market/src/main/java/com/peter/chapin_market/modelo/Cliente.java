package com.peter.chapin_market.modelo;

public class Cliente {
	
	private String nombre;
	private int puntos;
	private String nit;
	private double compras;
	private int tipo_tarjeta;
	

	public Cliente() {

	}

	public Cliente(String nombre, int puntos, String nit, double compras, int tipo_tarjeta) {
		this.nombre = nombre;
		this.puntos = puntos;
		this.nit = nit;
		this.compras = compras;
		this.tipo_tarjeta = tipo_tarjeta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public double getCompras() {
		return compras;
	}

	public void setCompras(double compras) {
		this.compras = compras;
	}

	public int getTipo_tarjeta() {
		return tipo_tarjeta;
	}

	public void setTipo_tarjeta(int tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", puntos=" + puntos + ", nit=" + nit + ", compras=" + compras
				+ ", tipo_tarjeta=" + tipo_tarjeta + "]";
	}
	
		
	
}
