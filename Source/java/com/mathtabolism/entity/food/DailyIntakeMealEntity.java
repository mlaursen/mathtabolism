/**
 * 
 */
package com.mathtabolism.entity.food;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
public class DailyIntakeMealEntity extends BaseGeneratedEntity {
  
  @OneToOne
  @JoinColumn(name = "meal_id")
  private MealEntity mealEntity;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "daily_intake_id")
  private DailyIntakeEntity dailyIntakeEntity;
  
  public DailyIntakeMealEntity() {
  }
  
  /**
   * 
   * @return
   */
  public MealEntity getMeal() {
    return mealEntity;
  }
  
  /**
   * 
   * @param mealEntity
   */
  public void setMeal(MealEntity mealEntity) {
    this.mealEntity = mealEntity;
  }
  
  /**
   * 
   * @return
   */
  public DailyIntakeEntity getDailyIntake() {
    return dailyIntakeEntity;
  }
  
  /**
   * 
   * @param dailyIntakeEntity
   */
  public void setDailyIntake(DailyIntakeEntity dailyIntakeEntity) {
    this.dailyIntakeEntity = dailyIntakeEntity;
  }
  
}
