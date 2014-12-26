/**
 * 
 */
package com.mathtabolism.eao.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.IngredientEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class IngredientEAO extends BaseEAO<IngredientEntity> {
  
  public IngredientEAO() {
    super(IngredientEntity.class);
  }
  
  /**
   * Gets all ingredients from the database.
   * 
   * @return a List of all ingredients from the database
   */
  public List<IngredientEntity> findAllIngredients(int limit) {
    TypedQuery<IngredientEntity> q = em.createNamedQuery(IngredientEntity.Q_findAllIngredients, IngredientEntity.class);
    if(limit > 0) {
      q.setMaxResults(limit);
    }
    return q.getResultList();
  }
  
  /**
   * Gets all ingredients that respond to the given brand
   * 
   * @param brand
   *          the brand name
   * @return a list of ingredients that have the given brand
   */
  public List<IngredientEntity> findIngredientsByBrand(String brand) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("brand", brand);
    
    return findResultList(IngredientEntity.Q_findIngredientsByBrand, parameters);
  }
  
  /**
   * Gets all ingredients for the given category
   * 
   * @param category
   *          an {@link IngredientCategory} to lookup Ingredients for
   * @return a List of Ingredients for the given category
   */
  public List<IngredientEntity> findIngredientsByCategory(IngredientCategory category) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("category", category);
    
    return findResultList(IngredientEntity.Q_findIngredientsByCategory, parameters);
  }
  
  /**
   * Gets all ingredients that match like the given name. This is a case insensitive search and matches any characters
   * before or after the name.
   * 
   * @param name the Ingredient name to lookup
   * @return the List of Ingredients with a name like the search name
   */
  public List<IngredientEntity> findIngredientsByName(String name) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", "%" + name.toUpperCase() + "%");
    
    return findResultList(IngredientEntity.Q_findIngredientsByName, parameters);
  }
}
