/**
 * 
 */
package com.github.mlaursen.mathtabolism.beans.account;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.mathtabolism.beans.BaseBean;
import com.github.mlaursen.mathtabolism.bo.account.AccountBO;
import com.github.mlaursen.mathtabolism.entity.account.Account;

/**
 * 
 * @author laursenm
 */
@Named
@SessionScoped
public class AccountBean extends BaseBean {
	@Inject
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
	
	/**
	 * 
	 * @param account 
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String createAccount() {
		try {
			account = accountBO.create(account);
		}
		catch(EJBException e) {
			
			return null;
		}
		return "create";
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
