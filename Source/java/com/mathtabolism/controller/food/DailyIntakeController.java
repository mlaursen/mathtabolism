/**
 * 
 */
package com.mathtabolism.controller.food;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.bo.food.DailyIntakeBO;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.MealFactType;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.constants.TotalType;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.controller.account.AccountController;
import com.mathtabolism.emcontract.Account;
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.emcontract.AccountWeight;
import com.mathtabolism.model.account.AccountModel;
import com.mathtabolism.model.account.AccountSettingModel;
import com.mathtabolism.model.account.AccountWeightModel;
import com.mathtabolism.model.food.DailyIntakeModel;
import com.mathtabolism.model.food.MealModel;
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
  private AccountController accountController;
  
  private AccountModel accountModel;
  private List<DailyIntakeModel> dailyIntakes;
  
  public DailyIntakeController() {
  }
  
  @PostConstruct
  public void init() {
    accountModel = accountController.getAccountModel();
  }
  
  public List<DailyIntakeModel> getDailyIntakes() {
    if(dailyIntakes == null || dailyIntakes.isEmpty()) {
      dailyIntakes = dailyIntakeBO.findCurrentWeekForAccount(accountModel);
    }
    return dailyIntakes;
  }
  
  public String getMealFact(MealModel mealModel, MealFactType mealFactType) {
    switch(mealFactType) {
      case NAME:
        return mealModel.getName();
      case CALORIE:
        return IntakeCalculator.calculateMealNutrients(mealModel.getMealParts(), NutrientType.CALORIE).getDisplayValue();
      case FAT:
        return IntakeCalculator.calculateMealNutrients(mealModel.getMealParts(), NutrientType.FAT).getDisplayValue();
      case CARBOHYDRATE:
        return IntakeCalculator.calculateMealNutrients(mealModel.getMealParts(), NutrientType.CARBOHYDRATE).getDisplayValue();
      case PROTEIN:
        return IntakeCalculator.calculateMealNutrients(mealModel.getMealParts(), NutrientType.PROTEIN).getDisplayValue();
    }
    return "";
  }
  
  public String calculatedTotal(DailyIntakeModel dailyIntakeModel, NutrientType nutrientType, TotalType totalType) {
    Date intakeDate = dailyIntakeModel.getIntakeDate();
    AccountSettingModel accountSettings = accountBO.findLatestSettingsForDate(accountModel, intakeDate);
    BaseNutrient calculatedTotal = null;
    AccountWeightModel accountWeight = dailyIntakeModel.getAccountWeightModel();
    
    BaseNutrient expected = calculateExpected(nutrientType, accountModel, accountSettings, accountWeight);
    
    switch(totalType) {
      case EXPECTED:
        calculatedTotal = expected;
        break;
      case CURRENT:
        calculatedTotal = IntakeCalculator.calculateTotalDailyIntake(dailyIntakeModel, nutrientType);
        break;
      case REMAINING:
        calculatedTotal = expected;
        calculatedTotal.subtract(IntakeCalculator.calculateTotalDailyIntake(dailyIntakeModel, nutrientType));
        break;
    }
    return calculatedTotal == null ? "" : calculatedTotal.getDisplayValue();
  }
  
  private BaseNutrient calculateExpected(NutrientType nutrientType, Account account, AccountSetting accountSettings,
      AccountWeight accountWeight) {
    Calorie calories = new Calorie();
    if(accountWeight != null && (account.getBirthday() != null || accountSettings.getAge() != null)
        && accountSettings.getHeight() != null && account.getGender() != null) {
      double weight = accountWeight.getWeight();
      double height = accountSettings.getHeight();
      int age = accountSettings.getAge() != null ? accountSettings.getAge() : DateUtils.calculateAge(account.getBirthday());
      Gender gender = account.getGender();
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
