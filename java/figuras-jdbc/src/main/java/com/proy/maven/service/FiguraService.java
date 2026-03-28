package com.proy.maven.service;

import java.util.List;

import com.proy.maven.dao.impl.FiguraDAO;
import com.proy.maven.dao.impl.IFiguraDAO;
import com.proy.maven.entity.*;

public class FiguraService {
	IFiguraDAO iFiguraDAO;
	
	public FiguraService() {
		iFiguraDAO = new FiguraDAO();
	}
	
	public void Crear(Figura f) {
		if(f instanceof Rectangulo) {
			if(f.getLargo() <= 0 && f.getAncho() <= 0) {
				throw new IllegalArgumentException("El ancho y el largo deben ser mayores a 0");
			}
		}
		else if(f instanceof Circulo) {
			if(f.getRadio() <= 0) {
				throw new IllegalArgumentException("El radio debe ser mayor a 0");
			}
		}
		
		iFiguraDAO.Add(f);
	}
	
	public Figura ObtenerPorId(int id) {
		Figura f = null;
		
		if(id <= 0) {
			throw new IllegalArgumentException("El id de la figura no puede ser 0");
		}
		f = iFiguraDAO.GetById(id);
		if(f == null) {
			throw new RuntimeException("No existe una figura con el id " + id);
		}
		
		return f;
	}
	
	public List<Figura> ObtenerTodas(){
		return iFiguraDAO.GetAll();
	}
	
	public boolean Actualizar(Figura f) {
		if(f.getId() <= 0) {
			throw new RuntimeException("El id debe ser mayor a 0");
		}
		
		if(f instanceof Rectangulo) {
			if(f.getLargo() <= 0 && f.getAncho() <= 0) {
				throw new IllegalArgumentException("El ancho y el largo deben ser mayores a 0");
			}
		}
		else if(f instanceof Circulo) {
			if(f.getRadio() <= 0) {
				throw new IllegalArgumentException("El radio debe ser mayor a 0");
			}
		}
		
		iFiguraDAO.Save(f);
		return true;
	}
	
	public boolean Eliminar(int id) {
		if(id <= 0) {
			throw new RuntimeException("El id debe ser mayor a 0");
		}
		ObtenerPorId(id);
		iFiguraDAO.Remove(id);
		return true;
	}
}
