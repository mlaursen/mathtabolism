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
 * @author laursenm
 */
@Stateless
public class AccountSettingEAO extends BaseEAO<AccountSetting> {
	public AccountSettingEAO() {
		super(AccountSetting.class);
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public AccountSetting findCurrentAccountSetting(Account account) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("account_id", account.getId());
		return findOneResult(AccountSetting.Q_findCurrentAccountSetting, parameters);
	}
}
