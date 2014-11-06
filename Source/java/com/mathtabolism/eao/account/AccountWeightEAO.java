/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.DateUtils;

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
    TypedQuery<AccountWeight> q = em.createNamedQuery(AccountWeight.Q_findLatestWeight, AccountWeight.class);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    
    bindParameters(q, parameters);
    try {
      return q.getSingleResult();
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
    TypedQuery<AccountWeight> q = em.createNamedQuery(AccountWeight.Q_findTodaysWeight, AccountWeight.class);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    parameters.put("today", Calendar.getInstance().getTime());
    
    bindParameters(q, parameters);
    try {
      return q.getSingleResult();
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
}
