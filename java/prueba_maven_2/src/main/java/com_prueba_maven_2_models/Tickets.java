package com_prueba_maven_2_models;

import java.util.Date;

import com_prueba_maven_2_exceptions.CUILInvalidoException;
import com_prueba_maven_2_exceptions.NTarjetaInvalidoException;

public class Tickets {
	
	//Getters and Setters (simplificado)
	
	public double PrecioFinal;
	public String Destino;
	public Transporte Transporte;
	
	private String cuil;
	private String nombre;
	private String telefono;
	private String nTarjeta;
	
	public Tickets(String cuil, String nombre, String telefono, String nTarjeta) {
		if(cuil == null || !validarCUIL(cuil)) throw new CUILInvalidoException("Debe ingresar un CUIL válido");
		if(nTarjeta == null || !validarTarjeta(nTarjeta)) throw new NTarjetaInvalidoException("Debe ingresar un número de tarjeta válido");
		
		this.cuil = cuil;
		this.nombre = nombre;
		this.telefono = telefono;
		this.nTarjeta = nTarjeta;
	}
	
	//Creamos métodos para validación
	
	public boolean validarCUIL(String cuil) {
		return cuil.length() == 11;
	}
	public boolean validarTarjeta(String nTarjeta) {
		return nTarjeta.length() == 16;
	}
	//
	public String ToString() {
		return String.format("%s - %s - %s - %s | Transporte: %s - %s", cuil, nombre, telefono, nTarjeta, this.Transporte, this.Destino);
	}
}
