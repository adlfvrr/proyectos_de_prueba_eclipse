package com.proy.maven.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DIGITAL")

public class ProductoDigital extends Producto {

	@Column(name = "url_descarga")
	private String urlDescarga;
	
	public ProductoDigital() {
		
	}
	
	public ProductoDigital(String nombre, double precio, int stock, String url) {
		super(nombre, precio, stock);
		this.urlDescarga = url;
	}
	
	public String getUrlDescarga() {
		return this.urlDescarga;
	}
	
	public void setUrlDescarga(String url) {
		this.urlDescarga = url;
	}
	
	@Override
	public String toString() {
	    return super.toString() + " | URL: " + this.getUrlDescarga();
	}
	
}
