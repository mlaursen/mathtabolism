/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.AccountWeightEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountWeightEAO extends BaseEAO<AccountWeightEntity> {
  public AccountWeightEAO() {
    super(AccountWeightEntity.class);
  }
  
  public AccountWeightEntity findLatestWeight(AccountEntity accountEntity) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountEntity.getId());
    try {
      return findOneResult(AccountWeightEntity.Q_findLatestWeight, parameters);
    }
    catch (NoResultException e) {
      return createDefaultWeight(accountEntity);
    }
  }
  
  private AccountWeightEntity createWeightForAccount(AccountEntity accountEntity, double weight) {
    AccountWeightEntity accountWeightEntity = new AccountWeightEntity(accountEntity, Calendar.getInstance().getTime());
    accountWeightEntity.setWeight(weight);
    create(accountWeightEntity);
    return accountWeightEntity;
  }
  
  private AccountWeightEntity createDefaultWeight(AccountEntity accountEntity) {
    return createWeightForAccount(accountEntity, 0.0);
  }
  
  /**
   * Attempts to find the {@link AccountWeightEntity} for today and the given account.
   * 
   * @param accountEntity
   *          the {@link AccountEntity} to look up a weight for
   * @return
   */
  public AccountWeightEntity findTodaysWeight(AccountEntity accountEntity) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountEntity.getId());
    parameters.put("today", Calendar.getInstance().getTime());
    
    return findOneResult(AccountWeightEntity.Q_findTodaysWeight, parameters);
  }
  
  public List<AccountWeightEntity> findCurrentAccountWeightWeek(String accountId, DateTime startDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("start_date", startDate.toDate());
    parameters.put("end_date", startDate.plusDays(7).toDate());
    
    return findResultList(AccountWeightEntity.Q_findCurrentAccountWeightWeek, parameters);
  }
}
