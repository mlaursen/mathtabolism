/**
 * 
 */
package com.mathtabolism.bo.account;

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
import com.mathtabolism.converter.account.AccountConverter;
import com.mathtabolism.converter.account.AccountSettingConverter;
import com.mathtabolism.converter.account.AccountWeightConverter;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.model.account.AccountModel;
import com.mathtabolism.model.account.AccountSettingModel;
import com.mathtabolism.model.account.AccountWeightModel;
import com.mathtabolism.model.account.CreateAccountModel;
import com.mathtabolism.util.PasswordEncryption;
import com.mathtabolism.util.date.DateUtils;

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
  private AccountConverter aConverter;
  @Inject
  private AccountSettingConverter asConverter;
  @Inject
  private AccountWeightConverter awConverter;
  
  
  /**
   * Finds an Account by the given username and then loads all the data needed.
   * <p>The account info, the latest account settings, and the current weight are all loaded.
   * @param username the username to search by
   * @return the {@link AccountModel} with data populated from the database
   */
  public AccountModel findAccountByUsername(String username) {
    AccountEntity account = accountEAO.findAccountByUsername(username);
    AccountSettingEntity currentSettings = accountSettingEAO.findCurrentAccountSetting(account);
    AccountWeightEntity currentWeight = accountWeightEAO.findTodaysWeight(account);
    if(currentWeight == null) {
      currentWeight = new AccountWeightEntity(account, new Date());
    }
    AccountWeightEntity previousWeight = accountWeightEAO.findLatestWeight(account);

    AccountModel accountModel = aConverter.convertEntityToModel(account);
    AccountSettingModel accountSettingModel = asConverter.convertEntityToModel(currentSettings);
    AccountWeightModel currentWeightModel = awConverter.convertEntityToModel(currentWeight);
    AccountWeightModel previousWeightModel = awConverter.convertEntityToModel(previousWeight);
    
    accountModel.setCurrentSettings(accountSettingModel);
    accountModel.setCurrentWeight(currentWeightModel);
    accountModel.setPreviousWeight(previousWeightModel);
    
    return accountModel;
  }
  
  /**
   * Updates the last login date for an account 
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountModel updateLastLogin(AccountModel accountModel) {
    accountModel.setLastLogin(new Date());
    
    AccountEntity account = aConverter.convertModelToEntity(accountModel);
    account = accountEAO.update(account);
    return accountModel;
  }
  
  /**
   * Updates the account setting information with an AccountModel. If there is no account setting
   * for the updated date, a new AccountSetting is created. Otherwise it is updated.
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountModel createOrUpdateSettings(AccountModel accountModel) {
    AccountEntity account = aConverter.convertModelToEntity(accountModel);
    accountEAO.update(account);
    
    AccountSettingModel currentSettingsModel = accountModel.getCurrentSettings();
    currentSettingsModel.setDateChanged(new Date());
    
    AccountSettingEntity currentSettings = asConverter.convertModelToEntity(currentSettingsModel);
    if(DateUtils.isSameDate(currentSettings.getDateChanged(), accountSettingEAO.findLatestAccountSettingDateByAccount(account))) {
      accountSettingEAO.update(currentSettings);
    } else {
      accountSettingEAO.create(currentSettings);
    }
    return accountModel;
  }
  
  /**
   * Creates a new weight or updates an existing weight for an AccountModel
   * @param accountModel the {@link AccountModel}
   * @return the updated <tt>accountModel</tt>
   */
  public AccountModel createOrUpdateWeight(AccountModel accountModel) {
    AccountWeightEntity currentWeight = awConverter.convertModelToEntity(accountModel.getCurrentWeight());
    AccountWeightEntity existingWeight = accountWeightEAO.findById(currentWeight);
    if(existingWeight == null) {
      currentWeight = accountWeightEAO.update(currentWeight);
    } else {
      AccountEntity account = aConverter.convertModelToEntity(accountModel);
      AccountWeightEntity newWeight = new AccountWeightEntity(account, new Date());
      newWeight.setWeight(currentWeight.getWeight());
      accountWeightEAO.create(newWeight);
      accountModel.setCurrentWeight(awConverter.convertEntityToModel(newWeight));
    }
    return accountModel;
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
   * Creates a new account from a {@link CreateAccountModel}. Also creates a dummy {@link AccountSettingEntity}
   * once the account was created.
   * 
   * @param createAccountModel the model to create from
   */
  public void createAccount(CreateAccountModel createAccountModel) {
    AccountEntity account = aConverter.convertModelToEntity(createAccountModel);
    Date creationDate = Calendar.getInstance().getTime();
    account.setPassword(PasswordEncryption.encrypt(account.getPassword()));
    account.setRole(AccountRole.USER);
    account.setActiveSince(creationDate);
    accountEAO.create(account);
    
    AccountSettingEntity accountSetting = new AccountSettingEntity(account, creationDate);
    accountSettingEAO.create(accountSetting);
  }
  
  /**
   * Attempts to find a current week of Account Weights for an account. If the recalculation day
   * has not been set for the account settings, null is returned.
   * @param accountModel the {@link AccountModel}
   * @return a List of {@link AccountWeightModel} or null
   */
  public List<AccountWeightModel> findCurrentAccountWeightWeek(AccountModel accountModel) {
    Weekday recalcDay = accountModel.getCurrentSettings().getRecalculationDay();
    if(recalcDay != null) {
      DateTime startDate = DateUtils.findStartDate(recalcDay.toInt());
      return awConverter.convertEntitiesToModels(accountWeightEAO.findCurrentAccountWeightWeek(accountModel.getId(), startDate));
    }
    return null;
  }
  
  /**
   * Attempts to find an account weight by a given date. If the weight for the specified day does not exist, 
   * an empty {@link AccountWeightModel} is returned
   * @param accountModel the {@link AccountModel}
   * @param date a Date to search for
   * @return the {@link AccountWeightModel} or an empty {@link AccountWeightModel}
   */
  public AccountWeightModel findAccountWeightByDate(AccountModel accountModel, Date date) {
    AccountWeightEntity accountWeight = accountWeightEAO.findAccountWeightByDate(accountModel.getId(), date);
    if(accountWeight != null) {
      return awConverter.convertEntityToModel(accountWeight);
    } else {
      return new AccountWeightModel();
    }
  }
  
  /**
   * 
   * @param accountModel
   * @param date
   * @return
   */
  public AccountSettingModel findLatestSettingsForDate(AccountModel accountModel, Date date) {
    return asConverter.convertEntityToModel(accountSettingEAO.findLatestSettingsForDate(accountModel.getId(), date));
  }
  
  public List<AccountWeightEntity> findCurrentAccountWeightWeek(AccountEntity accountEntity, AccountSettingEntity currentSettings) {
    DateTime startDate = DateUtils.findStartDate(currentSettings.getRecalculationDay().toInt());
    return accountWeightEAO.findCurrentAccountWeightWeek(accountEntity.getId(), startDate);
  }
  
  public AccountWeightEntity findTodaysWeight(AccountEntity accountEntity) {
    return accountWeightEAO.findTodaysWeight(accountEntity);
  }
  
  public AccountWeightEntity findLatestWeight(AccountEntity accountEntity) {
    return accountWeightEAO.findLatestWeight(accountEntity);
  }
}
