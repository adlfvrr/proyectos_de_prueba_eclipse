package com.proy.maven.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FISICO")

public class ProductoFisico extends Producto {
	
	@Column(name = "peso")
	private double peso;
	
	public ProductoFisico() {
		
	}
	public ProductoFisico(String nombre, double precio, int stock, double peso) {
		super(nombre, precio, stock);
		this.peso = peso;
	}
	
	public void setpeso(double peso) {
		this.peso = peso;
	}
	public double getPeso() {
		return this.peso;
	}
	
	@Override
	public String toString() {
	    return super.toString() + " | Peso: " + peso + "kg";
	}
}
