/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountSettingEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountSettingEAO extends BaseEAO<AccountSettingEntity> {
  public AccountSettingEAO() {
    super(AccountSettingEntity.class);
  }
  
  /**
   * 
   * @param accountEntity
   *          the account to get the current AccountSetting for
   * @return
   */
  public AccountSettingEntity findCurrentAccountSetting(AccountEntity accountEntity) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountEntity.getId());
    return findOneResult(AccountSettingEntity.Q_findCurrentAccountSetting, parameters);
  }
  
  public AccountSettingEntity findLatestSettingsForDate(String accountId, Date date) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("date", date);
    return findOneResult(AccountSettingEntity.Q_findLatestSettingsForDate, parameters);
  }
}
