package com_prueba_maven_2_models;

public abstract class Transporte implements Comparable<Transporte>{

	protected double precioBase;
	protected String destino;
	
	public String getDestino() {
		return this.destino;
	}
	
	public Transporte(double precioBase, String destino) {
		this.precioBase = precioBase;
		this.destino = destino;
	}
	
	public abstract double calcularPrecio();
	
	public int compareTo(Transporte other) {
		if (other != null) return this.destino.compareTo(other.destino);
		return -1;
	}
	
	public abstract String getPatente();
	
	public abstract String toString();
}
