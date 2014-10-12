/**
 * 
 */
package com.mathtabolism.bo.ingredient;

import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.eao.ingredient.IngredientEAO;
import com.mathtabolism.entity.ingredient.Ingredient;

/**
 * 
 * @author mlaursen
 */
public class IngredientBO {
	private static Logger logger = Logger.getLogger(IngredientBO.class);
	private static final int POPULAR_LIMIT = 100;
	
	@Inject
	private IngredientEAO ingredientEAO;
	
	public IngredientBO() {
	}
	
	/**
	 * Gets all ingredients from the database.
	 * @return a List of all ingredients from the database
	 */
	public List<Ingredient> findAllIngredients() {
		return ingredientEAO.findAllIngredients(0);
	}
	
	/**
	 * Gets the top {@value #POPULAR_LIMIT} Ingredients
	 * @return a List of Ingredients
	 */
	public List<Ingredient> findPopularIngredients() {
		return ingredientEAO.findAllIngredients(POPULAR_LIMIT);
	}
	
	/**
	 * Gets all ingredients that respond to the given brand
	 * @param brand the brand name
	 * @return a list of ingredients that have the given brand
	 */
	public List<Ingredient> findIngredientsByBrand(String brand) {
		return ingredientEAO.findIngredientsByBrand(brand);
	}
	
	/**
	 * Gets all ingredients for the given category
	 * @param category an {@link IngredientCategory} to lookup Ingredients for
	 * @return a List of Ingredients for the given category
	 */
	public List<Ingredient> findIngredientsByCategory(IngredientCategory category) {
		return ingredientEAO.findIngredientsByCategory(category);
	}
	
	/**
	 * Gets all ingredients that match like the given name. This is a case insensitive search
	 * and matches any characters before or after the name.
	 * @param name the Ingredient name to lookup
	 * @return the List of Ingredients with a name like the search name
	 */
	public List<Ingredient> findIngredientsByName(String name) {
		return ingredientEAO.findIngredientsByName(name);
	}
	
}
