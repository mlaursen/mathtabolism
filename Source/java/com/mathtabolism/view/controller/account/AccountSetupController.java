/**
 * 
 */
package com.mathtabolism.view.controller.account;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.AccountStepForm;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.AccountSettingModel;
import com.mathtabolism.view.model.account.AccountWeightModel;
import com.mathtabolism.view.navigation.AccountNav;

/**
 *
 * @author mlaursen
 */
@ManagedBean
@ViewScoped
public class AccountSetupController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static final String ACTIVE_STEP_CSS = "current";
  private static final String EMPTY = "";
  
  public static final int MIN_BIRTHDAY_OFFSET = 80;
  public static final int MAX_BIRTHDAY_OFFSET = 5;

  private final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
  
  @Inject
  private AccountController accountController;
  @Inject
  private AccountBO accountBO;
  
  private AccountModel accountModel;
  private AccountStepForm activeStep = AccountStepForm.STEP1;
  
  @PostConstruct
  public void init() {
    this.accountModel = accountController.getAccountModel();
  }
  
  public void setAccountModel(AccountModel accountModel) {
    this.accountModel = accountModel;
  }
  
  public AccountModel getAccountModel() {
    return accountModel;
  }
  
  public String getStepCss(AccountStepForm step) {
    return activeStep.equals(step) ? ACTIVE_STEP_CSS : EMPTY;
  }
  
  public void nextStep() {
    activeStep = AccountStepForm.next(activeStep);
  }
  
  public void previousStep() {
    activeStep = AccountStepForm.previous(activeStep);
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
  
  public UnitSystem getUnitSystem() {
    return getCurrentSettings().getUnitSystem() == null ? UnitSystem.IMPERIAL : getCurrentSettings().getUnitSystem();
  }
  
  public void setUnitSystem(UnitSystem unitSystem) {
    AccountSettingModel currentSettings = getCurrentSettings();
    if(currentSettings.getUnitSystem() != null && !currentSettings.getUnitSystem().equals(unitSystem)) {
      accountModel.convertUnitsTo(unitSystem);
      currentSettings.convertUnitsTo(unitSystem);
    }
    currentSettings.setUnitSystem(unitSystem);
  }
  
  /**
   * Gets the current gender that is selected as a String. If this is a new account
   * with no settings set, the gender is defaulted to {@link Gender#MALE}.
   * @return a String
   */
  public String getSelectedGender() {
    return getString(accountModel.getGender());
  }
  
  /**
   * Gets the selected Formula Recalculation day as a String. If this is a new Account
   * with no settings set, the day is set to {@link Weekday#DAILY}
   * @return the String
   */
  public String getSelectedRecalculationDay() {
    return getString(getCurrentSettings().getRecalculationDay());
  }
  
  /**
   * Gets the selected activity multiplier as a String. If this is a new Account
   * with no settings set, it defaults to {@link ActivityMultiplier#SEDENTARY}.
   * @return the String
   */
  public String getSelectedActivityMultiplier() {
    return getString(getCurrentSettings().getActivityMultiplier());
  }
  
  /**
   * Gets the selected TDEEFormula as a String. If this is a new Account
   * with no settings set, it defaults to {@link TDEEFormula#MIFFLIN_ST_JEOR}.
   * @return the String
   */
  public String getSelectedFormula() {
    return getString(getCurrentSettings().getTdeeFormula());
  }
  
  /**
   * Gets the Selected Unit system as a String. If this is a new Account
   * with no settings set, it defaults to {@link UnitSystem#IMPERIAL}
   * @return the String
   */
  public String getSelectedUnitSystem() {
    return getString(getUnitSystem());
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
  public String createOrUpdateAccountSettings() {
    accountModel = accountBO.createOrUpdateSettings(accountModel);
    accountModel = accountBO.createOrUpdateWeight(accountModel);
    displayInfoMessage("account_UpdatedSettings");
    return redirect(AccountNav.DAILY_INTAKE);
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
   * Gets an array of SelectItem for feet. It is just counting from 1 - 8
   * @return an array of SelectItem
   */
  public SelectItem[] getFootItems() {
    SelectItem[] items = new SelectItem[8];
    for(int i = 1; i < 9; i++) {
      items[i - 1] = new SelectItem(i, "" + i);
    }
    return items;
  }
  
  /**
   * Gets an array of SelectItem for inches. it is just counting from 1 - 11.
   * @return an array of SelectItem
   */
  public SelectItem[] getInchItems() {
    SelectItem[] items = new SelectItem[12];
    for(int i = 0; i < 12; i++) {
      items[i] = new SelectItem(i, "" + i);
    }
    return items;
  }
  
  public String getCurrentDateFormat() {
    return isImperialUnitSystem() ? DateUtils.AMERICAN_DATE_FORMAT : DateUtils.EUROPEAN_DATE_FORMAT;
  }
  
  public boolean isImperialUnitSystem() {
    return UnitSystem.isImperial(getCurrentSettings().getUnitSystem());
  }
  
  public boolean isFirstStep() {
    return activeStep.isFirstStep();
  }
  
  public boolean isLastStep() {
    return activeStep.isLastStep();
  }
  
  public boolean isRecalculatedDaily() {
    return Weekday.DAILY.equals(getCurrentSettings().getRecalculationDay());
  }
  
  public String getExecutables() {
    switch(activeStep) {
      case STEP1:
        return "unit-system";
      case STEP2:
        return "gender height-imperial height-metric current-weight";
      case STEP3:
        return "activity-multiplier";
      case STEP4:
        return "recalculation-day tdee-formula";
      case STEP5:
        return "using-age " + (getCurrentSettings().isUsingAge() ? "age" : "birthday");
      default:
        return "";
    }
  }
  
  public String getBirthdayAgePrefix() {
    return getString(getCurrentSettings().isUsingAge() ? "ageStep1" : "birthdayStep");
  }
  
  public String getBirthdayAgeSuffux() {
    return getCurrentSettings().isUsingAge() ? getString("ageStep2") : "";
  }
}
