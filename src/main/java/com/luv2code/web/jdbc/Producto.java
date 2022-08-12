package com.luv2code.web.jdbc;

public class Producto {

	private int id;
	private String nombre;
	private String descripcion;
	private int stock;

	public Producto(String nombre, String descripcion, int stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
	}

	public Producto(int id, String nombre, String descripcion, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String name) {
		this.nombre = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String description) {
		this.descripcion = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock + "]";
	}
}
