/**
 * 
 */
package com.github.mlaursen.sample.eao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author mlaursen
 *
 */
public abstract class BaseEAO<T> {
  
  /* Ha ha ha ha ha ha */
  private final static String UNIT_NAME = "BetteruPU";
  
  @PersistenceContext(unitName = UNIT_NAME)
  protected EntityManager em;
  
  private Class<T> entityClass;
  public BaseEAO(Class<T> entityClass) {
    this.entityClass = entityClass;
  }
  
  public void add(T entity) {
    em.persist(entity);
  }
  
  public void remove(T entity) {
    T remove = em.merge(entity);
    em.remove(remove);
  }
  
  public T update(T entity) {
    return em.merge(entity);
  }
  
  public T findById(long id) {
    return em.find(entityClass, id);
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<T> findAll() {
    CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return (List<T>) em.createQuery(cq).getResultList();
  }
  
  public T findOneResult(String namedQuery, Map<String, Object> parameters) {
    T result = null;
    try {
      Query q = em.createNamedQuery(namedQuery);
      if(parameters != null && !parameters.isEmpty()) {
        populateQueryParameters(q, parameters);
      }
      result = (T) q.getSingleResult();
    }
    catch(Exception e) {
      System.out.println("Error while running query: " + e.getMessage());
      e.printStackTrace();
    }
    return result;
  }
  
  private void populateQueryParameters(Query q, Map<String, Object> parameters) {
    for(Entry<String, Object> e : parameters.entrySet()) {
      q.setParameter(e.getKey(), e.getValue());
    }
  }
}
