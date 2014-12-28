/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountSettingEAO extends BaseGeneratedEntityEAO<AccountSetting> {
  public AccountSettingEAO() {
    super(AccountSetting.class);
  }
  
  /**
   * 
   * @param account the account to get the current AccountSetting for
   * @return
   */
  public AccountSetting findCurrentAccountSetting(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    return findOneResult(AccountSetting.Q_findCurrentAccountSetting, parameters);
  }
  
  /**
   * 
   * @param accountId
   * @param date
   * @return
   */
  public AccountSetting findLatestSettingsForDate(String accountId, Date date) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("date", date);
    return findOneResult(AccountSetting.Q_findLatestSettingsForDate, parameters);
  }
  
  /**
   * Finds the latest date changed for an account's account settings
   * @param account the account to search in
   * @return the latest date changed
   */
  public Date findLatestAccountSettingDateByAccount(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    TypedQuery<Date> q = em.createNamedQuery(AccountSetting.Q_findLatestAccountSettingByAccountId, Date.class);
    bindParameters(q, parameters);
    return q.getSingleResult();
  }
}
