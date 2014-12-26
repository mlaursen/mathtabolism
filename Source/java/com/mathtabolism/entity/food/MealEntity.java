/**
 * 
 */
package com.mathtabolism.entity.food;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
public class MealEntity extends BaseGeneratedEntity {
  
  private String name;
  
  @OneToMany
  @JoinColumn(name = "meal_id")
  private List<MealPartEntity> mealParts;
  
  public MealEntity() {
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
  public List<MealPartEntity> getMealParts() {
    return mealParts;
  }
  
  /**
   * 
   * @param mealParts
   */
  public void setMealParts(List<MealPartEntity> mealParts) {
    this.mealParts = mealParts;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", name).append("mealParts", mealParts).toString();
  }
}
