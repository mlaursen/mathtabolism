/**
 * 
 */
package com.mathtabolism.controller.account;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.bo.account.DailyIntakeBO;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.MealFactType;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.constants.TotalType;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;
import com.mathtabolism.entity.food.MealEntity;
import com.mathtabolism.util.calculation.FormulaCalculation;
import com.mathtabolism.util.calculation.IntakeCalculator;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Named
@RequestScoped
public class DailyIntakeController extends BaseController {
  private static final long serialVersionUID = 592730676183607742L;
  @Inject
  private DailyIntakeBO dailyIntakeBO;
  @Inject
  private AccountBO accountBO;
  @Inject
  private AccountController accountBean;
  
  private List<DailyIntakeEntity> currentDailyIntakeWeek;
  private List<AccountWeightEntity> currentAccountWeightWeek;
  
  public DailyIntakeController() {
  }
  
  public List<DailyIntakeEntity> getCurrentDailyIntakeWeek() {
    if(currentDailyIntakeWeek == null || currentDailyIntakeWeek.isEmpty()) {
      currentDailyIntakeWeek = dailyIntakeBO.findCurrentWeek(accountBean.getAccount(), accountBean.getCurrentSettings());
    }
    return currentDailyIntakeWeek;
  }
  
  private List<AccountWeightEntity> getCurrentAccountWeightWeek() {
    if(currentAccountWeightWeek == null) {
      currentAccountWeightWeek = accountBO.findCurrentAccountWeightWeek(accountBean.getAccount(), accountBean.getCurrentSettings());
    }
    return currentAccountWeightWeek;
  }
  
  public List<DailyIntakeMealEntity> getDailyIntakeMeals(DailyIntakeEntity dailyIntakeEntity) {
    List<DailyIntakeMealEntity> meals = dailyIntakeEntity.getMeals();
    for(int i = meals.size(); i < 5; i++) {
      meals.add(dailyIntakeBO.getDefaultDailyIntakeMeal(dailyIntakeEntity, i));
    }
    return meals;
  }
  
  public String getMealFact(DailyIntakeMealEntity dailyIntakeMealEntity, MealFactType mealFactType) {
    MealEntity mealEntity = dailyIntakeMealEntity.getMeal();
    switch(mealFactType) {
      case NAME:
        return mealEntity.getName();
      case CALORIE:
        return IntakeCalculator.calculateMealNutrients(mealEntity.getMealParts(), NutrientType.CALORIE).getDisplayValue();
      case FAT:
        return IntakeCalculator.calculateMealNutrients(mealEntity.getMealParts(), NutrientType.FAT).getDisplayValue();
      case CARBOHYDRATE:
        return IntakeCalculator.calculateMealNutrients(mealEntity.getMealParts(), NutrientType.CARBOHYDRATE)
            .getDisplayValue();
      case PROTEIN:
        return IntakeCalculator.calculateMealNutrients(mealEntity.getMealParts(), NutrientType.PROTEIN).getDisplayValue();
    }
    return "";
  }
  
  public void setCurrentDailyIntakeWeek(List<DailyIntakeEntity> currentDailyIntakeWeek) {
    this.currentDailyIntakeWeek = currentDailyIntakeWeek;
  }
  
  public String calculatedTotal(DailyIntakeEntity dailyIntakeEntity, NutrientType nutrientType, TotalType totalType) {
    AccountEntity accountEntity = dailyIntakeEntity.getAccountEntity();
    Date intakeDate = dailyIntakeEntity.getIntakeDate();
    AccountSettingEntity accountSettingEntities = accountBO.findLatestSettingsForDate(accountEntity, intakeDate);
    BaseNutrient calculatedTotal = null;
    List<AccountWeightEntity> week = getCurrentAccountWeightWeek();
    AccountWeightEntity weight = null;
    if(week.stream().anyMatch(w -> DateUtils.isSameDate(w.getWeighInDate(), intakeDate))) {
      weight = week.stream().filter(w -> w.getWeighInDate().equals(intakeDate)).findFirst().get();
    }
    
    BaseNutrient expected = calculateExpected(nutrientType, accountEntity, accountSettingEntities, weight);
    
    switch(totalType) {
      case EXPECTED:
        calculatedTotal = expected;
        break;
      case CURRENT:
        calculatedTotal = IntakeCalculator.calculateTotalDailyIntake(dailyIntakeEntity, nutrientType);
        break;
      case REMAINING:
        calculatedTotal = expected;
        calculatedTotal.subtract(IntakeCalculator.calculateTotalDailyIntake(dailyIntakeEntity, nutrientType));
        break;
    }
    return calculatedTotal == null ? "" : calculatedTotal.getDisplayValue();
  }
  
  
  private BaseNutrient calculateExpected(NutrientType nutrientType, AccountEntity accountEntity, AccountSettingEntity accountSettingEntities, AccountWeightEntity accountWeightEntity) {
    Calorie calories = new Calorie();
    if(accountWeightEntity != null && (accountEntity.getBirthday() != null || accountSettingEntities.getAge() != null)
        && accountSettingEntities.getHeight() != null && accountEntity.getGender() != null) {
      double weight = accountWeightEntity.getWeight();
      double height = accountSettingEntities.getHeight();
      int age = accountSettingEntities.getAge() != null ? accountSettingEntities.getAge() : DateUtils.calculateAge(accountEntity.getBirthday());
      Gender gender = accountEntity.getGender();
      UnitSystem unitSystem = UnitSystem.IMPERIAL;
      calories = FormulaCalculation.calculateBMR(weight, height, age, gender, unitSystem);
    }
    
    Fat fat = new Fat();
    Carbohydrate carbs = new Carbohydrate();
    Protein protein = new Protein();
    //TODO: Implement splits. Just doing a 20/40/40 split right now
    if(calories.getAmount() > 0) {
      fat.setFromCalories(calories, 0.2);
      carbs.setFromCalories(calories, 0.4);
      protein.setFromCalories(calories, 0.4);
    }
    
    switch(nutrientType) {
      case CALORIE:
        return calories;
      case CARBOHYDRATE:
        return carbs;
      case FAT:
        return fat;
      case PROTEIN:
        return protein;
    }
    return BaseNutrient.create(nutrientType);
  }
}
