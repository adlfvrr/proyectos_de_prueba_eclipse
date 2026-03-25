package models2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.PresupuestoInexistenteException;
import exceptions.ProductoInexistenteException;

public class Presupuesto {
	
	private List<Producto> listaProductos;
	
	private Cliente solicitante;
	
	public double getPrecio() {
		double precio = 0;
		for(Producto p : listaProductos) {
			precio += p.Precio();
		}
		return precio;
	}
	
	public Presupuesto(String nombre, String direccion) {
		if(nombre == null || direccion == null) throw new PresupuestoInexistenteException("Introduzca un nombre/dirección válidos.");
		this.solicitante = new Cliente(nombre, direccion);
		listaProductos = new ArrayList<>();
	}
	
	public void AgregarProducto(Producto unProducto) {
		if(unProducto == null) throw new ProductoInexistenteException("Se debe agregar un producto.");
		listaProductos.add(unProducto);
	}
	
	public boolean QuitarProducto(int codigo) {
		boolean borrado = false;
		listaProductos.sort(null);
		Producto busqueda = new Banco(0,0);
		busqueda.setCodigo(codigo);
		int idx = Collections.binarySearch(listaProductos, busqueda);
		if (idx >-1) {
			listaProductos.remove(idx);
			borrado = true;
		}
		return borrado;
	}
	
	public Producto BuscarProducto(int codigo) {
		int cont = 0;
		while(cont < listaProductos.size()) {
			if(listaProductos.get(cont).getCodigo() == codigo) {
				return listaProductos.get(cont);
			}
			cont++;
		}
		return null;
		}
	
	public String[] Resumen() {
		String[] lines = new String[listaProductos.size() + 2];
		lines[0] = "Presupuesto de cliente: " + solicitante.ToString();
		for(int i = 0; i < listaProductos.size();i++) {
			String producto = "";
			if(listaProductos.get(i) != null) {
				producto = (listaProductos.get(i) instanceof Mesa) ? "Mesa" : "Silla";
			}
			lines[i + 1] = String.format("Producto: %s - Precio: $%.2f - Codigo: %d", producto, listaProductos.get(i).precioBase, listaProductos.get(i).getCodigo());
		}
		lines[listaProductos.size() + 1] = String.format("Precio total: %.2f", this.getPrecio());
		return lines;
	}
	
	
}
