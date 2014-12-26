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
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountSettingEAO extends BaseGeneratedEntityEAO<AccountSettingEntity> {
  public AccountSettingEAO() {
    super(AccountSettingEntity.class);
  }
  
  /**
   * 
   * @param account the account to get the current AccountSetting for
   * @return
   */
  public AccountSettingEntity findCurrentAccountSetting(AccountEntity accountEntity) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountEntity.getId());
    return findOneResult(AccountSettingEntity.Q_findCurrentAccountSetting, parameters);
  }
  
  /**
   * 
   * @param accountId
   * @param date
   * @return
   */
  public AccountSettingEntity findLatestSettingsForDate(String accountId, Date date) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("date", date);
    return findOneResult(AccountSettingEntity.Q_findLatestSettingsForDate, parameters);
  }
  
  /**
   * Finds the latest date changed for an account's account settings
   * @param account the account to search in
   * @return the latest date changed
   */
  public Date findLatestAccountSettingDateByAccount(AccountEntity account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    TypedQuery<Date> q = em.createNamedQuery(AccountSettingEntity.Q_findLatestAccountSettingByAccountId, Date.class);
    bindParameters(q, parameters);
    return q.getSingleResult();
  }
}
