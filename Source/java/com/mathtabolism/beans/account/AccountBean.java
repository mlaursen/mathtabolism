/**
 * 
 */
package com.mathtabolism.beans.account;

import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;

import com.mathtabolism.beans.BaseBean;
import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.entity.account.AccountWeight;
import com.mathtabolism.util.number.NumberUtils;

/**
 * 
 * @author mlaursen
 */
@Named
@SessionScoped
public class AccountBean extends BaseBean {
  private static final long serialVersionUID = 5069047046599920651L;
  @Inject
  private AccountBO accountBO;
  
  private Account account;
  private AccountSetting currentSettings;
  private AccountWeight currentWeight;
  
  private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
  public static final int MIN_BIRTHDAY_OFFSET = 80;
  public static final int MAX_BIRTHDAY_OFFSET = 5;
  
  /**
   * Gets the current account. If the account is null, it attempts to get it from the FacesContext
   * 
   * @return the account
   */
  public Account getAccount() {
    if(account == null) {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      String username = context.getUserPrincipal().getName();
      account = accountBO.findAccountByUsername(username);
      currentSettings = accountBO.findAccountSettingsForAccount(account);
      currentWeight = accountBO.findTodaysWeight(account);
      if(account != null) {
        account = accountBO.updateLastLogin(account);
      }
    }
    return account;
  }
  
  /**
   * 
   * @return
   */
  public AccountSetting getCurrentSettings() {
    return currentSettings;
  }
  
  /**
   * 
   * @param currentSettings
   */
  public void setCurrentSettings(AccountSetting currentSettings) {
    this.currentSettings = currentSettings;
  }
  
  public String getCurrentWeight() {
    return NumberUtils.formatAsString(currentWeight.getWeight(), 2);
  }
  
  public void setCurrentWeight(String currentWeight) {
    this.currentWeight.setWeight(currentWeight);
  }
  
  public String getSelectedWeekday() {
    return getString(currentSettings.getRecalculationDay());
  }
  
  public String getSelectedActivityMultiplier() {
    return getString(currentSettings.getActivityMultiplier());
  }
  
  public String getSelectedFormula() {
    return getString(currentSettings.getTdeeFormula());
  }
  
  /**
   * 
   * @param account
   */
  public void setAccount(Account account) {
    this.account = account;
  }
  
  public boolean isAccountAdmin() {
    return getRequest().isUserInRole("ADMIN");
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
  
  public void saveUpdatedSettings() {
    accountBO.updateSettings(account, currentSettings);
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
    return DateUtils.isSameDay(currentWeight.getWeighInDate(), Calendar.getInstance().getTime())
        && currentWeight.getWeight() > 0;
  }
}
