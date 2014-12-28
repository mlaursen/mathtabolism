/**
 * 
 */
package com.mathtabolism.model;

import com.mathtabolism.util.unit.Measurement;

/**
 * @author mlaursen
 *
 */
public interface MealPart extends GeneratedIdContract {

  /**
   * Sets the serving size for a Meal Part
   * @param serving the {@link Measurement}
   */
  void setServing(Measurement serving);
  
  /**
   * Gets the serving size for a Meal Part
   * @return the {@link Measurement}
   */
  Measurement getServing();
}
