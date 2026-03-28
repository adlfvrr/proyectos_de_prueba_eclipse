package com.proy.maven.entity;

public class Rectangulo extends Figura {
	
	
	public Rectangulo() {
		this.setTipoId(1);
	}
	
	public Rectangulo(int id) {super(id);
	this.setTipoId(1);}
	
	@Override
	public double CalcularArea() {
		return this.getLargo() * this.getAncho();
	}

	@Override
	public String toString() {
		return String.format("Id: %d - Tipo: Rectángulo - Largo: %f - Ancho: %f - Area: %f", this.getId(), this.getLargo(), this.getAncho(), this.getArea());
	}

}
