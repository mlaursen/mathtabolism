/**
 * 
 */
package com.mathtabolism.bo.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.resteasy.logging.Logger;
import org.joda.time.DateTime;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.converter.account.AccountConverter;
import com.mathtabolism.converter.account.DailyIntakeConverter;
import com.mathtabolism.eao.food.DailyIntakeEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
import com.mathtabolism.model.account.AccountModel;
import com.mathtabolism.model.account.AccountSettingModel;
import com.mathtabolism.model.account.AccountWeightModel;
import com.mathtabolism.model.food.DailyIntakeModel;
import com.mathtabolism.model.food.MealModel;
import com.mathtabolism.model.food.MealPartModel;
import com.mathtabolism.util.date.DateUtils;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeBO {
  private static Logger logger = Logger.getLogger(DailyIntakeBO.class);
  
  @Inject
  private DailyIntakeEAO dailyIntakeEAO;
  @Inject
  private AccountBO accountBO;
  @Inject
  private AccountConverter aConverter;
  @Inject
  private DailyIntakeConverter diConverter;
  
  public DailyIntakeBO() {
  }
  
  /**
   * Attempts to find a current DailyIntake week for an account. If the recalculation day
   * has not been set yet for the account, null is returned. If there currently is not
   * an existing week, a new week is generated.
   * @param accountModel the {@link AccountModel}
   * @return a list of {@link DailyIntakeModel} or null
   * @see DailyIntakeBO#generateNewWeek(AccountModel)
   */
  public List<DailyIntakeModel> findCurrentWeekForAccount(AccountModel accountModel) {
    AccountEntity account = aConverter.convertModelToEntity(accountModel);
    AccountSettingModel currentSettings = accountModel.getCurrentSettings();
    
    Weekday recalculationDay = currentSettings.getRecalculationDay();
    if(recalculationDay != null) {
      int recalcDOW = recalculationDay.toInt();
      List<DailyIntakeModel> currentWeek = diConverter.convertEntitiesToModels(dailyIntakeEAO.findCurrentWeek(account, DateUtils.findStartDate(recalcDOW)));
      if(currentWeek == null || currentWeek.isEmpty()) {
        currentWeek = generateNewWeek(accountModel);
      }
      
      return currentWeek;
    }
    return null;
  }
  
  /**
   * Creates or updates a DailyIntake for an account.
   * @param accountModel the {@link AccountModel}
   * @param dailyIntakeModel the {@link DailyIntakeModel}
   * @return an updated DailyIntakeModel
   */
  public DailyIntakeModel createOrUpdateDailyIntake(AccountModel accountModel, DailyIntakeModel dailyIntakeModel) {
    DailyIntakeEntity dailyIntake = diConverter.convertModelToEntity(dailyIntakeModel);
    dailyIntake.setAccountEntity(aConverter.convertModelToEntity(accountModel));
    if(dailyIntakeEAO.findById(dailyIntake) == null) {
      dailyIntakeEAO.create(dailyIntake);
    } else {
      dailyIntake = dailyIntakeEAO.update(dailyIntake);
    }
    
    return diConverter.convertEntityToModel(dailyIntake);
  }
  
  
  /**
   * Generates a new week of {@link DailyIntakeModel} for an account. If the recalculation day
   * has not yet been set for the account, null is returned.
   * 
   * <p>The associated {@link AccountWeightModel} will be set to the model as well.
   * 
   * @param accountModel the {@link AccountModel} to create a week for
   * @return a List of {@link DailyIntakeModel} or null
   */
  public List<DailyIntakeModel> generateNewWeek(AccountModel accountModel) {
    AccountSettingModel currentSettings = accountModel.getCurrentSettings();
    Weekday recalculationDay = currentSettings.getRecalculationDay();
    if(recalculationDay != null) {
      List<DailyIntakeModel> currentWeek = new ArrayList<>();
      int recalcDOW = recalculationDay.toInt();
      DateTime dt = DateUtils.findStartDate(recalcDOW);
      for(int i = 0; i < 7; i++) {
        Date intakeDate = dt.toDate();
        
        // TODO: Try to copy the previous week's multipliers and calorie changes
        DailyIntakeModel dailyIntakeModel = new DailyIntakeModel();
        dailyIntakeModel.setIntakeDate(intakeDate);
        dailyIntakeModel.setCalorieChange(0);
        dailyIntakeModel.setFatMultiplier(0.5);
        dailyIntakeModel.setCarbMultiplier(0.5);
        dailyIntakeModel.setProteinMultiplier(1.0);
        dailyIntakeModel.setAccountWeightModel(accountBO.findAccountWeightByDate(accountModel, intakeDate));
        
        dailyIntakeModel.setMeals(createDefaultMealList());
        
        dailyIntakeModel = createOrUpdateDailyIntake(accountModel, dailyIntakeModel);
        
        currentWeek.add(dailyIntakeModel);
        dt = dt.plusDays(1);
      }
      return currentWeek;
    }
    return null;
  }
  
  private List<MealModel> createDefaultMealList() {
    List<MealModel> meals = new ArrayList<>();
    for(int i = 0; i < 4; i++) {
      MealModel meal = new MealModel();
      meal.setName(String.format("Meal %02d", i + 1));
      meal.setMealParts(new ArrayList<MealPartModel>());
      
      meals.add(meal);
    }
    return meals;
  }
}
