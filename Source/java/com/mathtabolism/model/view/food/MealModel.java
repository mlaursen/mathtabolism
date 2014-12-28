/**
 * 
 */
package com.mathtabolism.model.view.food;

import java.util.List;

import com.mathtabolism.model.Meal;
import com.mathtabolism.model.view.BaseModel;

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
