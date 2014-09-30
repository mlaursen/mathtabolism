/**
 * 
 */
package com.mathtabolism.beans.account;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.beans.BaseBean;
import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.entity.account.Account;

/**
 * 
 * @author mlaursen
 */
@Named
@RequestScoped
public class CreateAccountBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	@Inject
	private AccountBO accountBO;
	private Account account;
	
	/**
	 * Lazy create of the account
	 * 
	 * @return the account or a new account if null
	 */
	public Account getAccount() {
		if(account == null) {
			account = new Account();
		}
		return account;
	}
	
	public String createAccount() {
		try {
			account = accountBO.create(account);
		}
		catch (EJBException e) {
			sendErrorMessageToUser("The username already exists.");
			return null;
		}
		sendInfoMessageToUser("Your account has been created! Please log in");
		return "create";
	}
}
