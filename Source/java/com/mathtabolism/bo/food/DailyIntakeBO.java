/**
 * 
 */
package com.mathtabolism.bo.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.resteasy.logging.Logger;
import org.joda.time.DateTime;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.eao.food.DailyIntakeEAO;
import com.mathtabolism.eao.food.DailyIntakeMealEAO;
import com.mathtabolism.eao.food.MealEAO;
import com.mathtabolism.eao.food.MealPartEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.entity.food.MealPart;
import com.mathtabolism.util.date.MathtabolismDateUtils;
import com.mathtabolism.util.emconverter.EntityModelConverter;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.AccountWeightModel;
import com.mathtabolism.view.model.food.DailyIntakeModel;
import com.mathtabolism.view.model.food.MealModel;
import com.mathtabolism.view.model.food.MealPartModel;

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
  private DailyIntakeMealEAO dailyIntakeMealEAO;
  @Inject
  private MealEAO mealEAO;
  @Inject
  private MealPartEAO mealPartEAO;
  @Inject
  private AccountBO accountBO;
  @Inject
  private IngredientBO ingredientBO;
  @Inject
  private EntityModelConverter converter;
  
  public DailyIntakeBO() {
  }
  
  private List<DailyIntakeModel> toModels(List<DailyIntake> dailyIntakes) {
    List<DailyIntakeModel> dailyIntakeModels = new ArrayList<>();
    for(DailyIntake dailyIntake : dailyIntakes) {
      dailyIntakeModels.add(toModel(dailyIntake));
    }
    return dailyIntakeModels;
  }
  
  private DailyIntakeModel toModel(DailyIntake dailyIntake) {
    DailyIntakeModel dailyIntakeModel = converter.convertEntityToModel(dailyIntake);
    List<Meal> meals = dailyIntake.getMeals().stream().map(m -> m.getMeal()).collect(Collectors.toList());
    List<MealModel> mealModels = new ArrayList<>();
    for(Meal meal : meals) {
      MealModel mealModel = converter.convertEntityToModel(meal);
      mealModel.setMealParts(converter.convertEntitiesToModels(meal.getMealParts()));
      
      mealModels.add(mealModel);
    }
    dailyIntakeModel.setMeals(mealModels);
    
    return dailyIntakeModel;
  }
  
  private List<DailyIntake> toEntities(List<DailyIntakeModel> dailyIntakeModels, Account account) {
    List<DailyIntake> dailyIntakes = new ArrayList<>();
    for(DailyIntakeModel dailyIntakeModel : dailyIntakeModels) {
      dailyIntakes.add(toEntity(dailyIntakeModel, account));
    }
    return dailyIntakes;
  }
  
  private DailyIntake toEntity(DailyIntakeModel dailyIntakeModel, AccountModel accountModel) {
    return toEntity(dailyIntakeModel, converter.extractEntityFromModel(accountModel));
  }
  
  private DailyIntake toEntity(DailyIntakeModel dailyIntakeModel, Account account) {
    DailyIntake dailyIntake = converter.extractEntityFromModel(dailyIntakeModel);
    dailyIntake.setAccount(account);
    
    List<MealModel> mealModels = dailyIntakeModel.getMeals();
    List<DailyIntakeMeal> diMeals = new ArrayList<>();
    for(MealModel mealModel : mealModels) {
      Meal meal = converter.extractEntityFromModel(mealModel);
      meal.setMealParts(converter.extractEntitiesFromModels(mealModel.getMealParts()));
      
      DailyIntakeMeal diMeal = new DailyIntakeMeal();
      diMeal.setDailyIntake(dailyIntake);
      diMeal.setMeal(meal);
      
      diMeals.add(diMeal);
    }
    dailyIntake.setMeals(diMeals);
    
    return dailyIntake;
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
    Account account = converter.extractEntityFromModel(accountModel);
    
    Weekday recalculationDay = accountModel.getRecalculationDay();
    if(recalculationDay != null) {
      int recalcDOW = recalculationDay.toInt();
      List<DailyIntake> dailyIntakeWeek = dailyIntakeEAO.findCurrentWeek(account, MathtabolismDateUtils.findStartDate(recalcDOW));
      List<DailyIntakeModel> currentWeek;
      if(dailyIntakeWeek == null || dailyIntakeWeek.isEmpty()) {
        currentWeek = generateNewWeek(accountModel);
      } else {
        currentWeek = toModels(dailyIntakeWeek);
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
    DailyIntake dailyIntake = toEntity(dailyIntakeModel, accountModel);

    for(DailyIntakeMeal dailyIntakeMeal : dailyIntake.getMeals()) {
      createOrUpdateDailyIntakeMeal(dailyIntakeMeal);
    }
    
    if(dailyIntakeEAO.findById(dailyIntake) == null) {
      dailyIntakeEAO.create(dailyIntake);
    } else {
      dailyIntake = dailyIntakeEAO.update(dailyIntake);
    }
    
    return toModel(dailyIntake);
  }
  
  private void createOrUpdateDailyIntakeMeal(DailyIntakeMeal dailyIntakeMeal) {
    Meal meal = dailyIntakeMeal.getMeal();
    for(MealPart mealPart : meal.getMealParts()) {
      if(mealPartEAO.findById(mealPart) == null) {
        mealPartEAO.create(mealPart);
      } else {
        mealPart = mealPartEAO.update(mealPart);
      }
      
      mealPart.setMeal(meal);
    }
    
    if(mealEAO.findById(meal) == null) {
      mealEAO.create(meal);
    } else {
      meal = mealEAO.update(meal);
    }
    
    dailyIntakeMeal.setMeal(meal);
    
    if(dailyIntakeMealEAO.findById(dailyIntakeMeal) == null) {
      dailyIntakeMealEAO.create(dailyIntakeMeal);
    } else {
      dailyIntakeMeal = dailyIntakeMealEAO.update(dailyIntakeMeal);
    }
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
    Weekday recalculationDay = accountModel.getRecalculationDay();
    if(recalculationDay != null) {
      List<DailyIntakeModel> currentWeek = new ArrayList<>();
      int recalcDOW = recalculationDay.toInt();
      DateTime dt = MathtabolismDateUtils.findStartDate(recalcDOW);
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
        
        currentWeek.add(dailyIntakeModel);
        dt = dt.plusDays(1);
      }
      return currentWeek;
    }
    return null;
  }
  
  private List<MealModel> createDefaultMealList() {
    List<MealModel> meals = new ArrayList<>();
    for(int i = 1; i <= 5; i++) {
      MealModel meal = new MealModel();
      meal.setName(String.format("Meal %02d", i));
      meal.setMealParts(new ArrayList<MealPartModel>());
      
      meals.add(meal);
    }
    return meals;
  }
}
