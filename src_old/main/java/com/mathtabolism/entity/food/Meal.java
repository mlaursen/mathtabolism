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

import com.mathtabolism.dto.MealDto;
import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.util.emconverter.EntityConverter;
import com.mathtabolism.view.model.food.MealModel;

/**
 * 
 * @author mlaursen
 */
@Entity
@EntityConverter(converterDto = MealDto.class, toModel = MealModel.class)
public class Meal extends BaseGeneratedEntity implements MealDto {
  
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
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id)
        .append("name", name).append("mealParts", mealParts).toString();
  }
}
