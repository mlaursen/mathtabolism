/**
 * 
 */
package com.mathtabolism.eao;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author mlaursen
 *
 */
public abstract class BaseEAO<T> {
	
	private final static String UNIT_NAME = "MathtabolismPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	protected BaseEAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * Adds an entity in the Database
	 * 
	 * @param entity
	 *          the entity to save
	 */
	public void create(T entity) {
		em.persist(entity);
	}
	
	/**
	 * Removes an entity from the Database
	 * 
	 * @param entity
	 *          the entity to remove
	 */
	public void remove(T entity) {
		T remove = em.merge(entity);
		em.remove(remove);
	}
	
	/**
	 * Updates an entity in the Database
	 * 
	 * @param entity
	 *          the entity to update
	 * @return the updated entity
	 */
	public T update(T entity) {
		return em.merge(entity);
	}
	
	/**
	 * Attempts to find an entity from the Datbase by the given id.
	 * 
	 * @param id
	 *          the id to search by
	 * @return an entity with the id or null
	 */
	public T findById(Long id) {
		return em.find(entityClass, id);
	}
	
	/**
	 * Attempts to create a named query
	 * 
	 * @param namedQuery
	 *          a named query to create
	 * @param parameters
	 *          the parameters to bind
	 * @return a Single entity or null
	 */
	public T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		try {
			TypedQuery<T> q = em.createNamedQuery(namedQuery, this.entityClass);
			if(parameters != null && !parameters.isEmpty()) {
				bindParameters(q, parameters);
			}
			result = q.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Binds the parameters to a query
	 * 
	 * @param q
	 *          the query to bind to
	 * @param parameters
	 *          the parameters to bind
	 */
	protected void bindParameters(Query q, Map<String, Object> parameters) {
		for(Entry<String, Object> e : parameters.entrySet()) {
			q.setParameter(e.getKey(), e.getValue());
		}
	}
}
