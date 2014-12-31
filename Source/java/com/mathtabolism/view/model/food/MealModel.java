/**
 * 
 */
package com.mathtabolism.view.model.food;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.dto.MealDto;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.util.emconverter.ModelConverter;
import com.mathtabolism.view.model.BaseModel;
import com.mathtabolism.view.model.food.MealPartModel;

/**
 * 
 * @author mlaursen
 */
@ModelConverter(entities = {Meal.class})
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", name)
        .append("mealParts", mealParts).toString();
  }
}
