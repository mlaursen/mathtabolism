/**
 * 
 */
package com.mathtabolism.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <p>This is a superclass for auto-magically generating an Id for each entity. I am going the route
 * of only having a single primary key to make joining easier. Any other unique constraints will be defined
 * separately.
 * 
 * @author mlaursen
 * @see com.mathtabolism.util.hibernate.PrimaryKeyGenerator
 */
@MappedSuperclass
public class BaseGeneratedEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;
  
  /**
   * Gets the entity's Generated Id
   * 
   * @return the id for the entity
   */
  public Long getId() {
    return id;
  }
  
  /**
   * Sets the id for the entity
   * 
   * @param id the entity's Id
   */
  public void setId(Long id) {
    this.id = id;
  }
}
