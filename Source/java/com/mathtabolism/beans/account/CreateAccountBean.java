/**
 * 
 */
package com.mathtabolism.beans.account;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

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

	private static final String FRONT_CSS = "flipper-front";
	private static final String BACK_CSS  = "flipper-back";
	private String loginCss = FRONT_CSS;
	private String signupCss = BACK_CSS;
	
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
			String username = account.getUsername();
			String password = account.getUnhashedPassword();
			
			boolean isValid = true;
			if(StringUtils.isEmpty(username) || username.length() < 6 || username.length() > 60) {
				displayErrorMessage("account_UsernameError");
				isValid = false;
			}
			
			if(StringUtils.isEmpty(password) || password.length() < 6) {
				displayErrorMessage("account_PasswordError");
				isValid = false;
			}
			
			if(!isValid) {
				setLoginCss(BACK_CSS);
				setSignupCss(FRONT_CSS);
				return null;
			}
			account = accountBO.create(account);
		}
		catch (EJBException e) {
			displayErrorMessage("account_AccountExists");
			return null;
		}
		displayInfoMessage("account_AccountCreated");
		return "create";
	}
	
	/**
	 * 
	 * @return 
	 */
	public String getLoginCss() {
		return loginCss;
	}

	/**
	 * 
	 * @param loginCss 
	 */
	public void setLoginCss(String loginCss) {
		this.loginCss = loginCss;
	}

	/**
	 * 
	 * @return 
	 */
	public String getSignupCss() {
		return signupCss;
	}

	/**
	 * 
	 * @param signupCss 
	 */
	public void setSignupCss(String signupCss) {
		this.signupCss = signupCss;
	}
}
