package br.imd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.imd.model.LogDatabase;

public class GenericDao {
	protected EntityManager em;
	private LogDatabase log;

	public void save(Object object) {
		em = Connection.getInstance();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
	}
	
	public void update(Object object) {
		em = Connection.getInstance();
		em.getTransaction().begin();
		em.merge(object);
		em.getTransaction().commit();
	}

	public void remove(Object object) {
		em = Connection.getInstance();
		em.getTransaction().begin();
		em.remove(object);
		em.getTransaction().commit();
	}
	
	public void saveLog(LogDatabase log){
		this.log = log;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoChansey");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(log);
		em.getTransaction().commit();
		em.close();
	}
}
