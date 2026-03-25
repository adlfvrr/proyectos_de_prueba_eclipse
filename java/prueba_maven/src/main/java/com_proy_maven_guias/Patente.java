package com_proy_maven_guias;

import com_proy_maven_interfaces.IPatente;

public class Patente implements IPatente {
	public String descripcion;
	public Patente(String patente) {
		this.descripcion = validarPatente(patente);
	}
	public String validarPatente(String pat) {
		String desc = "No reconocido";
		String patenteFormat = pat.trim().replace(" ", "").toUpperCase();
		if(patenteFormat.matches("[A-Z]{3}\\d{3}")) desc = "Automóviles y camionetas hasta 2016";
		else if(patenteFormat.matches("[A-Z]{2}\\d{3}[A-Z]{2}")) desc = "Automóviles y camionetas desde 2016";
		else if(patenteFormat.matches("[A-Z]{2}\\d{3}[A-Z]{3}")) desc = "Motocicleta";
		else if(patenteFormat.matches("[A-Z]{2}\\d{4}")) desc = "Acoplado";
		return desc;
	}
		
}
