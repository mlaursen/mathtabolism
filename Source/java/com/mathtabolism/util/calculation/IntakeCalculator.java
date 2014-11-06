package com.mathtabolism.util.calculation;

import java.util.List;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Ingredient;
import com.mathtabolism.entity.food.MealPart;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;

public class IntakeCalculator {
  private IntakeCalculator() {
  }
  
  /**
   * Calculate's a Nutrient's total value for a DailyIntakeMeal by adding all the ingredients in a meal with their
   * serving value together.
   * 
   * @param dailyIntakeMeal
   * @param nutrientType
   * @return
   */
  public static BaseNutrient calculateNutrient(DailyIntakeMeal dailyIntakeMeal, NutrientType nutrientType) {
    return calculateMealNutrients(dailyIntakeMeal.getMeal().getMealParts(), nutrientType);
  }
  
  public static BaseNutrient calculateMealNutrients(List<MealPart> mealParts, NutrientType nutrientType) {
    BaseNutrient nutrient = BaseNutrient.create(nutrientType);
    for(MealPart mealPart : mealParts) {
      Measurement mealPartServing = mealPart.getServing();
      Ingredient i = mealPart.getIngredient();
      Measurement ingredientServing = UnitConverter.getServing(mealPartServing, i);
      double ratio = mealPartServing.getValue() / ingredientServing.getValue();
      BaseNutrient iNutrient = BaseNutrient.getFromIngredient(i, nutrientType);
      nutrient.setAmount(iNutrient.getAmount() * ratio);
    }
    return nutrient;
  }
  
  public static BaseNutrient calculateTotalDailyIntake(DailyIntake dailyIntake, NutrientType nutrientType) {
    BaseNutrient total = BaseNutrient.create(nutrientType);
    List<DailyIntakeMeal> dailyIntakeMeals = dailyIntake.getMeals();
    for(DailyIntakeMeal dailyIntakeMeal : dailyIntakeMeals) {
      total.add(calculateNutrient(dailyIntakeMeal, nutrientType));
    }
    return total;
  }
}
