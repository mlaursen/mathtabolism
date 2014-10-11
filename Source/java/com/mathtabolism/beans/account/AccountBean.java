/**
 * 
 */
package com.mathtabolism.beans.account;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.mathtabolism.beans.BaseBean;
import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.entity.account.Account;

/**
 * 
 * @author mlaursen
 */
@Named
@SessionScoped
public class AccountBean extends BaseBean {
	private static final long serialVersionUID = 5069047046599920651L;
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
			if(account != null) {
				account = accountBO.updateLastLogin(account);
			}
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
	
	public boolean isAccountAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}
	
	public SelectItem[] getWeekdays() {
		return convertEnumToSelectItems(Weekday.values());
	}
	
	public SelectItem[] getActivityMultipliers() {
		return convertEnumToSelectItems(ActivityMultiplier.values());
	}
	
	public SelectItem[] getFormulas() {
		return convertEnumToSelectItems(TDEEFormula.values());
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	public String updateSettings() {
		accountBO.update(account);
		displayInfoMessage("account_UpdatedSettings");
		return "update";
	}
	
}
