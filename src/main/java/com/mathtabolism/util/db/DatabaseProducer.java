/**
 * 
 */
package com.mathtabolism.util.db;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author mlaursen
 *
 */
public class DatabaseProducer {
  @Produces
  @PersistenceContext(unitName = "MathtabolismPU")
  @MathtabolismDB
  protected EntityManager em;
}
