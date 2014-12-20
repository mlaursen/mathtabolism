/**
 * 
 */
package com.mathtabolism.controller.account;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.navigation.AccountNav;
import com.mathtabolism.util.string.StringUtils;
import com.mathtabolism.util.string.UsernameGenerator;

/**
 * 
 * @author mlaursen
 */
@Named
@RequestScoped
public class CreateAccountController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(CreateAccountController.class);
  
  @Inject
  private AccountBO accountBO;
  private Account account;
  
  private String confirmPassword;
  
  private static final String FRONT_CSS = "flipper-front";
  private static final String BACK_CSS = "flipper-back";
  private String loginCss = FRONT_CSS;
  private String signupCss = BACK_CSS;
  private boolean isUsernameAvailable = true;
  private boolean isValidUsernameLength = true;
  
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
      if(StringUtils.isEmpty(username) || username.length() < 3 || username.length() > 60) {
        displayErrorMessage("account_UsernameError");
        isValid = false;
      }
      
      if(StringUtils.isEmpty(password)) {
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
      HttpServletRequest request = (HttpServletRequest) getContext().getExternalContext().getRequest();
      try {
        if(request.getUserPrincipal() != null) {
          request.logout();
        }
        request.login(username, password);
      } catch(ServletException e) {
        logger.error("Unable to login after creating an account.");
      }
    }
    catch (EJBException e) {
      displayErrorMessage("account_AccountExists");
      return null;
    }
    displayInfoMessage("account_AccountCreated");
    return redirect(AccountNav.ACCOUNT_INITIALIZATION);
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
    isValidUsernameLength = StringUtils.isBetween(getAccount().getUsername(), 3, 60);
  }
  
  public boolean isValidUsernameLength() {
    return isValidUsernameLength;
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
