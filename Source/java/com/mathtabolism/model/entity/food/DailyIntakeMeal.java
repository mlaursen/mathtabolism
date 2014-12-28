/**
 * 
 */
package com.mathtabolism.model.entity.food;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.model.entity.BaseGeneratedEntity;
import com.mathtabolism.model.entity.account.DailyIntake;

/**
 * 
 * @author mlaursen
 */
@Entity
public class DailyIntakeMeal extends BaseGeneratedEntity {
  
  @OneToOne
  @JoinColumn(name = "meal_id")
  private Meal meal;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "daily_intake_id")
  private DailyIntake dailyIntake;
  
  public DailyIntakeMeal() {
  }
  
  /**
   * 
   * @return
   */
  public Meal getMeal() {
    return meal;
  }
  
  /**
   * 
   * @param meal
   */
  public void setMeal(Meal meal) {
    this.meal = meal;
  }
  
  /**
   * 
   * @return
   */
  public DailyIntake getDailyIntake() {
    return dailyIntake;
  }
  
  /**
   * 
   * @param dailyIntake
   */
  public void setDailyIntake(DailyIntake dailyIntake) {
    this.dailyIntake = dailyIntake;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dailyIntake", dailyIntake)
        .append("meal", meal).toString();
  }
}
