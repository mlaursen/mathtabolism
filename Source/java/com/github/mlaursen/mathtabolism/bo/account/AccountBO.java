/**
 * 
 */
package com.github.mlaursen.mathtabolism.bo.account;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.github.mlaursen.mathtabolism.constants.AccountRole;
import com.github.mlaursen.mathtabolism.eao.account.AccountEAO;
import com.github.mlaursen.mathtabolism.entity.account.Account;
import com.github.mlaursen.mathtabolism.util.PasswordEncryption;

/**
 * 
 * @author laursenm
 */
@Stateless
public class AccountBO {
	@Inject
	private AccountEAO accountEAO;
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Account findAccountByUsername(String username) {
		return accountEAO.findAccountByUsername(username);
	}
	
	public Account create(Account a) {
		a.setPassword(PasswordEncryption.encrypt(a.getUnhashedPassword()));
		a.setRole(AccountRole.USER);
		accountEAO.create(a);
		return a;
	}
}
