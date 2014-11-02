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
import com.mathtabolism.entity.food.Ingredient;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class IngredientEAO extends BaseEAO<Ingredient> {
	
	public IngredientEAO() {
		super(Ingredient.class);
	}
	
	/**
	 * Gets all ingredients from the database.
	 * @return a List of all ingredients from the database
	 */
	public List<Ingredient> findAllIngredients(int limit) {
		TypedQuery<Ingredient> q = em.createNamedQuery(Ingredient.Q_findAllIngredients, Ingredient.class);
		if(limit > 0) {
			q.setMaxResults(limit);
		}
		return q.getResultList();
	}
	
	/**
	 * Gets all ingredients that respond to the given brand
	 * @param brand the brand name
	 * @return a list of ingredients that have the given brand
	 */
	public List<Ingredient> findIngredientsByBrand(String brand) {
		TypedQuery<Ingredient> q = em.createNamedQuery(Ingredient.Q_findIngredientsByBrand, Ingredient.class);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("brand", brand);
		bindParameters(q, parameters);
		return q.getResultList();
	}
	
	/**
	 * Gets all ingredients for the given category
	 * @param category an {@link IngredientCategory} to lookup Ingredients for
	 * @return a List of Ingredients for the given category
	 */
	public List<Ingredient> findIngredientsByCategory(IngredientCategory category) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("category", category);
		TypedQuery<Ingredient> q = em.createNamedQuery(Ingredient.Q_findIngredientsByCategory, Ingredient.class);
		bindParameters(q, parameters);
		return q.getResultList();
	}
	
	/**
	 * Gets all ingredients that match like the given name. This is a case insensitive search
	 * and matches any characters before or after the name.
	 * @param name the Ingredient name to lookup
	 * @return the List of Ingredients with a name like the search name
	 */
	public List<Ingredient> findIngredientsByName(String name) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", "%" + name.toUpperCase() + "%");
		TypedQuery<Ingredient> q = em.createNamedQuery(Ingredient.Q_findIngredientsByName, Ingredient.class);
		bindParameters(q, parameters);
		return q.getResultList();
	}
}
