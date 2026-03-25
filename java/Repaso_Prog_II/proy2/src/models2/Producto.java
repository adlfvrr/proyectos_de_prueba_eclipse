package models2;

public abstract class Producto implements Comparable<Producto> {
	protected double precioBase;
	protected double largo;
	
	private int codigo;
	public int getCodigo() {
		return this.codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Producto(double precio, double largo) {
		this.precioBase = precio;
		this.largo = largo;
	}
	public abstract double Peso();
	public abstract double Precio();
	
	@Override
	public int compareTo(Producto other) {
		if (other != null) {
			return Integer.compare(this.getCodigo(), other.getCodigo()); 
		}
		return -1;
	}
}
