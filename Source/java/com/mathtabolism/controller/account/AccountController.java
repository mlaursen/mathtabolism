/**
 * 
 */
package com.mathtabolism.controller.account;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.model.account.AccountModel;
import com.mathtabolism.model.account.AccountSettingModel;
import com.mathtabolism.model.account.AccountWeightModel;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Named
@SessionScoped
public class AccountController extends BaseController {
  private static final long serialVersionUID = 5069047046599920651L;
  public static final int MIN_BIRTHDAY_OFFSET = 80;
  public static final int MAX_BIRTHDAY_OFFSET = 5;

  private final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
  
  
  @Inject
  private AccountBO accountBO;
  
  private AccountModel accountModel;
  
  /**
   * Lazily loads the AccountModel.
   * <p>If the account model was null, the user has just logged in. The username is searched 
   * in the external context and then an account is attempted to be found. (It should be since the
   * jaas authentication allowed us to get here).
   * 
   * @return the account model
   */
  public AccountModel getAccountModel() {
    if(accountModel == null) {
      String username = getContext().getExternalContext().getUserPrincipal().getName();
      accountModel = accountBO.findAccountByUsername(username);
      accountModel = accountBO.updateLastLogin(accountModel);
    }
    return accountModel;
  }
  
  /**
   * Gets the current settings
   * @return the current settings
   */
  public AccountSettingModel getCurrentSettings() {
    return accountModel.getCurrentSettings();
  }
  
  /**
   * Gets the current account weight
   * @return the current weight
   */
  public AccountWeightModel getCurrentWeight() {
    return accountModel.getCurrentWeight();
  }
  
  /**
   * Gets the previous account weight
   * @return the previous weight
   */
  public AccountWeightModel getPreviousWeight() {
    return accountModel.getPreviousWeight();
  }
  
  /**
   * Gets the current gender that is selected as a String. If this is a new account
   * with no settings set, the gender is defaulted to {@link Gender#MALE}.
   * @return a String
   */
  public String getSelectedGender() {
    if(accountModel.getGender() == null) {
      accountModel.setGender(Gender.MALE);
    }
    
    return getString(accountModel.getGender());
  }
  
  /**
   * Gets the selected Formula Recalculation day as a String. If this is a new Account
   * with no settings set, the day is set to {@link Weekday#DAILY}
   * @return the String
   */
  public String getSelectedRecalculationDay() {
    AccountSettingModel currentSettings = getCurrentSettings();
    if(currentSettings.getRecalculationDay() == null) {
      currentSettings.setRecalculationDay(Weekday.DAILY);
    }
    
    return getString(currentSettings.getRecalculationDay());
  }
  
  /**
   * Gets the selected activity multiplier as a String. If this is a new Account
   * with no settings set, it defaults to {@link ActivityMultiplier#SEDENTARY}.
   * @return the String
   */
  public String getSelectedActivityMultiplier() {
    AccountSettingModel currentSettings = getCurrentSettings();
    if(currentSettings.getActivityMultiplier() == null) {
      currentSettings.setActivityMultiplier(ActivityMultiplier.SEDENTARY);
    }
    return getString(currentSettings.getActivityMultiplier());
  }
  
  /**
   * Gets the selected TDEEFormula as a String. If this is a new Account
   * with no settings set, it defaults to {@link TDEEFormula#MIFFLIN_ST_JEOR}.
   * @return the String
   */
  public String getSelectedFormula() {
    AccountSettingModel currentSettings = getCurrentSettings();
    if(currentSettings.getTdeeFormula() == null) {
      currentSettings.setTdeeFormula(TDEEFormula.MIFFLIN_ST_JEOR);
    }
    return getString(currentSettings.getTdeeFormula());
  }
  
  /**
   * Gets the Selected Unit system as a String. If this is a new Account
   * with no settings set, it defaults to {@link UnitSystem#IMPERIAL}
   * @return the String
   */
  public String getSelectedUnitSystem() {
    return getString(getCurrentUnitSystem());
  }
  
  /**
   * Gets the current selected Unit System as a String. If this is a new Account
   * with no settings set, it defaults to {@link UnitSystem#IMPERIAL}
   * @return the String
   */
  private UnitSystem getCurrentUnitSystem() {
    AccountSettingModel currentSettings = getCurrentSettings();
    if(currentSettings.getUnitSystem() == null) {
      currentSettings.setUnitSystem(UnitSystem.IMPERIAL);
    }
    return currentSettings.getUnitSystem();
  }
  
  /**
   * Gets the Genders as an array of SelectItem for the dropdown selection.
   * @return an array of SelectItem
   */
  public SelectItem[] getGenders() {
    return convertEnumToSelectItems(Gender.values());
  }
  
  /**
   * Gets the Recalculation Days as an array of SelectItem for the dropdown selection.
   * @return an array of SelectItem
   */
  public SelectItem[] getWeekdays() {
    return convertEnumToSelectItems(Weekday.values());
  }
  
  /**
   * Gets the Activity Multipliers as an array of SelectItem for the dropdown selection.
   * @return an array of SelectItem
   */
  public SelectItem[] getActivityMultipliers() {
    return convertEnumToSelectItems(ActivityMultiplier.values());
  }
  
  /**
   * Gets the TDEE Formulas as an array of SelectItem for the dropdown selection.
   * @return an array of SelectItem
   */
  public SelectItem[] getFormulas() {
    return convertEnumToSelectItems(TDEEFormula.values());
  }
  
  /**
   * Gets the Unit Systems as an array of SelectItem for the dropdown selection.
   * @return an array of SelectItem
   */
  public SelectItem[] getUnitSystems() {
    return convertEnumToSelectItems(UnitSystem.values());
  }
  
  /**
   * Creates or updates the Account's Settings/configuration
   */
  public void createOrUpdateAccountSettings() {
    accountModel = accountBO.createOrUpdateSettings(accountModel);
    displayInfoMessage("account_UpdatedSettings");
  }
  
  /**
   * Creates or updates the current weight
   */
  public void createOrUpdateCurrentWeight() {
    accountBO.createOrUpdateWeight(accountModel);
    displayInfoMessage("account_UpdatedWeight");
  }
  
  /**
   * Gets the {@link #CURRENT_YEAR} - {@link #MIN_BIRTHDAY_OFFSET}
   * @return minimum birthday year
   */
  public int getMinBirthdayYear() {
    return CURRENT_YEAR - MIN_BIRTHDAY_OFFSET;
  }
  
  /**
   * Gets the {@link #CURRENT_YEAR} + {@link #MAX_BIRTHDAY_OFFSET}
   * @return the maximum birthday year
   */
  public int getMaxBirthdayYear() {
    return CURRENT_YEAR + MAX_BIRTHDAY_OFFSET;
  }
  
  /**
   * Checks if the current weight is set for the account.
   * <p>The current weight is considered set if
   * <ul>
   * <li>The current weight is not null
   * <li>The current weight is greater than 0
   * <li>The weigh in date is today
   * </ul>
   * @return true if the weight is set
   */
  public boolean isTodayWeightSet() {
    AccountWeightModel currentWeight = getAccountModel().getCurrentWeight();
    return currentWeight != null && DateUtils.isSameDate(currentWeight.getWeighInDate(), new Date())
        && currentWeight.getWeight() > 0;
  }
  
  /**
   * Checks if the account is considered a first time user. (Someone that has no settings set)
   * @return true if the account is considered a first time user
   */
  public boolean isFirstTimeUser() {
    AccountSettingModel currentSettings = getAccountModel().getCurrentSettings();
    return DateUtils.isSameDate(accountModel.getActiveSince(), new Date())
        || currentSettings == null || currentSettings.getActivityMultiplier() == null
        || currentSettings.getTdeeFormula() == null || currentSettings.getUnitSystem() == null
        || (accountModel.getBirthday() == null || currentSettings.getAge() == null);
  }

  /**
   * Gets an array of SelectItem for the dropdown choice of Large Heights (Feet or Meters)
   * This will be an array of numbers start at 1 going to 9 for Imperial and going to 3 for Metric
   * @return an array of SelectItem
   */
  public SelectItem[] getStartingLargeHeights() {
    int min = 1;
    int max;
    switch(getCurrentUnitSystem()) {
      case IMPERIAL:
        max = 9;
        break;
      case METRIC:
        max = 3;
        break;
      default:
        max = min;
        break;
    }
    SelectItem[] items = new SelectItem[max + min];
    for(; min <= max; min++) {
      items[min] = new SelectItem(min, "" + min);
    }
    return items;
  }
  
  /**
   * Gets an array of SelectItem for the dropdown choce of Small Heights (Inches or Centimeters).
   * This will be an array of numbers starting at 1 going to 11 for Imperial and going to 9 for Metric.
   * <p>0 is ignored since it is really the large unit still. 12 is ignored for imperial because
   * it is considered a foot and 10 is ignored for metric because it is considered a meter.
   * @return an array of SelectItem
   */
  public SelectItem[] getStartingSmallHeights() {
    int min = 1;
    int max;
    switch(getCurrentUnitSystem()) {
      case IMPERIAL:
        max = 11; // 12 is really a foot
        break;
      case METRIC:
        max = 9; // 10 is really a meter
        break;
      default:
        max = min;
        break;
    }
    SelectItem[] items = new SelectItem[max + min];
    for(; min <= max; min++) {
      items[min] = new SelectItem(min, "" + min);
    }
    return items;
  }
}
