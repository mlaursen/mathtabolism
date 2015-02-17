/**
 * 
 */
package com.mathtabolism.api.crud;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.mathtabolism.api.ResponseBuilder;
import com.mathtabolism.entity.GeneratedEntity;
import com.mathtabolism.util.db.MathtabolismDB;

/**
 * @author mlaursen
 *
 */
public abstract class GenericCRUDResource<E extends GeneratedEntity> implements ApiCRUDResource<E> {
  private static Logger logger = Logger.getLogger(GenericCRUDResource.class);
  
  @MathtabolismDB
  @PersistenceContext(unitName = "MathtabolismPU")
  protected EntityManager em;
  private Class<E> entityClass;
  
  protected GenericCRUDResource(Class<E> entityClass) {
    this.entityClass = entityClass;
  }
  
  @Override
  public Response create(E entity) {
    Status status = Status.BAD_REQUEST;
    if(entity != null) {
      if(entity.getId() != null) {
        return ResponseBuilder.buildResponse(status, "The given entity has an id. This should be an update call instead of create");
      }
      try {
        em.persist(entity);
        
        status = Status.CREATED;
      } catch(EJBException e) {
        logger.error(e);
        
        status = Status.INTERNAL_SERVER_ERROR;
      }
    }
    
    
    return ResponseBuilder.buildResponse(status, entity);
  }
  
  @Override
  public E retrieve(Long id) {
    return findById(id);
  }
  
  @Override
  public Response update(E entity, Long entityId) {
    Status status = Status.INTERNAL_SERVER_ERROR;
    E fromDB = findById(entityId);
    if(fromDB == null) {
      status = Status.NOT_FOUND;
    } else {
      entity.setId(entityId);
      
      try {
        em.merge(entity);
        
        status = Status.OK;
      } catch(EJBException e) {
        logger.error(e);
      }
    }
    
    return ResponseBuilder.buildResponse(status, entity);
  }
  
  @Override
  public Response delete(Long id) {
    Status status = Status.INTERNAL_SERVER_ERROR;
    E fromDB = findById(id);
    if(fromDB == null) {
      status = Status.NOT_FOUND;
    } else {
      try {
        em.remove(fromDB);
        
        status = Status.OK;
      } catch(EJBException e) {
        logger.error(e);
        
      }
    }
    return ResponseBuilder.buildResponse(status, fromDB);
  }
  
  /**
   * Attempts to find an entity by the given id
   * @param id the id 
   * @return the entity or null
   */
  protected E findById(Long id) {
    return em.find(entityClass, id);
  }

}
