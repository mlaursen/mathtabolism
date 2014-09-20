/**
 * 
 */
package com.mathtabolism.bo.account;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.util.PasswordEncryption;

/**
 * 
 * @author laursenm
 */
@Stateless
public class AccountBO {
	@Inject
	private AccountEAO accountEAO;
	@Inject
	private AccountSettingEAO accountSettingEAO;
	
	/**
	 * 
	 * @param username
	 * @return
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
	 * @param account
	 * @return
	 */
	public Account updateLastLogin(Account account) {
		return accountEAO.updateLastLogin(account);
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public Account create(Account account) {
		account.setPassword(PasswordEncryption.encrypt(account.getUnhashedPassword()));
		account.setUnhashedPassword("");
		account.setRole(AccountRole.USER);
		account.setActiveSince(Calendar.getInstance().getTime());
		accountEAO.create(account);
		
		AccountSetting accountSetting = new AccountSetting();
		accountSetting.setAccount(account);
		accountSetting.setRecalculationDay(Weekday.SUNDAY);
		accountSetting.setActivityMultiplier(ActivityMultiplier.SEDENTARY);
		accountSetting.setTdeeFormula(TDEEFormula.HARRIS_BENEDICT);
		accountSetting.setDateChanged(Calendar.getInstance().getTime());
		accountSettingEAO.create(accountSetting);
		return account;
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public Account updateAccount(Account account) {
		accountEAO.update(account);
		return account;
	}
}
