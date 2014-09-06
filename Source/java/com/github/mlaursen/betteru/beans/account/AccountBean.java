/**
 * 
 */
package com.github.mlaursen.betteru.beans.account;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.betteru.bo.account.AccountBO;
import com.github.mlaursen.betteru.entity.account.Account;

/**
 * 
 * @author laursenm
 */
@Named
@SessionScoped
public class AccountBean implements Serializable {
	@EJB
	private AccountBO accountBO;
	private Account account;
	
	/**
	 * Gets the current account. If the account is null, it attempts to get it from the FacesContext
	 * 
	 * @return the account
	 */
	public Account getAccount() {
		if(account == null) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String username = context.getUserPrincipal().getName();
			account = accountBO.findAccountByUsername(username);
		}
		return account;
	}
	
	public boolean isAccountAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
