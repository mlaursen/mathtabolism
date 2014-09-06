/**
 * 
 */
package com.github.mlaursen.sample.eao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.github.mlaursen.sample.entity.Account;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountEAO extends BaseEAO<Account> {
  public AccountEAO() {
    super(Account.class);
  }
  
  public Account findAccountByEmail(String email) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("email", email);
    
    return findOneResult(Account.Q_findByEmail, parameters);
  }
}
