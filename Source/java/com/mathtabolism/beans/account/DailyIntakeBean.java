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
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;

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
	
	
	private double calculate(DailyIntake dailyIntake) {
		List<DailyIntakeMeal> meals = dailyIntake.getMeals();
		for(DailyIntakeMeal meal : meals) {
			meal.getMeal().getMealParts();
		}
		return 0;
	}
	
	public double calculateTotalCalories(DailyIntake intake) {
		return 0;
	}
}
