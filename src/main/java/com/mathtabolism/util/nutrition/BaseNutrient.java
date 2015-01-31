/**
 * 
 */
package com.mathtabolism.util.nutrition;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.dto.IngredientDto;
import com.mathtabolism.util.calculation.Addable;
import com.mathtabolism.util.calculation.Subtractable;
import com.mathtabolism.util.number.MathtabolismNumberUtils;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseNutrient implements Addable, Subtractable {
  protected double amount;
  
  public BaseNutrient() {
  }
  
  public BaseNutrient(double amount) {
    this.amount = amount;
  }
  
  @Override
  public void add(Addable nutrient) {
    if(nutrient instanceof BaseNutrient && this.getClass().equals(nutrient.getClass())) {
      this.amount += ((BaseNutrient) nutrient).amount;
    }
  }
  
  @Override
  public void subtract(Subtractable nutrient) {
    if(nutrient instanceof BaseNutrient && this.getClass().equals(nutrient.getClass())) {
      this.amount -= ((BaseNutrient) nutrient).amount;
    }
  }
  
  /**
   * Sets the amount of a Nutrient
   * @param amount the amount
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  /**
   * Gets the amount of a Nutrient
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object != null && object.getClass().equals(getClass())) {
      return MathtabolismNumberUtils.aboutEqual(amount, ((BaseNutrient) object).amount);
    }
    return false;
  }
  
  /**
   * Gets a formatted number String of the Nutrient amount
   * @return a String
   */
  public String getDisplayValue() {
    return MathtabolismNumberUtils.format(amount);
  }
  
  /**
   * @return
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("amount", amount).toString();
  }
  
  /**
   * Creates a BaseNutrient based on the NutrientType
   * 
   * @param whichNutrient
   * @return a subclass of BaseNutrient
   */
  public static BaseNutrient create(NutrientType whichNutrient) {
    switch(whichNutrient) {
      case CALORIE:
        return new Calorie();
      case FAT:
        return new Fat();
      case CARBOHYDRATE:
        return new Carbohydrate();
      case PROTEIN:
        return new Protein();
    }
    return null;
  }
  
  /**
   * Helper method for extracting a nutrient type from an Ingredient.
   * @param ingredient the ingredient
   * @param whichNutrient the NutrientType
   * @return a BaseNutrient or null
   */
  public static BaseNutrient getFromIngredient(IngredientDto ingredient, NutrientType whichNutrient) {
    switch(whichNutrient) {
      case CALORIE:
        return ingredient.getCalories();
      case FAT:
        return ingredient.getFat();
      case CARBOHYDRATE:
        return ingredient.getCarbohydrates();
      case PROTEIN:
        return ingredient.getProtein();
    }
    return null;
  }
}
