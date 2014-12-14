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
import com.mathtabolism.util.string.UsernameGenerator;

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
  
  private String confirmPassword;
  
  private static final String FRONT_CSS = "flipper-front";
  private static final String BACK_CSS = "flipper-back";
  private String loginCss = FRONT_CSS;
  private String signupCss = BACK_CSS;
  private boolean isUsernameAvailable = true;
  
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
      
      if(StringUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
        displayErrorMessage("account_PasswordMatchError");
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
  
  /**
   * 
   * @return
   */
  public String getConfirmPassword() {
    return confirmPassword;
  }
  
  /**
   * 
   * @param confirmPassword
   */
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  /**
   * Checks if the username is available for a new user.
   * This should only be called from the ajax call.
   */
  public void checkIsUsernameAvailable() {
    isUsernameAvailable = accountBO.isUsernameAvailable(getAccount().getUsername());
  }
  
  /**
   * 
   * @return true if the username is available
   */
  public boolean isUsernameAvailable() {
    return isUsernameAvailable;
  }
  
  public String getRandomUsername() {
    return UsernameGenerator.getRandomUsername();
  }
}
