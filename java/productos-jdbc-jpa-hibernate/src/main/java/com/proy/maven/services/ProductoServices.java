package com.proy.maven.services;

import java.util.List;

import com.proy.maven.dao.IProductoDAO;
import com.proy.maven.dao.ProductoDAO;
import com.proy.maven.entity.Producto;

public class ProductoServices {
	private IProductoDAO dao;
	
	public ProductoServices() {
		this.dao = new ProductoDAO();
	}
	
	public List<Producto> obtenerTodos(){
		return this.dao.getAll();
	}
	
	public Producto obtenerPorId(int id) {
		if(id <= 0) {
			throw new RuntimeException("Id inexistente");
		}
		Producto p = this.dao.getById(id);
		if(p == null) {
			throw new RuntimeException("La figura con id " + id + " no existe");
		}
		return p;
	}
	
	public boolean añadirProducto(Producto p) {
		if(p == null) {
			throw new RuntimeException("Error al agregar el producto");
		}
		 if(p.getNombre() == null || p.getNombre().isEmpty()) 
		        throw new RuntimeException("El nombre es obligatorio");
		    if(p.getPrecio() <= 0) 
		        throw new RuntimeException("El precio debe ser mayor a 0");
		    if(p.getStock() < 0) 
		        throw new RuntimeException("El stock no puede ser negativo");
		this.dao.add(p);
		return true;
	}
	
	public boolean actualizarProducto(Producto p) {
		
		if(p == null) {
			throw new RuntimeException("El producto no existe");
		}
		this.dao.save(p);
		return true;
	}
	
	public boolean eliminarProducto(int id) {
		if(id <= 0) {
			throw new RuntimeException("Id inexistente");
		}
		this.dao.remove(id);
		return true;
	}
}
