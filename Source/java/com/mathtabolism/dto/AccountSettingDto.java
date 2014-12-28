package com.mathtabolism.dto;

import java.util.Date;

import com.mathtabolism.constants.ActivityMultiplier;
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
   * Sets the date that the account settings were changed
   * @param dateChanged the date
   */
  void setDateChanged(Date dateChanged);
  
  /**
   * The date the account settings were changed
   * @return the date
   */
  Date getDateChanged();
  
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
   * Sets the person's height. This number will be a representation of feet + inches or meters + cm
   * @param height the height
   */
  void setHeight(Double height);
  
  /**
   * Gets the person's height. This number will be a representation of feet + inches or meters + cm
   * @return the height
   */
  Double getHeight();
  
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
