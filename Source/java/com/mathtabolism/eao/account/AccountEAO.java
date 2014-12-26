/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.AccountEntity;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountEAO extends BaseEAO<AccountEntity> {
  public AccountEAO() {
    super(AccountEntity.class);
  }
  
  /**
   * 
   * @param username
   *          the username to search by
   * @return an {@link AccountEntity} or null
   */
  public AccountEntity findAccountByUsername(String username) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("username", username);
    
    return findOneResult(AccountEntity.Q_findByUsername, parameters);
  }
  
  /**
   * Updates the last logic date for an account to the current time
   * 
   * @param a
   *          the account to update
   * @return an Account with the last login date updated
   */
  public AccountEntity updateLastLogin(AccountEntity a) {
    a.setLastLogin(Calendar.getInstance().getTime());
    update(a);
    return a;
  }
}
