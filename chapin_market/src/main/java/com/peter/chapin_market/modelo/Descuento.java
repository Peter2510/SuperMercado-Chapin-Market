package com.peter.chapin_market.modelo;

import java.time.LocalDate;

public class Descuento {

	private int codigo_compra;
	private LocalDate fecha_descuento;
	private double total;
		
	public Descuento() {
		
	}

	public int getCodigo_compra() {
		return codigo_compra;
	}
	
	public void setCodigo_compra(int codigo_compra) {
		this.codigo_compra = codigo_compra;
	}
	
	public LocalDate getFecha_descuento() {
		return fecha_descuento;
	}
	
	public void setFecha_descuento(LocalDate fecha_descuento) {
		this.fecha_descuento = fecha_descuento;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
}
