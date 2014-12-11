/**
 * 
 */
package com.mathtabolism.beans.account;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.beans.BaseBean;
import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.bo.account.DailyIntakeBO;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.MealFactType;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.constants.TotalType;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.entity.account.AccountWeight;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.util.calculation.FormulaCalculation;
import com.mathtabolism.util.calculation.IntakeCalculator;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.unit.UnitSystem;

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
  private AccountBO accountBO;
  @Inject
  private AccountBean accountBean;
  
  private List<DailyIntake> currentDailyIntakeWeek;
  private List<AccountWeight> currentAccountWeightWeek;
  
  public DailyIntakeBean() {
  }
  
  public List<DailyIntake> getCurrentDailyIntakeWeek() {
    if(currentDailyIntakeWeek == null || currentDailyIntakeWeek.isEmpty()) {
      currentDailyIntakeWeek = dailyIntakeBO.findCurrentWeek(accountBean.getAccount(), accountBean.getCurrentSettings());
    }
    return currentDailyIntakeWeek;
  }
  
  private List<AccountWeight> getCurrentAccountWeightWeek() {
    if(currentAccountWeightWeek == null) {
      currentAccountWeightWeek = accountBO.findCurrentAccountWeightWeek(accountBean.getAccount(), accountBean.getCurrentSettings());
    }
    return currentAccountWeightWeek;
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
        return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.CARBOHYDRATE)
            .getDisplayValue();
      case PROTEIN:
        return IntakeCalculator.calculateMealNutrients(meal.getMealParts(), NutrientType.PROTEIN).getDisplayValue();
    }
    return "";
  }
  
  public void setCurrentDailyIntakeWeek(List<DailyIntake> currentDailyIntakeWeek) {
    this.currentDailyIntakeWeek = currentDailyIntakeWeek;
  }
  
  public String calculatedTotal(DailyIntake dailyIntake, NutrientType nutrientType, TotalType totalType) {
    Account account = dailyIntake.getAccount();
    Date intakeDate = dailyIntake.getIntakeDate();
    AccountSetting accountSettings = accountBO.findLatestSettingsForDate(account, intakeDate);
    BaseNutrient calculatedTotal = null;
    List<AccountWeight> week = getCurrentAccountWeightWeek();
    AccountWeight weight = null;
    if(week.stream().anyMatch(w -> DateUtils.isSameDate(w.getWeighInDate(), intakeDate))) {
      weight = week.stream().filter(w -> w.getWeighInDate().equals(intakeDate)).findFirst().get();
    }
    
    Calorie expected = new Calorie();
    if(weight != null && (account.getBirthday() != null || accountSettings.getAge() != null)
        && accountSettings.getHeight() != null && account.getGender() != null) {
      double w = weight.getWeight();
      double height = accountSettings.getHeight();
      int age = accountSettings.getAge() != null ? accountSettings.getAge() : DateUtils.calculateAge(account.getBirthday());
      Gender gender = account.getGender();
      UnitSystem unitSystem = UnitSystem.IMPERIAL;
      expected = FormulaCalculation.calculateBMR(w, height, age, gender, unitSystem);
    }
    
    switch(totalType) {
      case EXPECTED:
        calculatedTotal = expected;
        break;
      case CURRENT:
        calculatedTotal = IntakeCalculator.calculateTotalDailyIntake(dailyIntake, nutrientType);
        break;
      case REMAINING:
        calculatedTotal = expected;
        calculatedTotal.subtract(IntakeCalculator.calculateTotalDailyIntake(dailyIntake, nutrientType));
        break;
    }
    return calculatedTotal == null ? "" : calculatedTotal.getDisplayValue();
  }
  
}
