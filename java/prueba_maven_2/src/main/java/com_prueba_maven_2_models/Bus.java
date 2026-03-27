package com_prueba_maven_2_models;

public class Bus extends Transporte{
	
	private String patente;
	private int clase;
	
	public String getPatente() {
		return this.patente;
	}
	public int getClase() {
		return this.clase;
	}

	public Bus(double precioBase, String destino, String patente, int clase) {
		super(precioBase, destino);
		this.patente = patente;
		this.clase = clase;
	}

	@Override
	public double calcularPrecio() {
		return this.precioBase * 11.5;
	}
	@Override
	public String toString() {
		return String.format("%s - %f - %d", this.patente, this.precioBase, this.clase);
	}
	
}
