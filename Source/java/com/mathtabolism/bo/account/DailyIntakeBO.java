/**
 * 
 */
package com.mathtabolism.bo.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.DateTime;

import com.mathtabolism.eao.account.DailyIntakeEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.entity.food.MealPart;
import com.mathtabolism.util.date.DateUtils;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeBO {
	
	@Inject
	private DailyIntakeEAO dailyIntakeEAO;
	
	public DailyIntakeBO() {
	}
	
	/**
	 * Finds the current week of {@link DailyIntake} for a given {@link Account}.
	 * <p>If the week does not exist, a new week is generated for the account.
	 * I guess this could technically run into a problem that a new week is generated for an account
	 * right when the account is created as well. The account's list of DailyIntake could
	 * start before the creation of the account. I am calling this a "Feature".
	 * 
	 * @param account the Account to find a DailyIntake week for
	 * @return a list of the DailyIntake for the current account's week
	 */
	public List<DailyIntake> findCurrentWeek(Account account, AccountSetting currentSettings) {
		int recalcDOW = currentSettings.getRecalculationDay().toInt();
		List<DailyIntake> currentWeek = dailyIntakeEAO.findCurrentWeek(account, DateUtils.findStartDate(recalcDOW));
		if(currentWeek == null || currentWeek.isEmpty() || currentWeek.size() < 7) {
			currentWeek = generateNewWeek(account, currentSettings);
		}
		return currentWeek;
	}
	
	/**
	 * Generates a new week for an {@link Account} starting on the Account's recalculation day.
	 * @param account the Account to generate a week for
	 * @return a List of {@link DailyIntake}
	 */
	private List<DailyIntake> generateNewWeek(Account account, AccountSetting currentSettings) {
		List<DailyIntake> weekOfIntakes = new ArrayList<>();
		int recalcDOW = currentSettings.getRecalculationDay().toInt();
		DateTime dt = DateUtils.findStartDate(recalcDOW);
		for(int i = 0; i < 7; i++) {
			Date intakeDate = dt.toDate();
			weekOfIntakes.add(generateNewDailyIntake(account, intakeDate));
			dt = dt.plusDays(1);
		}
		return weekOfIntakes;
	}
	
	/**
	 * Generates a new DailyIntake with the default values for a given account and intake date.
	 * A default DailyIntake has no calorie change, a 50/50 split between fat and carbohydrates,
	 * and a 1:1 ratio for person weight/grams of protein.
	 * <p>This also saves the new daily intake to the database
	 * @param account the account to set to the new DailyIntake
	 * @param intakeDate the date
	 * @return a new DailyIntake with default values
	 */
	private DailyIntake generateNewDailyIntake(Account account, Date intakeDate) {
		DailyIntake di = new DailyIntake();
		di.setAccount(account);
		di.setIntakeDate(intakeDate);
		di.setCalorieChange(0);
		di.setFatMultiplier(0.5);
		di.setCarbMultiplier(0.5);
		di.setProteinMultiplier(1.0);
		dailyIntakeEAO.create(di);
		return di;
	}
	
	public DailyIntakeMeal getDefaultDailyIntakeMeal(DailyIntake dailyIntake, int index) {
		DailyIntakeMeal dailyIntakeMeal = new DailyIntakeMeal();
		Meal meal = new Meal();
		meal.setName(String.format("Meal %02d", index + 1));
		meal.setMealParts(new ArrayList<MealPart>());
		dailyIntakeMeal.setMeal(meal);
		dailyIntakeMeal.setDailyIntake(dailyIntake);
		return dailyIntakeMeal;
	}
}
