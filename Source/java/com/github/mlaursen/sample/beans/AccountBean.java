/**
 * 
 */
package com.github.mlaursen.sample.beans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.github.mlaursen.sample.bo.AccountBO;
import com.github.mlaursen.sample.entity.Account;

/**
 * @author mlaursen
 *
 */
@Named
@SessionScoped
public class AccountBean extends BaseBean {
  @EJB
  private AccountBO accountBO;
  private Account account;
  
  public Account getAccount() {
    if(account == null) {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      String email = context.getUserPrincipal().getName();
      account = accountBO.findAccountByEmail(email);
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
