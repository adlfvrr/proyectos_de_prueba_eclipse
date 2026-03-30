package com.proy.maven.dao;

import java.util.List;

import com.proy.maven.entity.Producto;

public interface IProductoDAO {
	void add(Producto producto);
	Producto getById(int id);
	List<Producto> getAll();
	void save(Producto producto);
	void remove(int id);
}
