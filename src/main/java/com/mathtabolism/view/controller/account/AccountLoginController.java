/**
 * 
 */
package com.mathtabolism.view.controller.account;

import java.io.IOException;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import com.mathtabolism.manager.account.AccountManager;
import com.mathtabolism.util.string.StringUtils;
import com.mathtabolism.util.string.UsernameGenerator;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.CreateAccountModel;
import com.mathtabolism.view.navigation.AccountNav;

/**
 * <p>Controller for handling ONLY account creation or logging in. During the attempted creation process, the flipper
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
public class AccountLoginController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(AccountLoginController.class);
  private static final String LOGIN_ERROR = "account_InvalidCredentials";
  private static final String CREATED_LOGIN_ERROR = "account_LoginErrorAFterCreation";
  
  @Inject
  private AccountManager accountBO;
  
  private CreateAccountModel accountModel;

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
  
  public void createAccount() {
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
        return;
      }
      accountBO.createAccount(accountModel);
      
      String toNext = login(CREATED_LOGIN_ERROR).replace("?faces-redirect=true", ".xhtml").substring(1);
      if(toNext != null) {
        displayInfoMessage("account_AccountCreated");
      }
      
      getContext().getExternalContext().redirect(toNext);
    } catch (EJBException e) {
      displayErrorMessage("account_AccountExists");
    } catch (IOException e) {
      displayErrorMessage(CREATED_LOGIN_ERROR);
    }
  }
  
  public String login() {
    return login(LOGIN_ERROR);
  }
  
  private String login(String error) {
    try {
      HttpServletRequest request = (HttpServletRequest) getContext().getExternalContext().getRequest();
      if(request.getUserPrincipal() != null) {
        request.logout();
      }
      
      request.login(accountModel.getUsername(), accountModel.getPassword());
      AccountModel account = accountBO.findAccountByUsername(accountModel.getUsername());
      if(account.isIncompleteSetup()) {
        return redirect(AccountNav.ACCOUNT_INITIALIZATION);
      } else {
        return redirect(AccountNav.DAILY_INTAKE);
      }
    } catch(ServletException e) {
      displayErrorMessage(error);
      return null;
    }
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
