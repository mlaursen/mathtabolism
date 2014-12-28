/**
 * 
 */
package com.mathtabolism.dto;

import java.util.List;

/**
 * @author mlaursen
 *
 */
public interface MealDto extends GeneratedIdDto {

  /**
   * Gets the Meal's name
   * @return the meal's name
   */
  String getName();
  
  /**
   * Sets the meal's name
   * @param name the name
   */
  void setName(String name);
}
