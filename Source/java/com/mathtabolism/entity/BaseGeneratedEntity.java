/**
 * 
 */
package com.mathtabolism.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author mlaursen
 */
@MappedSuperclass
public class BaseGeneratedEntity extends BaseEntity {
  @Id
  @GeneratedValue(generator = "dymanic_pk_generator")
  @GenericGenerator(name = "dymanic_pk_generator", strategy = "com.mathtabolism.util.hibernate.PrimaryKeyGenerator")
  protected String id;
  
  /**
   * Gets the Entity's Generated Id
   * 
   * @return the id for the entity
   */
  public String getId() {
    return id;
  }
  
  /**
   * Sets the id for the entity
   * 
   * @param id
   *          the Entity's Id
   */
  public void setId(String id) {
    this.id = id;
  }
}
