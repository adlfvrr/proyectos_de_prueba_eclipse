package com.proy.maven.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("productosPU");
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static void close() {
		emf.close();
	}
}
