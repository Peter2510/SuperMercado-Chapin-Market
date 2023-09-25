package com.peter.chapin_market.modelo;

import java.util.Collection;

public class Empleado{

	private int codigo;
	private String nombre;
	private int codigo_rol;
	private int codigo_sucursal;
	private String contrasenia;
	private int caja;
	
	public Empleado() {

	}
	
	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo_rol() {
		return codigo_rol;
	}

	public void setCodigo_rol(int codigo_rol) {
		this.codigo_rol = codigo_rol;
	}

	public int getCodigo_sucursal() {
		return codigo_sucursal;
	}

	public void setCodigo_sucursal(int codigo_sucursal) {
		this.codigo_sucursal = codigo_sucursal;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
}
