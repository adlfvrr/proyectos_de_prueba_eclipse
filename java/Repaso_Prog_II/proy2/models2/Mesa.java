package models2;

public class Mesa extends Producto {
	private double ancho;
	private double grosor;
	
	public Mesa(double precio, double largo, double ancho, double grosor) {
		super(precio,largo);
		this.ancho = ancho;
		this.grosor = grosor;
	}
	
	@Override
	public double Peso() {
		return (this.largo*this.ancho*this.grosor) * 0.3;
	}
	@Override
	public double Precio() {
		return this.Peso() * this.precioBase * 1.25;
	}
}
