package models;

public class Encuesta implements Comparable<Encuesta> {
	private boolean usaBicicleta;
	private boolean usaAuto;
	private boolean usaTransportePublico;
	private String email;
	private double distanciaASuDestino;
	
	//creamos el objeto
	public Encuesta() {
		this.usaBicicleta = false;
		this.usaTransportePublico = false;
		this.usaAuto = false;
		this.email = "";
		this.distanciaASuDestino = 0;
	}
	//definimos getters and setters
	public boolean getUsaBicicleta() {
		return this.usaBicicleta;
	}
	public void setUsaBicicleta(boolean encuesta) {
		this.usaBicicleta = encuesta;
	}
	//
	public boolean getUsaAuto() {
		return this.usaAuto;
	}
	public void setUsaAuto(boolean encuesta) {
		this.usaAuto = encuesta;
	}
	public boolean getUsaTransportePublico() {
		return this.usaTransportePublico;
	}
	public void setUsaTransportePublico(boolean encuesta) {
		this.usaTransportePublico = encuesta;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getDistancia() {
		return this.distanciaASuDestino;
	}
	public void setDistancia(double distancia) {
		this.distanciaASuDestino = distancia;
	}
	
	@Override
	public int compareTo(Encuesta other) {
		if(other !=null) return Double.compare(this.distanciaASuDestino, other.distanciaASuDestino);
		return -1;
	}
	
}
