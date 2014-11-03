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
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.constants.TotalType;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.util.calculation.IntakeCalculator;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;

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
	
	public void setCurrentDailyIntakeWeek(List<DailyIntake> currentDailyIntakeWeek) {
		this.currentDailyIntakeWeek = currentDailyIntakeWeek;
	}
	
	public BaseNutrient calculate(DailyIntake dailyIntake, NutrientType nutrientType, TotalType totalType) {
		return new Calorie(100);
	}
	
	public BaseNutrient calculateCurrentCalories(DailyIntake dailyIntake) {
		return IntakeCalculator.calculateTotalDailyIntake(dailyIntake, NutrientType.CALORIE);
	}
	
	public BaseNutrient calculateCurrentFat(DailyIntake dailyIntake) {
		return IntakeCalculator.calculateTotalDailyIntake(dailyIntake, NutrientType.FAT);
	}
	
	public BaseNutrient calculateCurrentCarbohydrates(DailyIntake dailyIntake) {
		return IntakeCalculator.calculateTotalDailyIntake(dailyIntake, NutrientType.CARBOHYDRATE);
	}
	
	public BaseNutrient calculateCurrentProtein(DailyIntake dailyIntake) {
		return IntakeCalculator.calculateTotalDailyIntake(dailyIntake, NutrientType.PROTEIN);
	}
}
