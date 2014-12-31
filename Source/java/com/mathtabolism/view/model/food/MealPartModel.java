/**
 * 
 */
package com.mathtabolism.view.model.food;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.dto.MealPartDto;
import com.mathtabolism.entity.food.MealPart;
import com.mathtabolism.util.emconverter.ModelConverter;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 */
@ModelConverter(entities = {MealPart.class})
public class MealPartModel extends BaseModel implements MealPartDto {
  
  private Measurement serving;
  private IngredientModel ingredient;
  
  public void setIngredient(IngredientModel ingredient) {
    this.ingredient = ingredient;
  }
  
  public IngredientModel getIngredient() {
    return ingredient;
  }

  @Override
  public void setServing(Measurement serving) {
    this.serving = serving;
  }

  @Override
  public Measurement getServing() {
    return serving;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("serving", serving).append("ingredient", ingredient).toString();
  }
}
