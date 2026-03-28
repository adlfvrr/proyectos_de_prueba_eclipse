package com.proy.maven.entity;

public	abstract class Figura {
	private int id;
	private int tipoId;
	private double largo;
	private double ancho;
	private double radio;
	
	public Figura(int id) {
		this.id = id;
	}
	public Figura() {
		
	}
	
	public abstract double CalcularArea();
	public abstract String toString();
	
	//getters and setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getArea() {
		return this.CalcularArea();
	}
	
	public int getTipoId() {
		return this.tipoId;
	}
	
	public void setTipoId(int tipo) {
		this.tipoId = tipo;
	}
	public double getLargo() {
		return largo;
	}
	public void setLargo(double largo) {
		this.largo = largo;
	}
	public double getAncho() {
		return ancho;
	}
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}

	
}
