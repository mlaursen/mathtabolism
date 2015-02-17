/**
 * 
 */
package com.mathtabolism.api.crud;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mathtabolism.entity.GeneratedEntity;

/**
 * @author mlaursen
 *
 */
public interface ApiCRUDResource<E extends GeneratedEntity> {
  /**
   * Attempts to create an entity from JSON. If there was a successful creation,
   * the {@link HttpServletResponse#SC_CREATED} should be returned. Otherwise,
   * {@link HttpServletResponse#SC_BAD_REQUEST} will be returned.
   * 
   * <p>The url should look as follows:
   * <code><pre>
   *    /api/{entityName}/create
   * </pre></code>
   * 
   * @param entity the entity to create
   * @return a HttpServletResponse representing the result of the creation action
   */
  @POST
  @Path("/create")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  Response create(E entity);
  
  /**
   * Attempts to find an entity by the given id.
   * 
   * <p>The url should look as follows:
   * <code><pre>
   *    /api/{entityName}/{entityId}
   * </pre></code>
   * @param id the entity's id
   * @return the entity or null
   */
  @GET
  @Path("/{entityId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  E retrieve(@PathParam("entityId") Long id);
  
  /**
   * Attempts to update an entity by entity id and JSON data. If the entity 
   * was successfully updated, {@link HttpServletResponse#SC_CREATED} should be
   * returned. Otherwise {@link HttpServletResponse#SC_BAD_REQUEST} will
   * be returned.
   * 
   * <p>The url should look as follows:
   * <code><pre>
   *    /api/{entityName}/{entityId}
   * </pre></code>
   * 
   * @param entity the entity to update
   * @param entityId the entity's id
   * @return a HttpServletResponse representing the result of the update action
   */
  @POST
  @Path("/{entityId}")
  Response update(E entity, @PathParam("entityId") Long entityId);
  
  /**
   * Attempts to delete an entity by the given entity id.
   * 
   * <p>The url should look as follows:
   * <code><pre>
   *    /api/{entityName}/{entityId}
   * </pre></code>
   * @param id the entity's id to delete
   * @return a HttpServletResponse representing the result of the delete action
   */
  @DELETE
  @Path("/{entityId}")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  Response delete(@PathParam("entityId") Long id);
  
}
