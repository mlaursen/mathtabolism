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
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountWeight;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountWeightEAO extends BaseEAO<AccountWeight> {
  public AccountWeightEAO() {
    super(AccountWeight.class);
  }
  
  public AccountWeight findLatestWeight(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    try {
      return findOneResult(AccountWeight.Q_findLatestWeight, parameters);
    }
    catch (NoResultException e) {
      return createDefaultWeight(account);
    }
  }
  
  private AccountWeight createWeightForAccount(Account account, double weight) {
    AccountWeight accountWeight = new AccountWeight(account, Calendar.getInstance().getTime());
    accountWeight.setWeight(weight);
    create(accountWeight);
    return accountWeight;
  }
  
  private AccountWeight createDefaultWeight(Account account) {
    return createWeightForAccount(account, 0.0);
  }
  
  /**
   * Attempts to find the {@link AccountWeight} for today and the given account.
   * 
   * @param account
   *          the {@link Account} to look up a weight for
   * @return
   */
  public AccountWeight findTodaysWeight(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    parameters.put("today", Calendar.getInstance().getTime());
    
    try {
      return findOneResult(AccountWeight.Q_findTodaysWeight, parameters);
    }
    catch (NoResultException e) {
      AccountWeight latestWeight = findLatestWeight(account);
      
      if(!DateUtils.isSameDay(latestWeight.getWeighInDate(), new Date())) {
        return createWeightForAccount(account, latestWeight.getWeight());
      }
      else {
        return latestWeight;
      }
    }
    
  }
  
  public List<AccountWeight> findCurrentAccountWeightWeek(String accountId, DateTime startDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("start_date", startDate.toDate());
    parameters.put("end_date", startDate.plusDays(7).toDate());
    
    return findResultList(AccountWeight.Q_findCurrentAccountWeightWeek, parameters);
  }
}
