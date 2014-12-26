/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.account.AccountEntity;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountEAO extends BaseGeneratedEntityEAO<AccountEntity> {
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
}
