/**
 * 
 */
package com.mathtabolism.util.nutrition;


/**
 * 
 * @author mlaursen
 */
public abstract class MacroNutrient extends BaseNutrient {
  
  private int calorieMultiplier;
  
  public MacroNutrient() {
  }
  
  protected MacroNutrient(double amount, int calorieMultiplier) {
    super(amount);
    this.calorieMultiplier = calorieMultiplier;
  }
  
  public Calorie toCalories() {
    return new Calorie(amount * calorieMultiplier);
  }
  
  public void setFromCalories(Calorie calories) {
    setFromCalories(calories, 1);
  }
  
  public void setFromCalories(Calorie calories, double multiplier) {
    amount = calories.getAmount() / calorieMultiplier * multiplier;
  }
  
  public int getCalorieMultiplier() {
    return calorieMultiplier;
  }
}
