package com.mathtabolism.util.calculation;

import java.util.List;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.entity.account.DailyIntakeEntity;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;
import com.mathtabolism.entity.food.IngredientEntity;
import com.mathtabolism.entity.food.MealPartEntity;
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
   * @param dailyIntakeMealEntity
   * @param nutrientType
   * @return
   */
  public static BaseNutrient calculateNutrient(DailyIntakeMealEntity dailyIntakeMealEntity, NutrientType nutrientType) {
    return calculateMealNutrients(dailyIntakeMealEntity.getMeal().getMealParts(), nutrientType);
  }
  
  public static BaseNutrient calculateMealNutrients(List<MealPartEntity> mealPartEntities, NutrientType nutrientType) {
    BaseNutrient nutrient = BaseNutrient.create(nutrientType);
    for(MealPartEntity mealPartEntity : mealPartEntities) {
      Measurement mealPartServing = mealPartEntity.getServing();
      IngredientEntity i = mealPartEntity.getIngredient();
      Measurement ingredientServing = UnitConverter.getServing(mealPartServing, i);
      double ratio = mealPartServing.getValue() / ingredientServing.getValue();
      BaseNutrient iNutrient = BaseNutrient.getFromIngredient(i, nutrientType);
      nutrient.setAmount(iNutrient.getAmount() * ratio);
    }
    return nutrient;
  }
  
  public static BaseNutrient calculateTotalDailyIntake(DailyIntakeEntity dailyIntakeEntity, NutrientType nutrientType) {
    BaseNutrient total = BaseNutrient.create(nutrientType);
    List<DailyIntakeMealEntity> dailyIntakeMealEntities = dailyIntakeEntity.getMeals();
    for(DailyIntakeMealEntity dailyIntakeMealEntity : dailyIntakeMealEntities) {
      total.add(calculateNutrient(dailyIntakeMealEntity, nutrientType));
    }
    return total;
  }
}
