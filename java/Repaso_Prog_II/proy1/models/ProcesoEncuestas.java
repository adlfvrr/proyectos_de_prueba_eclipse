package models;

import java.util.ArrayList;
import java.util.List;


public class ProcesoEncuestas {
	
	private int cantContactables;
	public int getCantContactables() {
		return contactables.size();
				}
	
	private int cantEncuestados;
	public int getCantidadEncuestados() {
		return this.cantEncuestados;
	}
	 //readonlys
	public double getPorcBicicleta() {
		return this.cantBicicleta / this.cantContactables;
	}
	
	public double getPorcAuto() {
		return  this.cantAuto / this.cantContactables;
	}
	
	public double getPorcTransPub() {
		return this.cantTransPub / this.cantContactables;
	}
	
	//declaramos contadores
	private int cantBicicleta;
	private int cantAuto;
	private int cantTransPub;
	
	private List<Encuesta> contactables;
	
	//creamos el objeto
	public ProcesoEncuestas() {
		this.cantEncuestados = 0;
		this.cantContactables = 0;
		this.cantBicicleta = 0;
		this.cantAuto = 0;
		this.cantTransPub = 0;
		this.contactables = new ArrayList<>();
		
	}
	
	public void RegistrarEncuesta(Encuesta encuesta, boolean puedeSerContactado) {
			if(encuesta.getUsaAuto() == true) this.cantAuto++;
			if(encuesta.getUsaBicicleta() == true) this.cantBicicleta++;
			if(encuesta.getUsaTransportePublico() == true) this.cantTransPub++;
			if(puedeSerContactado) contactables.add(encuesta);
			this.cantEncuestados++;
		}
	public Encuesta VerContactable(int idx) {
		return contactables.get(idx);
	}
	public void OrdenarEncuestables() {
		contactables.sort(null);
	}
}
