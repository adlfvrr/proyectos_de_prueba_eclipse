package com.proy.maven.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
	
	public static Connection getConnection() {
			try {
				String url = "jdbc:postgresql://localhost:5432/proy_maven_figuras_db";
				String user = "postgres";
				String pass = "perseito";
				return DriverManager.getConnection(url, user, pass);
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				return null;
			}
	}
	
}
