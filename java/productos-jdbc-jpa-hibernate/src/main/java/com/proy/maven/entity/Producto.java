package com.proy.maven.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "precio", nullable = false)
	private double precio;
	@Column(name = "stock", nullable = false)
	private int stock;
	
	//getters and setters
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Producto() {
		
	}
	
	public Producto(String nombre, double precio, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	public String toString() {
		return String.format("Nombre: %s - Precio: $%f.2 - Stock restante: %d", this.getNombre(), this.getPrecio(), this.getStock());
	}
}
