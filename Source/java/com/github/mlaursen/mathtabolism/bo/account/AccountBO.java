/**
 * 
 */
package com.github.mlaursen.mathtabolism.bo.account;

import java.util.Calendar;

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
	
	public Account updateLastLogin(Account account) {
		return accountEAO.updateLastLogin(account);
	}
	
	public Account create(Account account) {
		account.setPassword(PasswordEncryption.encrypt(account.getUnhashedPassword()));
		account.setUnhashedPassword("");
		account.setRole(AccountRole.USER);
		account.setActiveSince(Calendar.getInstance().getTime());
		accountEAO.create(account);
		return account;
	}
}
