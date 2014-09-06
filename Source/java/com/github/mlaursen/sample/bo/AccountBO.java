/**
 * 
 */
package com.github.mlaursen.sample.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.github.mlaursen.sample.eao.AccountEAO;
import com.github.mlaursen.sample.entity.Account;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountBO {
  @EJB
  private AccountEAO userEAO;
  
  public Account findAccountByEmail(String email) {
    return userEAO.findAccountByEmail(email);
  }
}
