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
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;
import com.mathtabolism.entity.food.MealEntity;
import com.mathtabolism.entity.food.MealPartEntity;
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
   * Finds the current week of {@link DailyIntakeEntity} for a given {@link AccountEntity}.
   * <p>
   * If the week does not exist, a new week is generated for the account. I guess this could technically run into a
   * problem that a new week is generated for an account right when the account is created as well. The account's list
   * of DailyIntake could start before the creation of the account. I am calling this a "Feature".
   * 
   * @param accountEntity
   *          the Account to find a DailyIntake week for
   * @return a list of the DailyIntake for the current account's week
   */
  public List<DailyIntakeEntity> findCurrentWeek(AccountEntity accountEntity, AccountSettingEntity currentSettings) {
    int recalcDOW = currentSettings.getRecalculationDay().toInt();
    List<DailyIntakeEntity> currentWeek = dailyIntakeEAO.findCurrentWeek(accountEntity, DateUtils.findStartDate(recalcDOW));
    if(currentWeek == null || currentWeek.isEmpty()) {
      currentWeek = generateNewWeek(accountEntity, currentSettings);
    }
    return currentWeek;
  }
  
  /**
   * Generates a new week for an {@link AccountEntity} starting on the Account's recalculation day.
   * 
   * @param accountEntity
   *          the Account to generate a week for
   * @return a List of {@link DailyIntakeEntity}
   */
  private List<DailyIntakeEntity> generateNewWeek(AccountEntity accountEntity, AccountSettingEntity currentSettings) {
    List<DailyIntakeEntity> weekOfIntakes = new ArrayList<>();
    int recalcDOW = currentSettings.getRecalculationDay().toInt();
    DateTime dt = DateUtils.findStartDate(recalcDOW);
    for(int i = 0; i < 7; i++) {
      Date intakeDate = dt.toDate();
      weekOfIntakes.add(generateNewDailyIntake(accountEntity, intakeDate));
      dt = dt.plusDays(1);
    }
    return weekOfIntakes;
  }
  
  /**
   * Generates a new DailyIntake with the default values for a given account and intake date. A default DailyIntake has
   * no calorie change, a 50/50 split between fat and carbohydrates, and a 1:1 ratio for person weight/grams of protein.
   * <p>
   * This also saves the new daily intake to the database
   * 
   * @param accountEntity
   *          the account to set to the new DailyIntake
   * @param intakeDate
   *          the date
   * @return a new DailyIntake with default values
   */
  private DailyIntakeEntity generateNewDailyIntake(AccountEntity accountEntity, Date intakeDate) {
    DailyIntakeEntity di = new DailyIntakeEntity();
    di.setAccountEntity(accountEntity);
    di.setIntakeDate(intakeDate);
    di.setCalorieChange(0);
    di.setFatMultiplier(0.5);
    di.setCarbMultiplier(0.5);
    di.setProteinMultiplier(1.0);
    dailyIntakeEAO.create(di);
    return di;
  }
  
  public DailyIntakeMealEntity getDefaultDailyIntakeMeal(DailyIntakeEntity dailyIntakeEntity, int index) {
    DailyIntakeMealEntity dailyIntakeMealEntity = new DailyIntakeMealEntity();
    MealEntity mealEntity = new MealEntity();
    mealEntity.setName(String.format("Meal %02d", index + 1));
    mealEntity.setMealParts(new ArrayList<MealPartEntity>());
    dailyIntakeMealEntity.setMeal(mealEntity);
    dailyIntakeMealEntity.setDailyIntake(dailyIntakeEntity);
    return dailyIntakeMealEntity;
  }
}
