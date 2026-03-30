package com.proy.maven.dao;

import java.util.List;

import com.proy.maven.database.JPAUtil;
import com.proy.maven.entity.Producto;

import jakarta.persistence.EntityManager;

public class ProductoDAO implements IProductoDAO {

	private EntityManager getEM() {
		return JPAUtil.getEntityManagerFactory().createEntityManager();
	}
	public void add(Producto producto) {
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			em.persist(producto);
			em.getTransaction().commit();
		}
		catch(Exception ex) {
			em.getTransaction().rollback();
			throw new RuntimeException("Error al insertar producto", ex);
		}
		finally {
			em.close();
		}
		
	}

	@Override
	public Producto getById(int id) {
		EntityManager em = getEM();
		try {
			Producto p = em.find(Producto.class, id);
			return p;
		}
		catch(Exception ex) {
			throw new RuntimeException("Error al encontrar la figura con id " + id, ex);
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Producto> getAll() {
		List<Producto> listaProductos;
		EntityManager em = getEM();
		try {
			listaProductos = em.createQuery("FROM Producto", Producto.class).getResultList();
			return listaProductos;
		}
		catch(Exception ex) {
			throw new RuntimeException("Error al mostrar las figuras", ex);
		}
		finally {
			em.close();
		}
	}

	@Override
	public void save(Producto producto) {
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			em.merge(producto);
			em.getTransaction().commit();
		}
		catch(Exception ex) {
			em.getTransaction().rollback();
			throw new RuntimeException("Error al actualizar la figura " + ex);
		}
		finally {
			em.close();
		}
	}

	@Override
	public void remove(int id) {
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			em.remove(em.find(Producto.class, id));
			em.getTransaction().commit();
		}
		catch(Exception ex) {
			em.getTransaction().rollback();
			throw new RuntimeException("Error al remover la figura " + ex);
		}
		finally {
		em.close();
		}
		
	}

}
