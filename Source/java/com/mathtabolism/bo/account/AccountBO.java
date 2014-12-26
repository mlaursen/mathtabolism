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
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
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
  
  /**
   * 
   * @param username
   *          the username to search for
   * @return an {@link AccountEntity}
   */
  public AccountEntity findAccountByUsername(String username) {
    return accountEAO.findAccountByUsername(username);
  }
  
  public AccountSettingEntity findAccountSettingsForAccount(AccountEntity accountEntity) {
    return accountSettingEAO.findCurrentAccountSetting(accountEntity);
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
  
  /**
   * 
   * @param accountEntity
   *          the account to update
   * @return an account with an updated last login date
   */
  public AccountEntity updateLastLogin(AccountEntity accountEntity) {
    return accountEAO.updateLastLogin(accountEntity);
  }
  
  /**
   * 
   * @param accountEntity
   * @return
   */
  public AccountEntity updateSettings(AccountEntity accountEntity, AccountSettingEntity currentSettings) {
    accountEAO.update(accountEntity);
    currentSettings.setDateChanged(Calendar.getInstance().getTime());
    AccountSettingEntity currentSettingsDB = accountSettingEAO.findCurrentAccountSetting(accountEntity);
    if(DateUtils.isSameDate(currentSettings.getDateChanged(), currentSettingsDB.getDateChanged())) {
      accountSettingEAO.update(currentSettings);
    }
    else {
      accountSettingEAO.create(currentSettings);
    }
    return accountEntity;
  }
  
  public AccountWeightEntity createOrUpdateWeight(AccountWeightEntity weight) {
    Date d = Calendar.getInstance().getTime();
    if(DateUtils.isSameDate(d, weight.getWeighInDate())) {
      return accountWeightEAO.update(weight);
    } else {
      AccountWeightEntity currentWeight = new AccountWeightEntity();
      currentWeight.setWeighInDate(d);
      currentWeight.setWeight(weight.getWeight());
      accountWeightEAO.create(currentWeight);
      return currentWeight;
    }
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
}
