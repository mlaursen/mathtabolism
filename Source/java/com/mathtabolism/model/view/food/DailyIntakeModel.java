/**
 * 
 */
package com.mathtabolism.model.view.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.model.DailyIntake;
import com.mathtabolism.model.view.BaseModel;
import com.mathtabolism.model.view.account.AccountWeightModel;

/**
 * @author mlaursen
 *
 */
public class DailyIntakeModel extends BaseModel implements DailyIntake {

  private Date intakeDate;
  private Integer calorieChange;
  private Double fatMultiplier;
  private Double carbMultiplier;
  private Double proteinMultiplier;
  
  private AccountWeightModel accountWeightModel;
  private List<MealModel> meals;
  
  /**
   * Gets the {@link AccountWeightModel} associated with this daily intake
   * @return the account weight
   */
  public AccountWeightModel getAccountWeightModel() {
    return accountWeightModel;
  }

  /**
   * Sets the {@link AccountWeightModel} assocaited with this daily intake
   * @param accountWeightModel the account weight
   */
  public void setAccountWeightModel(AccountWeightModel accountWeightModel) {
    this.accountWeightModel = accountWeightModel;
  }
  
  /**
   * Gets the associated meals for a daily intake
   * @return the list of meals
   */
  public List<MealModel> getMeals() {
    return meals;
  }
  
  /**
   * Sets the associated list of meals for a daily intake
   * @param meals a List of meals
   */
  public void setMeals(List<MealModel> meals) {
    this.meals = meals;
  }

  @Override
  public void setIntakeDate(Date intakeDate) {
    this.intakeDate = intakeDate;
  }
  
  @Override
  public Date getIntakeDate() {
    return intakeDate;
  }
  
  @Override
  public void setCalorieChange(Integer calorieChange) {
    this.calorieChange = calorieChange;
  }
  
  @Override
  public Integer getCalorieChange() {
    return calorieChange;
  }
  
  @Override
  public void setFatMultiplier(Double fatMultiplier) {
    this.fatMultiplier = fatMultiplier;
  }
  
  @Override
  public Double getFatMultiplier() {
    return fatMultiplier;
  }
  
  @Override
  public void setCarbMultiplier(Double carbMultiplier) {
    this.carbMultiplier = carbMultiplier;
  }
  
  @Override
  public Double getCarbMultiplier() {
    return carbMultiplier;
  }
  
  @Override
  public void setProteinMultiplier(Double proteinMultiplier) {
    this.proteinMultiplier = proteinMultiplier;
  }
  
  @Override
  public Double getProteinMultiplier() {
    return proteinMultiplier;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("intakeDate", intakeDate)
        .append("calorieChange", calorieChange).append("fatMultiplier", fatMultiplier).append("carbMultiplier", carbMultiplier)
        .append("proteinMultiplier", proteinMultiplier).append("accountWeightModel", accountWeightModel).toString();
  }
}
