package com.mathtabolism.dto;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 *
 */
public interface AccountSettingDto extends GeneratedIdDto {
  
  /**
   * Sets the age for the current account settings at a specific date
   * @param age the age
   */
  void setAge(Integer age);
  
  /**
   * Get the age for the current account settings at a specific date
   * @return the age
   */
  Integer getAge();
  
  /**
   * Set if the account is using an age versus calculating the age on birthday
   * @param useAge an {@link Indicator}
   */
  void setUseAge(Indicator useAge);
  
  /**
   * Get if the account is using an age versus calculating the age on birthday
   * @return the {@link Indicator}
   */
  Indicator getUseAge();
  
  /**
   * Sets the person's height. This number will be a representation of feet + inches or meters + cm
   * @param height the height
   */
  void setHeight(Integer height);
  
  /**
   * Gets the person's height. This number will be a representation of feet + inches or meters + cm
   * @return the height
   */
  Integer getHeight();
  
  /**
   * Sets the person's activity multiplier
   * @param activityMultiplier the multipier
   */
  void setActivityMultiplier(ActivityMultiplier activityMultiplier);
  
  /**
   * Gets the person's activity multiplier
   * @return
   */
  ActivityMultiplier getActivityMultiplier();
  
  /**
   * Sets the day to start recalculating the {@link TDEEFormula}
   * @param recalculationDay the {@link Weekday}
   */
  void setRecalculationDay(Weekday recalculationDay);
  
  /**
   * Gets the day to start recalculation the {@link TDEEFormula}
   * @return the {@link Weekday}
   */
  Weekday getRecalculationDay();
  
  /**
   * Sets the {@link TDEEFormula}
   * @param tdeeFormula the formula to use
   */
  void setTdeeFormula(TDEEFormula tdeeFormula);
  
  /**
   * Gets the {@link TDEEFormula} to use
   * @return the formula
   */
  TDEEFormula getTdeeFormula();
  
  /**
   * Sets the {@link UnitSystem}
   * @param unitSystem the unit system
   */
  void setUnitSystem(UnitSystem unitSystem);
  
  /**
   * Gets the {@link UnitSystem}
   * @return the unit system
   */
  UnitSystem getUnitSystem();
}
