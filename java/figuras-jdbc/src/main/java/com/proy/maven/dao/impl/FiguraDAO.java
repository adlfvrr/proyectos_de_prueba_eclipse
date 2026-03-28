package com.proy.maven.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.proy.maven.database.DBConnection;
import com.proy.maven.entity.Circulo;
import com.proy.maven.entity.Figura;
import com.proy.maven.entity.Rectangulo;

public class FiguraDAO implements IFiguraDAO {

	@Override
	public List<Figura> GetAll() {
		
		List<Figura> listaFiguras = new ArrayList<>();
		String sql = "SELECT * FROM Figuras";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				listaFiguras.add(this.mapearFigura(rs));
			}
			
			return listaFiguras;
		}
		catch(SQLException ex) {
			throw new RuntimeException("Error al cargar las figuras", ex);
		}
	}

	@Override
	public Figura GetById(int id) {
		String sql = "SELECT * FROM Figuras WHERE id = ?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return mapearFigura(rs);
			}
			
			return null;
		}
		catch(SQLException ex) {
			throw new RuntimeException("Error al obtener figura con id " + id);
		}
	}

	@Override
	public void Add(Figura nuevo) {
		String sql = "INSERT INTO Figuras (tipo_id, ancho, largo, radio, area) VALUES(?, ?, ?, ?, ?)";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					ps.setInt(1, nuevo.getTipoId());
					ps.setDouble(2, nuevo.getAncho());
					ps.setDouble(3, nuevo.getLargo());
					ps.setDouble(4, nuevo.getRadio());
					ps.setDouble(5, nuevo.getArea());
					
					ps.executeUpdate();
					
					ResultSet keys = ps.getGeneratedKeys();
					if(keys.next()) {
						nuevo.setId(keys.getInt(1));
					}
				}
		catch(SQLException ex) {throw new RuntimeException("Error al insertar figura", ex);}
	}

	@Override
	public void Save(Figura entity) {
		String sql = "UPDATE Figuras SET tipo_id = ?, ancho = ?, largo = ?, radio = ?, area = ? WHERE id = ?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, entity.getTipoId());
			ps.setDouble(2, entity.getAncho());
			ps.setDouble(3, entity.getLargo());
			ps.setDouble(4, entity.getRadio());
			ps.setDouble(5, entity.getArea());
			ps.setInt(6, entity.getId());
			
			ps.executeUpdate();
		}
		catch(SQLException ex) {
			throw new RuntimeException("Error en el update.");
		}
	}

	@Override
	public void Remove(int id) {
		String sql = "DELETE FROM Figuras WHERE id = ?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(SQLException ex) {
			throw new RuntimeException("No se pudo encontrar la figura con el id " + id);
		}
	}
	
	private Figura mapearFigura(ResultSet rs) throws SQLException {
		Figura f = null;
		int tipo = rs.getInt("tipo_id");
		
		if(tipo == 1) {
			f = new Rectangulo();
			f.setAncho(rs.getDouble("ancho"));
			f.setLargo(rs.getDouble("largo"));
		}
		else if (tipo == 2) {
			f = new Circulo();
			f.setRadio(rs.getDouble("radio"));
		}
		else {
			throw new IllegalArgumentException("Tipo de id desconocido (" + tipo + ")");
		}
		
		f.setId(rs.getInt("id"));
		
		return f;
	}
	}
