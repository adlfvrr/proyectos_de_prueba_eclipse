package com.proy.maven.entity;

public class Circulo extends Figura {

	
	public Circulo(int id) {
		super(id);
		this.setTipoId(2);
		}
	
	public Circulo() {
		this.setTipoId(2);
	}
	
	@Override
	public double CalcularArea() {
		return Math.PI * (this.getRadio() * this.getRadio());
	}

	@Override
	public String toString() {
		return String.format("Id: %d - Tipo: Círculo - Radio: %f - Area: %f", this.getId(), this.getRadio(), this.getArea());
	}

}
