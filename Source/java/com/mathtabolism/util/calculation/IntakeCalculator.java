package com.mathtabolism.util.calculation;

import java.util.List;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.model.view.food.DailyIntakeModel;
import com.mathtabolism.model.view.food.IngredientModel;
import com.mathtabolism.model.view.food.MealModel;
import com.mathtabolism.model.view.food.MealPartModel;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;

public class IntakeCalculator {
  private IntakeCalculator() {
  }
  
  public static BaseNutrient calculateNutrient(MealModel dailyIntakeMeal, NutrientType nutrientType) {
    return calculateMealNutrients(dailyIntakeMeal.getMealParts(), nutrientType);
  }
  
  public static BaseNutrient calculateMealNutrients(List<MealPartModel> mealPartModels, NutrientType nutrientType) {
    BaseNutrient nutrient = BaseNutrient.create(nutrientType);
    for(MealPartModel mealPartModel : mealPartModels) {
      Measurement mealPartServing = mealPartModel.getServing();
      IngredientModel i = mealPartModel.getIngredient();
      Measurement ingredientServing = UnitConverter.getServing(mealPartServing, i);
      double ratio = mealPartServing.getValue() / ingredientServing.getValue();
      BaseNutrient iNutrient = BaseNutrient.getFromIngredient(i, nutrientType);
      nutrient.setAmount(iNutrient.getAmount() * ratio);
    }
    return nutrient;
  }
  
  public static BaseNutrient calculateTotalDailyIntake(DailyIntakeModel dailyIntakeModel, NutrientType nutrientType) {
    BaseNutrient total = BaseNutrient.create(nutrientType);
    List<MealModel> meals = dailyIntakeModel.getMeals();
    for(MealModel meal : meals) {
      total.add(calculateNutrient(meal, nutrientType));
    }
    return total;
  }
}
