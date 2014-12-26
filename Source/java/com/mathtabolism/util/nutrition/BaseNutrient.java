/**
 * 
 */
package com.mathtabolism.util.nutrition;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.emcontract.Ingredient;
import com.mathtabolism.util.number.NumberUtils;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseNutrient {
  
  protected double amount;
  
  public BaseNutrient() {
  }
  
  public BaseNutrient(double amount) {
    this.amount = amount;
  }
  
  /**
   * 
   * @param nutrient
   */
  public void add(BaseNutrient nutrient) {
    if(this.getClass().equals(nutrient.getClass())) {
      this.amount += ((BaseNutrient) nutrient).amount;
    }
  }
  
  /**
   * 
   * @param nutrient
   */
  public void subtract(BaseNutrient nutrient) {
    if(this.getClass().equals(nutrient.getClass())) {
      this.amount -= ((BaseNutrient) nutrient).amount;
    }
  }
  
  /**
   * 
   * @param amount
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  /**
   * 
   * @return
   */
  public double getAmount() {
    return amount;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object != null && object.getClass().equals(getClass())) {
      return NumberUtils.aboutEqual(amount, ((BaseNutrient) object).amount);
    }
    return false;
  }
  
  public String getDisplayValue() {
    return NumberUtils.formatAsString(amount);
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
  
  public static BaseNutrient getFromIngredient(Ingredient ingredient, NutrientType whichNutrient) {
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
