/**
 * 
 */
package com.mathtabolism.controller.account;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.util.number.NumberUtils;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Named
@SessionScoped
public class AccountController extends BaseController {
  private static final long serialVersionUID = 5069047046599920651L;
  private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
  public static final int MIN_BIRTHDAY_OFFSET = 80;
  public static final int MAX_BIRTHDAY_OFFSET = 5;
  
  
  @Inject
  private AccountBO accountBO;
  
  private AccountEntity accountEntity;
  private AccountSettingEntity currentSettings;
  private AccountWeightEntity currentWeight;
  private AccountWeightEntity previousWeight;
  private String heightLarge;
  private String heightSmall;
  
  /**
   * Lazy loads the account.
   * <p>Also sets the currentSettings, currentWeight, previousWeight, and the last login date
   * 
   * <p>This should technically be an AccountModel or something.
   * 
   * @return the account
   */
  public AccountEntity getAccount() {
    if(accountEntity == null) {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      String username = context.getUserPrincipal().getName();
      accountEntity = accountBO.findAccountByUsername(username);
      currentSettings = accountBO.findAccountSettingsForAccount(accountEntity);
      currentWeight = accountBO.findTodaysWeight(accountEntity);
      if(currentWeight == null) {
        currentWeight = new AccountWeightEntity(accountEntity, new Date());
      }
      previousWeight = accountBO.findLatestWeight(accountEntity);
      if(accountEntity != null) {
        accountEntity = accountBO.updateLastLogin(accountEntity);
      }
    }
    return accountEntity;
  }
  
  /**
   * Gets the current account settings
   * @return the current account settings
   */
  public AccountSettingEntity getCurrentSettings() {
    return currentSettings;
  }
  
  /**
   * Sets the current account settings
   * @param currentSettings the current account settings
   */
  public void setCurrentSettings(AccountSettingEntity currentSettings) {
    this.currentSettings = currentSettings;
  }
  
  /**
   * Gets the current weight for the account.
   * @return the current weight formatted to 2 decimal places or the empty String
   */
  public String getCurrentWeight() {
    return currentWeight == null ? "" : NumberUtils.formatAsString(currentWeight.getWeight(), 2);
  }
  
  /**
   * Gets the previous weight for the account.
   * <p>This is really only used as a suggested weight for that update weight div.
   * 
   * @return the previous weight formatted to 2 decimal places or the empty String
   */
  public String getPreviousWeight() {
    return previousWeight == null ? "" : NumberUtils.formatAsString(previousWeight.getWeight(), 2);
  }
  
  /**
   * Sets the current weight for the account
   * @param currentWeight the current weight String
   */
  public void setCurrentWeight(String currentWeight) {
    this.currentWeight.setWeight(currentWeight);
  }
  
  /**
   * Gets the selected gender for the account. If the gender is not set,
   * it defaults to Male
   * @return the selected gender
   */
  public String getSelectedGender() {
    if(accountEntity.getGender() == null) {
      accountEntity.setGender(Gender.MALE);
    }
    return getString(accountEntity.getGender());
  }
  
  /**
   * Gets the account's selected recalculation day. If it is not set, it
   * defaults to daily. If the recalculation day is not {@link Weekday#DAILY},
   * "each " is appened to the String.
   * <p>Example: "every Sunday"
   * @return the selected recalculation day as a String
   */
  public String getSelectedWeekday() {
    if(currentSettings.getRecalculationDay() == null) {
      currentSettings.setRecalculationDay(Weekday.DAILY);
    }
    return getString(currentSettings.getRecalculationDay());
  }
  
  /**
   * Gets the account's activity multiplier. If it is not set, it defaults to {@link ActivityMultiplier#SEDENTARY}.
   * 
   * @return the selected activity multiplier as a String
   */
  public String getSelectedActivityMultiplier() {
    if(currentSettings.getActivityMultiplier() == null) {
      currentSettings.setActivityMultiplier(ActivityMultiplier.SEDENTARY);
    }
    return getString(currentSettings.getActivityMultiplier());
  }
  
  /**
   * Gets the account's selected TDEEFormula. If it is not set, it defaults to {@link TDEEFormula#MIFFLIN_ST_JEOR}.
   * @return the selected TDEE Formula as a String
   */
  public String getSelectedFormula() {
    if(currentSettings.getTdeeFormula() == null) {
      currentSettings.setTdeeFormula(TDEEFormula.MIFFLIN_ST_JEOR);
    }
    return getString(currentSettings.getTdeeFormula());
  }
  
  /**
   * Gets the Selected Unit system. If the Unit System is not set, it
   * is defaulted to {@link UnitSystem#IMPERIAL}
   * @return the selected Unit System
   */
  public String getSelectedUnitSystem() {
    return getString(getCurrentUnitSystem());
  }
  
  private UnitSystem getCurrentUnitSystem() {
    if(currentSettings.getUnitSystem() == null) {
      currentSettings.setUnitSystem(UnitSystem.IMPERIAL);
    }
    return currentSettings.getUnitSystem();
  }
  
  /**
   * 
   * @param accountEntity
   */
  public void setAccount(AccountEntity accountEntity) {
    this.accountEntity = accountEntity;
  }
  
  public boolean isAccountAdmin() {
    return getRequest().isUserInRole("ADMIN");
  }
  
  public SelectItem[] getGenders() {
    return convertEnumToSelectItems(Gender.values());
  }
  
  public SelectItem[] getWeekdays() {
    return convertEnumToSelectItems(Weekday.values());
  }
  
  public SelectItem[] getActivityMultipliers() {
    return convertEnumToSelectItems(ActivityMultiplier.values());
  }
  
  public SelectItem[] getFormulas() {
    return convertEnumToSelectItems(TDEEFormula.values());
  }
  
  public SelectItem[] getUnitSystems() {
    return convertEnumToSelectItems(UnitSystem.values());
  }
  
  public void saveUpdatedSettings() {
    accountBO.updateSettings(accountEntity, currentSettings);
    displayInfoMessage("account_UpdatedSettings");
  }
  
  public void saveCurrentWeight() {
    currentWeight = accountBO.createOrUpdateWeight(currentWeight);
    displayInfoMessage("account_UpdatedWeight");
  }
  
  /**
   * 
   * @return the minimum year for a person's birthday
   */
  public int getMinBirthdayYear() {
    return CURRENT_YEAR - MIN_BIRTHDAY_OFFSET;
  }
  
  /**
   * 
   * @return the maximum year for a person's birthday
   */
  public int getMaxBirthdayYear() {
    return CURRENT_YEAR + MAX_BIRTHDAY_OFFSET;
  }
  
  public boolean isTodayWeightSet() {
    getAccount();
    return currentWeight != null && DateUtils.isSameDay(currentWeight.getWeighInDate(), 
        Calendar.getInstance().getTime()) && currentWeight.getWeight() > 0;
  }
  
  /**
   * Checks if the current account is considered a first time user. A first time user is someone that has not gone
   * through the account initialization fancy page. (They don't have any settings set)
   * @return true if the account is considered a first time user
   */
  public boolean isFirstTimeUser() {
    getAccount();
    return DateUtils.isSameDay(accountEntity.getActiveSince(), new Date()) || currentSettings == null
        || currentSettings.getActivityMultiplier() == null || currentSettings.getHeight() == null
        || currentSettings.getRecalculationDay() == null || currentSettings.getTdeeFormula() == null;
  }

  public String getHeightLarge() {
    return heightLarge;
  }

  public void setHeightLarge(String heightLarge) {
    this.heightLarge = heightLarge;
    currentSettings.setHeight(NumberUtils.stringToDouble(heightLarge) + NumberUtils.stringToDouble(heightSmall));
  }

  public String getHeightSmall() {
    return heightSmall;
  }

  public void setHeightSmall(String heightSmall) {
    this.heightSmall = heightSmall;
    currentSettings.setHeight(NumberUtils.stringToDouble(heightLarge) + NumberUtils.stringToDouble(heightSmall));
  }
  
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
