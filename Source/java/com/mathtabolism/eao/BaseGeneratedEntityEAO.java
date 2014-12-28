/**
 * 
 */
package com.mathtabolism.eao;

import com.mathtabolism.model.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseGeneratedEntityEAO<T extends BaseGeneratedEntity> extends BaseEAO<T> {

  /**
   * @param entityClass
   */
  protected BaseGeneratedEntityEAO(Class<T> entityClass) {
    super(entityClass);
  }
  
  /**
   * Attempts to find an entity by the generated id column
   * @param entity the entity
   * @return the found entity or null
   */
  public T findById(T entity) {
    return entity.getId() == null ? null : super.findById(entity.getId());
  }
  
}
