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
import com.mathtabolism.converter.account.AccountConverter;
import com.mathtabolism.converter.account.AccountSettingConverter;
import com.mathtabolism.converter.account.AccountWeightConverter;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
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
  private DailyIntakeBO dailyIntakeBO;
  @Inject
  private AccountConverter accountConverter;
  @Inject
  private AccountSettingConverter accountSettingConverter;
  @Inject
  private AccountWeightConverter accountWeightConverter;
  
  
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

    AccountModel accountModel = accountConverter.convertEntityToModel(account);
    AccountSettingModel accountSettingModel = accountSettingConverter.convertEntityToModel(currentSettings);
    AccountWeightModel currentWeightModel = accountWeightConverter.convertEntityToModel(currentWeight);
    AccountWeightModel previousWeightModel = accountWeightConverter.convertEntityToModel(previousWeight);
    
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
    
    AccountEntity account = accountConverter.convertModelToEntity(accountModel);
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
    AccountEntity account = accountConverter.convertModelToEntity(accountModel);
    accountEAO.update(account);
    
    AccountSettingModel currentSettingsModel = accountModel.getCurrentSettings();
    currentSettingsModel.setDateChanged(new Date());
    
    AccountSettingEntity currentSettings = accountSettingConverter.convertModelToEntity(currentSettingsModel);
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
    AccountWeightEntity currentWeight = accountWeightConverter.convertModelToEntity(accountModel.getCurrentWeight());
    AccountWeightEntity existingWeight = accountWeightEAO.findById(currentWeight);
    if(existingWeight == null) {
      currentWeight = accountWeightEAO.update(currentWeight);
    } else {
      AccountEntity account = accountConverter.convertModelToEntity(accountModel);
      AccountWeightEntity newWeight = new AccountWeightEntity(account, new Date());
      newWeight.setWeight(currentWeight.getWeight());
      accountWeightEAO.create(newWeight);
      accountModel.setCurrentWeight(accountWeightConverter.convertEntityToModel(newWeight));
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
    AccountEntity account = accountConverter.convertModelToEntity(createAccountModel);
    Date creationDate = Calendar.getInstance().getTime();
    account.setPassword(PasswordEncryption.encrypt(account.getPassword()));
    account.setRole(AccountRole.USER);
    account.setActiveSince(creationDate);
    accountEAO.create(account);
    
    AccountSettingEntity accountSetting = new AccountSettingEntity(account, creationDate);
    accountSettingEAO.create(accountSetting);
  }
  
  
  
  
  
  
  public AccountSettingEntity findLatestSettingsForDate(AccountEntity accountEntity, Date date) {
    return accountSettingEAO.findLatestSettingsForDate(accountEntity.getId(), date);
  }
  
  public List<DailyIntakeEntity> findCurrentDailyIntakeWeekForAccount(AccountEntity accountEntity, AccountSettingEntity currentSettings) {
    return dailyIntakeBO.findCurrentWeek(accountEntity, currentSettings);
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
