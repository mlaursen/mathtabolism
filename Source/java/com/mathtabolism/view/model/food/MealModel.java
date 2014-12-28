/**
 * 
 */
package com.mathtabolism.view.model.food;

import java.util.List;

import com.mathtabolism.dto.MealDto;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.util.emconverter.EMConverter;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 */
@EMConverter(converter = MealDto.class, convertTo = Meal.class)
public class MealModel extends BaseModel implements MealDto {
  
  private String name;
  private List<MealPartModel> mealParts;
  
  public List<MealPartModel> getMealParts() {
    return mealParts;
  }

  public void setMealParts(List<MealPartModel> mealParts) {
    this.mealParts = mealParts;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

}
