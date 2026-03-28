package com.proy.maven.dao.impl;

import java.util.List;

import com.proy.maven.entity.Figura;

public interface IFiguraDAO {
	
	public List<Figura> GetAll();
	
	public Figura GetById(int id);
	
	public void Add(Figura nuevo);
	
	public void Save(Figura entity);
	
	public void Remove(int id);
	
}

