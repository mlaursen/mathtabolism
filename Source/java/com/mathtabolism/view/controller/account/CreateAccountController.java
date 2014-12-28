/**
 * 
 */
package com.mathtabolism.view.controller.account;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.util.string.StringUtils;
import com.mathtabolism.util.string.UsernameGenerator;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.CreateAccountModel;
import com.mathtabolism.view.navigation.AccountNav;

/**
 * <p>Controller for handling ONLY account creation. During the attempted creation process, the flipper
 * css will be changed to have the Signup Form starting front facing if some backend process failed. Hopefully
 * the javascript should never allow it to get to that point, but it is there just in case.
 * 
 * <p>Some other things the controller does is check if a new username is available and does some semi-validation
 * on the new account fields. Currently, no validation is done on email
 * 
 * <p>For fun, there is a <i>random</i> username generator to give suggestions for new users
 * 
 * @author mlaursen
 * @see CreateAccountModel
 */
@Named
@RequestScoped
public class CreateAccountController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(CreateAccountController.class);
  private static final String FRONT_CSS = "flipper-front";
  private static final String BACK_CSS = "flipper-back";
  
  @Inject
  private AccountBO accountBO;
  
  private CreateAccountModel accountModel;
  private String loginCss = FRONT_CSS;
  private String signupCss = BACK_CSS;
  private boolean isUsernameAvailable = true;
  private boolean isValidUsernameLength = true;

  /**
   * Lazy load of the account
   * @return the account
   */
  public CreateAccountModel getAccountModel() {
    if(accountModel == null) {
      accountModel = new CreateAccountModel();
    }
    return accountModel;
  }
  
  public String createAccount() {
    try {
      String username = accountModel.getUsername();
      String password = accountModel.getPassword();
      String confPass = accountModel.getConfirmPassword();
      
      boolean isValid = true;
      if(StringUtils.isEmpty(username) || username.length() < 3 || username.length() > 60) {
        displayErrorMessage("account_UsernameError");
        isValid = false;
      }
      
      if(StringUtils.isEmpty(password)) {
        displayErrorMessage("account_PasswordError");
        isValid = false;
      }
      
      if(StringUtils.isEmpty(confPass) || !confPass.equals(password)) {
        displayErrorMessage("account_PasswordMatchError");
        isValid = false;
      }
      
      if(!isValid) {
        setLoginCss(BACK_CSS);
        setSignupCss(FRONT_CSS);
        return null;
      }
      accountBO.createAccount(accountModel);
      HttpServletRequest request = (HttpServletRequest) getContext().getExternalContext().getRequest();
      try {
        if(request.getUserPrincipal() != null) {
          request.logout();
        }
        request.login(username, password);
      } catch(ServletException e) {
        logger.error("Unable to login after creating an account.");
      }
    } catch (EJBException e) {
      displayErrorMessage("account_AccountExists");
      return null;
    }
    displayInfoMessage("account_AccountCreated");
    return redirect(AccountNav.ACCOUNT_INITIALIZATION);
  }
  
  /**
   * Sets the css class for the login side of the flipper. This will be
   * a css class indicating if the login side should start front facing or back facing
   * @param loginCss the login css class
   */
  public void setLoginCss(String loginCss) {
    this.loginCss = loginCss;
  }
  
  /**
   * Gets the css class for the login side of the flipper. This will be
   * a css class indicating if the login side should start front facing or back facing
   * @return the css class
   */
  public String getLoginCss() {
    return loginCss;
  }
  
  /**
   * Sets the css class for the signup side of the flipper. This will be
   * a css class indicating if the signup side should start front facing or back facing
   * @param signupCss the css class
   */
  public void setSignupCss(String signupCss) {
    this.signupCss = signupCss;
  }
  
  /**
   * Gets the css class for the signup side of the flipper. This will be
   * a css class indicating if the signup side should start front facing or back facing
   * @return the css class
   */
  public String getSignupCss() {
    return signupCss;
  }

  
  /**
   * Checks if the username is available for a new user.
   * This should only be called from the ajax call.
   */
  public void checkIsUsernameAvailable() {
    isUsernameAvailable = accountBO.isUsernameAvailable(getAccountModel().getUsername());
    isValidUsernameLength = StringUtils.isBetween(getAccountModel().getUsername(), 3, 60);
  }
  
  /**
   * 
   * @return true if the username length is valid
   */
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
  
  /**
   * Gets a random username
   * @return a random username
   * @see UsernameGenerator
   */
  public String getRandomUsername() {
    return UsernameGenerator.getRandomUsername();
  }
}
