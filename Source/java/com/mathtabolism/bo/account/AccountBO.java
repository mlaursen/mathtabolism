/**
 * 
 */
package com.mathtabolism.bo.account;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.util.PasswordEncryption;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountBO {
	private static Logger logger = Logger.getLogger(AccountBO.class);
	
	@Inject
	private AccountEAO accountEAO;
	@Inject
	private AccountSettingEAO accountSettingEAO;
	
	/**
	 * 
	 * @param username the username to search for
	 * @return an {@link Account} with a current {@link AccountSetting} or null
	 */
	public Account findAccountByUsername(String username) {
		Account account = accountEAO.findAccountByUsername(username);
		if(account.getAccountSettings().size() == 1) {
			account.setCurrentSettings(account.getAccountSettings().get(0));
		}
		else {
			account.setCurrentSettings(accountSettingEAO.findCurrentAccountSetting(account));
		}
		return account;
	}
	
	/**
	 * 
	 * @param account the account to update
	 * @return an account with an updated last login date
	 */
	public Account updateLastLogin(Account account) {
		return accountEAO.updateLastLogin(account);
	}
	
	/**
	 * 
	 * @param account the account to create
	 * @return an Account with a generated primary key and default {@link AccountSetting}
	 */
	public Account create(Account account) {
		Date creationDate = Calendar.getInstance().getTime();
		account.setPassword(PasswordEncryption.encrypt(account.getUnhashedPassword()));
		account.setUnhashedPassword("");
		account.setRole(AccountRole.USER);
		account.setActiveSince(creationDate);
		accountEAO.create(account);
		
		AccountSetting accountSetting = new AccountSetting(account, creationDate);
		accountSetting.setDefaults();
		accountSettingEAO.create(accountSetting);
		logger.debug("Account created: " + account);
		return account;
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public Account update(Account account) {
		accountEAO.update(account);
		return account;
	}
}
