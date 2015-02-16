/**
 * 
 */
package com.mathtabolism.api.crud;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mathtabolism.util.db.MathtabolismDB;

/**
 * @author mlaursen
 *
 */
@Path("/crud")
public abstract class GenericCRUDResource<E> implements ApiCRUDResource<E> {
  
  @Inject
  @MathtabolismDB
  protected EntityManager em;
  private Class<E> entityClass;
  
  protected GenericCRUDResource(Class<E> entityClass) {
    this.entityClass = entityClass;
  }
  
  protected Response buildResponse(Status status, Object additional) {
    return Response.status(status).entity(additional).build();
  }
  
  @Override
  public Response create(E entity) {
    Status status = Status.BAD_REQUEST;
    try {
      em.persist(entity);
      status = Status.CREATED;
    } catch(EJBException e) {
    }
    
    return buildResponse(status, entity);
  }
  
  @Override
  public Response update(E entity, Long entityId) {
    Status status = Status.BAD_REQUEST;
    E fromDB = findById(entityId);
    if(fromDB == null) {
      status = Status.NOT_FOUND;
    }
    // TODO Auto-generated method stub
    return null;
  }
  
  protected E findById(Long id) {
    return em.find(entityClass, id);
  }
  
//  @Override
//  public void put(E entity) {
//    em.persist(entity);
//  }
//  
//  @Override
//  public E update(E entity) {
//    return em.merge(entity);
//  }
//
//  @Override
//  public void delete(E entity) {
//    em.remove(update(entity));
//  }
//  
//  @Override
//  public E getById(long id) {
//    return em.find(entityClass, id);
//  }

}
