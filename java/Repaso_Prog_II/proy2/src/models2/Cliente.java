package models2;

public class Cliente {
	private String nombre;
	private String direccion;
	public Cliente(String nom, String dir) {
		this.nombre = nom;
		this.direccion = dir;
	}
	public String ToString() {
		return String.format("%s - %s", this.nombre, this.direccion);
	}
}