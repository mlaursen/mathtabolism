/**
 * 
 */
package com.mathtabolism.model;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;

/**
 * @author mlaursen
 *
 */
public interface Ingredient extends GeneratedIdContract {
  
  /**
   * Sets the Ingredient's name
   * @param name the name
   */
  void setName(String name);
  
  /**
   * Gets the Ingredient's name
   * @return the name
   */
  String getName();
  
  /**
   * Sets the {@link IngredientCategory}
   * @param category the category
   */
  void setCategory(IngredientCategory category);
  
  /**
   * Gets the {@link IngredientCategory}
   * @return the category
   */
  IngredientCategory getCategory();
  
  /**
   * Sets the default serving measurement
   * @param serving the {@link Measurement}
   */
  void setServing(Measurement serving);
  
  /**
   * Gets the default serving measurement
   * @return the {@link Measurement}
   */
  Measurement getServing();
  
  /**
   * Sets the alternate serving measurement
   * @param alternateServing the {@link Measurement}
   */
  void setAlternateServing(Measurement alternateServing);
  
  /**
   * Gets the alternate serving measurement
   * @return the {@link Measurement}
   */
  Measurement getAlternateServing();
  
  /**
   * Sets the amount of Calories in an ingredient for either serving
   * @param calories a {@link Calorie} amount
   */
  void setCalories(Calorie calories);
  
  /**
   * Gets the amount of Calories in an ingredient for either serving
   * @return a {@link Calorie} amount
   */
  Calorie getCalories();
  
  /**
   * Sets the amount of Fat in an ingredient for either serving
   * @param calories a {@link Fat} amount
   */
  void setFat(Fat fat);
  
  /**
   * Gets the amount of Fat in an ingredient for either serving
   * @return a {@link Fat} amount
   */
  Fat getFat();
  
  /**
   * Sets the amount of Carbohydrates in an ingredient for either serving
   * @param calories a {@link Carbohydrate} amount
   */
  void setCarbohydrates(Carbohydrate carbohydrates);
  
  /**
   * Gets the amount of Carbohydrates in an ingredient for either serving
   * @return a {@link Carbohydrate} amount
   */
  Carbohydrate getCarbohydrates();
  
  /**
   * Sets the amount of Protein in an ingredient for either serving
   * @param calories a {@link Protein} amount
   */
  void setProtein(Protein protein);
  
  /**
   * Gets the amount of Protein in an ingredient for either serving
   * @return a {@link Protein} amount
   */
  Protein getProtein();
}
