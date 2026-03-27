package com_prueba_maven_2_models;

public class Avion extends Transporte {
	
	private String patente;
	
	@Override
	public String getPatente() {
		return this.patente;
	}
	
	
	public Avion(double precioBase, String destino, String nIdentificacion) {
		super(precioBase, destino);
		this.patente = nIdentificacion;
	}
	
	
	@Override
	public double calcularPrecio() {
		return this.precioBase * 1.30 * 1.21;
	}


	@Override
	public String toString() {
		return String.format("%s - %f", this.patente, this.precioBase);
	}

}
