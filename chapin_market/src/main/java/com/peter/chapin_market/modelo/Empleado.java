package com.peter.chapin_market.modelo;

public class Empleado {

	private int codigo;
	private String nombre;
	private int rol;
	private int codigo_sucursal;
	private String contrasenia;
	
	public Empleado() {

	}
	
	public Empleado(String nombre, int rol, int codigo_sucursal, String contrasenia) {
		this.nombre = nombre;
		this.rol = rol;
		this.codigo_sucursal = codigo_sucursal;
		this.contrasenia = contrasenia;
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

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
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
