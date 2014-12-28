/**
 * 
 */
package com.mathtabolism.model;

import java.util.Date;

/**
 * @author mlaursen
 *
 */
public interface DailyIntake extends GeneratedIdContract {
  
  /**
   * Sets the new intake date
   * @param intakeDate the intake date
   */
  void setIntakeDate(Date intakeDate);
  
  /**
   * Gets the intake date
   * @return the intake date
   */
  Date getIntakeDate();
  
  /**
   * Sets the calorie change amount
   * @param calorieChange the calorie change amount
   */
  void setCalorieChange(Integer calorieChange);
  
  /**
   * Gets the calorie change amount
   * @return the amount
   */
  Integer getCalorieChange();
  
  /**
   * Sets the multiplier for figuring out how many calories should be from Fat.
   * @param fatMultiplier the amount
   */
  void setFatMultiplier(Double fatMultiplier);
  
  /**
   * Gets the multiplier for figuring out how many calories should be from Fat.
   * @return the amount
   */
  Double getFatMultiplier();
  
  /**
   * Sets the multiplier for figuring out how many calories should come
   * from Carbohydrates
   * @param carbMultiplier the amount
   */
  void setCarbMultiplier(Double carbMultiplier);
  
  /**
   * Gets the multiplier for figuring out how many calories should come
   * from Carbohydrates
   * @return the amount
   */
  Double getCarbMultiplier();
  
  /**
   * Sets the multiplier for figuring out how many calories should come from
   * Protein.
   * @param proteinMultiplier the amount
   */
  void setProteinMultiplier(Double proteinMultiplier);
  
  /**
   * Gets the multiplier for figuring out how many calories should come from
   * Protein.
   * @return the amount
   */
  Double getProteinMultiplier();
}
