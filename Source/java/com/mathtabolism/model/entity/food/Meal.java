/**
 * 
 */
package com.mathtabolism.model.entity.food;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.model.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
public class Meal extends BaseGeneratedEntity implements com.mathtabolism.model.Meal {
  
  private String name;
  
  @OneToMany
  @JoinColumn(name = "meal_id")
  private List<MealPart> mealParts;
  
  public Meal() {
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
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
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", name).append("mealParts", mealParts).toString();
  }
}
