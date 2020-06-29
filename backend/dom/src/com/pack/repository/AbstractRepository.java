package com.pack.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class AbstractRepository<T>{

	@Inject
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	public T insert(T entity) throws Exception  {
		try {
			em.persist(entity);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return entity;
	}


}
