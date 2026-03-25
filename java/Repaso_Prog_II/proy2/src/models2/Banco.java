package models2;

public class Banco extends Producto {

	public Banco(double precio, double largo) {
		super(precio, largo);
	}
	
	@Override
	public double Peso() {	
		return (this.largo*0.25) * 0.42;
	}
	@Override
	public double Precio() {
		return this.Peso() * this.precioBase * 1.15;
	}
}
