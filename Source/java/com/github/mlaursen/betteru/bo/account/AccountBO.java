/**
 * 
 */
package com.github.mlaursen.betteru.bo.account;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.github.mlaursen.betteru.eao.account.AccountEAO;
import com.github.mlaursen.betteru.entity.account.Account;

/**
 * 
 * @author laursenm
 */
@Stateless
public class AccountBO {
	@EJB
	private AccountEAO accountEAO;
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Account findAccountByUsername(String username) {
		return accountEAO.findAccountByUsername(username);
	}
}
