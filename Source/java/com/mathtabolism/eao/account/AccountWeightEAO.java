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
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountWeight;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountWeightEAO extends BaseGeneratedEntityEAO<AccountWeight> {
  public AccountWeightEAO() {
    super(AccountWeight.class);
  }
  
  /**
   * Attempts to find a single account weight by a given search date
   * @param accountId the account id
   * @param date the search date
   * @return the account weight or null
   */
  public AccountWeight findAccountWeightByDate(String accountId, Date date) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("weigh_in_date", date);
    return findOneResult(AccountWeight.Q_findAccountWeightByDate, parameters);
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
   * @param account the {@link Account} to look up a weight for
   * @return
   */
  public AccountWeight findTodaysWeight(Account account) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    parameters.put("today", new Date());
    
    return findOneResult(AccountWeight.Q_findTodaysWeight, parameters);
  }
  
  public List<AccountWeight> findCurrentAccountWeightWeek(String accountId, DateTime startDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("start_date", startDate.toDate());
    parameters.put("end_date", startDate.plusDays(7).toDate());
    
    return findResultList(AccountWeight.Q_findCurrentAccountWeightWeek, parameters);
  }
  
  public List<AccountWeight> findAccountWeightsInRange(String accountId, Date startDate, Date endDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountId);
    parameters.put("start_date", startDate);
    parameters.put("end_date", endDate);
    
    return findResultList(AccountWeight.Q_findAccountWeightsInRange, parameters);
  }
}
