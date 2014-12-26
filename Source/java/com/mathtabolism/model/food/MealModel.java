/**
 * 
 */
package com.mathtabolism.model.food;

import java.util.List;

import com.mathtabolism.emcontract.Meal;
import com.mathtabolism.model.BaseModel;

/**
 * @author mlaursen
 *
 */
public class MealModel extends BaseModel implements Meal {
  
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
