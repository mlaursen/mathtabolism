/**
 * 
 */
package com.mathtabolism.entity.food;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
public class Meal extends BaseGeneratedEntity {
  
  private String name;
  
  @OneToMany
  @JoinColumn(name = "meal_id")
  private List<MealPart> mealParts;
  
  public Meal() {
  }
  
  /**
   * 
   * @return
   */
  public String getName() {
    return name;
  }
  
  /**
   * 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * 
   * @return
   */
  public List<MealPart> getMealParts() {
    return mealParts;
  }
  
  /**
   * 
   * @param mealParts
   */
  public void setMealParts(List<MealPart> mealParts) {
    this.mealParts = mealParts;
  }
}
