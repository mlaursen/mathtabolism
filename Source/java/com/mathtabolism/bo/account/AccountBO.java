/**
 * 
 */
package com.mathtabolism.bo.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.entity.account.AccountWeight;
import com.mathtabolism.util.PasswordEncryption;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.emconverter.EntityModelConverter;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.AccountWeightModel;
import com.mathtabolism.view.model.account.CreateAccountModel;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountBO {
  private static Logger logger = Logger.getLogger(AccountBO.class);
  
  @Inject
  private AccountEAO accountEAO;
  @Inject
  private AccountSettingEAO accountSettingEAO;
  @Inject
  private AccountWeightEAO accountWeightEAO;
  @Inject
  private EntityModelConverter converter;
  
  public AccountModel findAccountById(String accountId) {
    Account account = accountEAO.findById(accountId);
    return setAccountModelFromAccount(account);
  }
  
  /**
   * Finds an Account by the given username and then loads all the data needed.
   * <p>The account info, the latest account settings, and the current weight are all loaded.
   * @param username the username to search by
   * @return the {@link AccountModel} with data populated from the database
   */
  public AccountModel findAccountByUsername(String username) {
    Account account = accountEAO.findAccountByUsername(username);
    return setAccountModelFromAccount(account);
  }
  
  private AccountModel setAccountModelFromAccount(Account account) {
    AccountSetting currentSettings = accountSettingEAO.findCurrentAccountSetting(account);
    AccountWeight currentWeight = accountWeightEAO.findTodaysWeight(account);
    AccountWeight previousWeight = accountWeightEAO.findLatestWeight(account);

    AccountModel accountModel = converter.convertEntityToModel(account);
    accountModel = converter.convertEntityToModel(currentSettings, accountModel);
    accountModel.splitHeight();
    accountModel.setCurrentWeight(currentWeight == null ? null : currentWeight.getWeight());
    accountModel.setPreviousWeight(previousWeight == null ? null : previousWeight.getWeight());
    
    return accountModel;
  }
  
  /**
   * Updates the last login date for an account 
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountModel updateLastLogin(AccountModel accountModel) {
    accountModel.setLastLogin(new Date());
    
    Account account = converter.extractEntityFromModel(accountModel);
    account = accountEAO.update(account);
    return accountModel;
  }
  
  /**
   * Updates the account setting information with an AccountModel. If there is no account setting
   * for the updated date, a new AccountSetting is created. Otherwise it is updated.
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountModel createOrUpdateAccountSettings(AccountModel accountModel) {
    Account account = converter.extractEntityFromModel(accountModel);
    accountEAO.update(account);
    
    AccountSetting accountSettings = converter.extractEntityFromModel(accountModel, AccountSetting.class);
    accountSettings.setAccount(account);
    accountSettings.setDateChanged(new Date());
    if(isLatestAccountSettingsToday(account)) {
      accountSettingEAO.update(accountSettings);
    } else {
      accountSettings.setId(null);
      accountSettingEAO.create(accountSettings);
    }
    
    accountModel = converter.convertEntityToModel(account, accountModel);
    accountModel = converter.convertEntityToModel(accountSettings, accountModel);
    return accountModel;
  }
  
  private boolean isLatestAccountSettingsToday(Account account) {
    return DateUtils.isSameDate(new Date(), accountSettingEAO.findLatestAccountSettingDateByAccount(account));
  }
  
  private boolean isNewWeight(AccountWeight weight) {
    return accountWeightEAO.findById(weight) == null;
  }
  
  /**
   * Creates a new weight or updates an existing weight for an AccountModel
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountWeightModel createOrUpdateWeight(AccountModel accountModel) {
    AccountWeightModel weightModel = new AccountWeightModel(accountModel.getCurrentWeight());
    weightModel.setWeighInDate(new Date());
    return createOrUpdateWeight(weightModel, accountModel);
  }
  
  public AccountWeightModel createOrUpdateWeight(AccountWeightModel weightModel, AccountModel accountModel) {
    AccountWeight weight = converter.extractEntityFromModel(weightModel);
    Account account = accountEAO.findById(accountModel.getAccountId());
    weight.setAccount(account);
    if(isNewWeight(weight)) {
      weight.setId(null);
      accountWeightEAO.create(weight);
    } else {
      weight = accountWeightEAO.update(weight);
    }
    
    return converter.convertEntityToModel(weight);
  }
  
  /**
   * Checks if a given username is currently available.
   * <p>A username is considered available if blank or there are no usernames that match the given username.
   * 
   * @param username the username to search for
   * @return true if the username is available.
   */
  public boolean isUsernameAvailable(String username) {
    return StringUtils.isBlank(username) || accountEAO.findAccountByUsername(username) == null;
  }
  
  /**
   * Creates a new account from a {@link CreateAccountModel}. Also creates a dummy {@link AccountSetting}
   * once the account was created.
   * 
   * @param createAccountModel the model to create from
   */
  public void createAccount(CreateAccountModel createAccountModel) {
    Account account = converter.extractEntityFromModel(createAccountModel);
    Date creationDate = Calendar.getInstance().getTime();
    account.setPassword(PasswordEncryption.encrypt(account.getPassword()));
    account.setRole(AccountRole.USER);
    account.setActiveSince(creationDate);
    accountEAO.create(account);
    
    AccountSetting accountSetting = new AccountSetting(account, creationDate);
    accountSettingEAO.create(accountSetting);
  }
  
  /**
   * Attempts to find a current week of Account Weights for an account. If the recalculation day
   * has not been set for the account settings, null is returned.
   * @param accountModel the {@link AccountModel}
   * @return a List of {@link AccountWeightModel} or null
   */
  public List<AccountWeightModel> findCurrentAccountWeightWeek(AccountModel accountModel) {
    Weekday recalcDay = accountModel.getRecalculationDay();
    if(recalcDay != null) {
      DateTime startDate = DateUtils.findStartDate(recalcDay.toInt());
      List<AccountWeight> weights = accountWeightEAO.findCurrentAccountWeightWeek(accountModel.getAccountId(), startDate);
      List<AccountWeightModel> weightModels = new ArrayList<>();
      for(AccountWeight weight : weights) {
        weightModels.add(converter.convertEntityToModel(weight));
      }
      return weightModels;
    }
    return null;
  }
  
  /**
   * Attempts to find an account weight by a given date. If the weight for the specified day does not exist, 
   * an empty {@link AccountWeightModel} is returned
   * @param accountModel the {@link AccountModel}
   * @param date a Date to search for
   * @return the {@link AccountWeightModel} or null
   */
  public AccountWeightModel findAccountWeightByDate(AccountModel accountModel, Date date) {
    AccountWeight accountWeight = accountWeightEAO.findAccountWeightByDate(accountModel.getAccountId(), date);
    if(accountWeight != null) {
      return converter.convertEntityToModel(accountWeight);
    } else {
      return null;
    }
  }
  
  /**
   * 
   * @param accountModel
   * @param date
   * @return
   */
  public AccountModel findLatestSettingsForDate(AccountModel accountModel, Date date) {
    return converter.convertEntityToModel(accountSettingEAO.findLatestSettingsForDate(accountModel.getAccountId(), date));
  }
  
  public List<AccountWeight> findCurrentAccountWeightWeek(Account account, AccountSetting currentSettings) {
    DateTime startDate = DateUtils.findStartDate(currentSettings.getRecalculationDay().toInt());
    return accountWeightEAO.findCurrentAccountWeightWeek(account.getId(), startDate);
  }
  
  public AccountWeight findTodaysWeight(Account account) {
    return accountWeightEAO.findTodaysWeight(account);
  }
  
  public AccountWeight findLatestWeight(Account account) {
    return accountWeightEAO.findLatestWeight(account);
  }
}
