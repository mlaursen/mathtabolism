/**
 * 
 */
package com.mathtabolism.dto;

import com.mathtabolism.util.unit.Measurement;

/**
 * @author mlaursen
 *
 */
public interface MealPartDto extends GeneratedIdDto {

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
