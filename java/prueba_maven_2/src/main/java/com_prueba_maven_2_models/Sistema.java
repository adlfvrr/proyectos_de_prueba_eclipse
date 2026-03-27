package com_prueba_maven_2_models;

import java.util.List;
import java.util.ArrayList;

public class Sistema {
	
	private List<Tickets> tickets;
	
	public int getCantTickets() {
		return tickets.size();
	}
	
	private List<Transporte> transportes;
	
	public Sistema() {
		tickets = new ArrayList<>();
		transportes = new ArrayList<>();
	}
	
	public Tickets AgregarTickets(String cuil, String nombre, String telefono, String nTarjeta, String tipo, String destino) {
		Tickets ticket = new Tickets(cuil, nombre, telefono, nTarjeta);
			Transporte t = BuscarTransporte(destino, tipo);
			if(t != null) {
			ticket.Destino = destino;
			ticket.Transporte = t;
			ticket.PrecioFinal = t.calcularPrecio();
			tickets.add(ticket);
		}
			else {
				throw new RuntimeException(String.format("No existe un %s hacia ese destino programado.", tipo));
			}
			return ticket;
	}
	
	public Tickets VerTicket(int idx) {
		return tickets.get(idx);
	}
	
	public void AgregarTransporte(Transporte transporte) {
		transportes.add(transporte);
	}
	
	public Transporte BuscarTransporte(String destino, String tipo) {
		Transporte transporte = null;
		for(int i = 0; i < transportes.size(); i++) {
			if(transportes.get(i).destino == destino && ((transportes.get(i) instanceof Bus && tipo == "Bús") || (transportes.get(i) instanceof Avion && tipo == "Avión"))) {
				transporte = transportes.get(i);
			}
		}
		return transporte;
	}
	
	public Transporte VerTransporte(int i) {
		return transportes.get(i);
	}
	
	public int VerCantTransportes() {
		return transportes.size();
	}
	
	public String[] ResumenTickets(){
		String[] resumenLines = new String[tickets.size()];
		for(int i = 0; i < resumenLines.length; i++) {
			String ticketString = tickets.get(i).ToString();
			resumenLines[i] = ticketString;
		}
		return resumenLines;
	}
	
	public String[] ResumenTransportes() {
		String[] resumenLines = new String[transportes.size()];
		for(int i = 0; i < resumenLines.length; i++) {
			String transporteString = transportes.get(i).toString();
			resumenLines[i] = transporteString;
		}
		return resumenLines;
	}
	
	public Transporte ImportTransporte(String s) {
		
		Transporte t = null;
		String[] stringFormatted = s.split(";");
		String destino;
		double precioBase;
		String patente;
		int clase;
		if(stringFormatted.length == 3) {
			destino = stringFormatted[0];
			precioBase = Double.parseDouble(stringFormatted[1]);
			patente = stringFormatted[2];
			t = new Avion(precioBase, destino, patente);
		}
		else {
			if(stringFormatted.length == 4) {
				destino = stringFormatted[0];
				precioBase = Double.parseDouble(stringFormatted[1]);
				patente = stringFormatted[2];
				clase = Integer.parseInt(stringFormatted[3]);
				t = new Bus(precioBase, destino, patente, clase);
			}
		}
		return t;
	}
}
