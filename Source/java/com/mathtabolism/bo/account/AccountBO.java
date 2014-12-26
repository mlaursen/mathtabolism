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
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;
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
   *          the account to create
   * @return an Account with a generated primary key and default {@link AccountSettingEntity}
   */
  public AccountEntity create(AccountEntity accountEntity) {
    Date creationDate = Calendar.getInstance().getTime();
    accountEntity.setPassword(PasswordEncryption.encrypt(accountEntity.getUnhashedPassword()));
    accountEntity.setUnhashedPassword("");
    accountEntity.setRole(AccountRole.USER);
    accountEntity.setActiveSince(creationDate);
    accountEAO.create(accountEntity);
    
    AccountSettingEntity accountSettingEntity = new AccountSettingEntity(accountEntity, creationDate);
    accountSettingEAO.create(accountSettingEntity);
    logger.debug("Account created: " + accountEntity);
    return accountEntity;
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
  
  public boolean isUsernameAvailable(String username) {
    return StringUtils.isBlank(username) || accountEAO.findAccountByUsername(username) == null;
  }
}
