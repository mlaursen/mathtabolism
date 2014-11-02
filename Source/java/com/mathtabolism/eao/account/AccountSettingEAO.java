/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountSettingEAO extends BaseEAO<AccountSetting> {
	public AccountSettingEAO() {
		super(AccountSetting.class);
	}
	
	/**
	 * 
	 * @param account the account to get the current AccountSetting for
	 * @return
	 */
	public AccountSetting findCurrentAccountSetting(Account account) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("account_id", account.getId());
		return findOneResult(AccountSetting.Q_findCurrentAccountSetting, parameters);
	}
}
