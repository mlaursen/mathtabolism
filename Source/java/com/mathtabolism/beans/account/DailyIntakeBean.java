/**
 * 
 */
package com.mathtabolism.beans.account;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.beans.BaseBean;
import com.mathtabolism.bo.account.DailyIntakeBO;
import com.mathtabolism.constants.MealFactType;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.constants.TotalType;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.util.calculation.IntakeCalculator;
import com.mathtabolism.util.nutrition.BaseNutrient;

/**
 * 
 * @author mlaursen
 */
@Named
@RequestScoped
public class DailyIntakeBean extends BaseBean {
	private static final long serialVersionUID = 592730676183607742L;
	@Inject
	private DailyIntakeBO dailyIntakeBO;
	@Inject
	private AccountBean accountBean;

	private List<DailyIntake> currentDailyIntakeWeek;
	public DailyIntakeBean() {
	}
	
	public List<DailyIntake> getCurrentDailyIntakeWeek() {
		if(currentDailyIntakeWeek == null || currentDailyIntakeWeek.isEmpty()) {
			currentDailyIntakeWeek = dailyIntakeBO.findCurrentWeek(accountBean.getAccount(), accountBean.getCurrentSettings());
		}
		return currentDailyIntakeWeek;
	}
	
	public List<DailyIntakeMeal> getDailyIntakeMeals(DailyIntake dailyIntake) {
		List<DailyIntakeMeal> meals = dailyIntake.getMeals();
		for(int i = meals.size(); i < 5; i++) {
			meals.add(dailyIntakeBO.getDefaultDailyIntakeMeal(dailyIntake, i));
		}
		return meals;
	}
	
	public String getMealFact(DailyIntakeMeal dailyIntakeMeal, MealFactType mealFactType) {
		Meal meal = dailyIntakeMeal.getMeal();
		switch(mealFactType) {
			case NAME:
				return meal.getName();
			case CALORIE:
				return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.CALORIE).getDisplayValue();
			case FAT:
				return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.FAT).getDisplayValue();
			case CARBOHYDRATE:
				return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.CARBOHYDRATE).getDisplayValue();
			case PROTEIN:
				return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.PROTEIN).getDisplayValue();
		}
		return "";
	}
	
	public void setCurrentDailyIntakeWeek(List<DailyIntake> currentDailyIntakeWeek) {
		this.currentDailyIntakeWeek = currentDailyIntakeWeek;
	}
	
	public String calculatedTotal(DailyIntake dailyIntake, NutrientType nutrientType, TotalType totalType) {
		BaseNutrient calculatedTotal = null;
		switch(totalType) {
			case EXPECTED:
				break;
			case CURRENT:
				calculatedTotal = IntakeCalculator.calculateTotalDailyIntake(dailyIntake, nutrientType);
			case REMAINING:
				break;
		}
		return calculatedTotal == null ? "" : calculatedTotal.getDisplayValue();
	}
	
}
