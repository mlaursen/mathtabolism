/**
 * 
 */
package com.mathtabolism.view.controller.account;

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
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.AccountSettingModel;
import com.mathtabolism.view.model.account.AccountWeightModel;

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
  
  public void updateAccountModel() {
    accountModel = accountBO.findAccountById(accountModel.getId());
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
  
  public String getDateFormat() {
    AccountSettingModel currentSettings = accountModel.getCurrentSettings();
    UnitSystem unitSystem = currentSettings != null && currentSettings.getUnitSystem() != null ? currentSettings.getUnitSystem() : UnitSystem.IMPERIAL;
    switch(unitSystem) {
      case METRIC:
        return "EEEE, dd MMMM YYYY";
      default:
        return "EEEE, MMMM dd, YYYY";
    }
  }
}
