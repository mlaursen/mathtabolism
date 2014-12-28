/**
 * 
 */
package com.mathtabolism.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

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
  private static final String GENERATOR_NAME = "dynamic_pk_generator";
  private static final String GENERATOR_CLASS = "com.mathtabolism.util.hibernate.PrimaryKeyGenerator";
  
  @Id
  @GeneratedValue(generator = GENERATOR_NAME)
  @GenericGenerator(name = GENERATOR_NAME, strategy = GENERATOR_CLASS)
  protected String id;
  
  /**
   * Gets the entity's Generated Id
   * 
   * @return the id for the entity
   */
  public String getId() {
    return id;
  }
  
  /**
   * Sets the id for the entity
   * 
   * @param id the entity's Id
   */
  public void setId(String id) {
    this.id = id;
  }
}
