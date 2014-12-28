/**
 * 
 */
package com.mathtabolism.model.view.food;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.model.MealPart;
import com.mathtabolism.model.view.BaseModel;
import com.mathtabolism.util.unit.Measurement;

/**
 * @author mlaursen
 *
 */
public class MealPartModel extends BaseModel implements MealPart {
  
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
