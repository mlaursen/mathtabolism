/**
 * 
 */
package com.mathtabolism.view.controller.account;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.AccountModel;

/**
 * 
 * @author mlaursen
 */
@Named
@SessionScoped
public class AccountController extends BaseController {
  private static final long serialVersionUID = 5069047046599920651L;
  
  @Inject
  private AccountBO accountBO;
  
  private AccountModel accountModel;
  
  /**
   * Creates or updates the current weight from the account model
   */
  public void createOrUpdateCurrentWeight() {
    accountBO.createOrUpdateWeight(accountModel);
    displayInfoMessage("account_UpdatedWeight");
  }
  
  /**
   * Lazily loads the AccountModel.
   * <p>If the account model was null, the user has just logged in. The username is searched 
   * in the external context and then an account is attempted to be found. (It should be since the
   * jaas authentication allowed us to get here).
   * 
   * @return the account model
   */
  public AccountModel getAccountModel() {
    if(accountModel == null) {
      String username = getContext().getExternalContext().getUserPrincipal().getName();
      accountModel = accountBO.findAccountByUsername(username);
      accountModel = accountBO.updateLastLogin(accountModel);
    }
    return accountModel;
  }
  
  public void updateAccountModel() {
    accountModel = accountBO.findAccountById(accountModel.getAccountId());
  }
  
  public String getDateFormat() {
    UnitSystem unitSystem = accountModel.getDefaultedUnitSystem();
    switch(unitSystem) {
      case METRIC:
        return "EEEE, dd MMMM YYYY";
      default:
        return "EEEE, MMMM dd, YYYY";
    }
  }
  
  public boolean isNewCurrentWeight() {
    return isLoggedIn() && getAccountModel().isIncompleteSetup() && !getAccountModel().isTodayWeightSet();
  }
}
