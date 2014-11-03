package com.mathtabolism.util.calculation;

import java.util.List;

import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Ingredient;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.entity.food.MealPart;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;

public class IntakeCalculator {
	private IntakeCalculator() {
	}
	
	/**
	 * Calculate's a Nutrient's total value for a DailyIntakeMeal by adding all the ingredients in a meal with
	 * their serving value together.
	 * 
	 * @param dailyIntakeMeal
	 * @param whichNutrient
	 * @return
	 */
	public static BaseNutrient calculateNutrient(DailyIntakeMeal dailyIntakeMeal, NutrientType whichNutrient) {
		BaseNutrient nutrient = BaseNutrient.create(whichNutrient);
		Meal meal = dailyIntakeMeal.getMeal();
		List<MealPart> mealParts = meal.getMealParts();
		for(MealPart mealPart : mealParts) {
			Measurement mealPartServing = mealPart.getServing();
			Ingredient i = mealPart.getIngredient();
			Measurement ingredientServing = UnitConverter.getServing(mealPartServing, i);
			double ratio = mealPartServing.getValue() / ingredientServing.getValue();
			BaseNutrient iNutrient = BaseNutrient.getFromIngredient(i, whichNutrient);
			nutrient.setAmount(iNutrient.getAmount() * ratio);
		}
		return nutrient;
	}
	
	public static BaseNutrient calculateTotalDailyIntake(DailyIntake dailyIntake, NutrientType whichNutrient) {
		BaseNutrient total = BaseNutrient.create(whichNutrient);
		List<DailyIntakeMeal> dailyIntakeMeals = dailyIntake.getMeals();
		for(DailyIntakeMeal dailyIntakeMeal : dailyIntakeMeals) {
			total.add(calculateNutrient(dailyIntakeMeal, whichNutrient));
		}
		return total;
	}
}
