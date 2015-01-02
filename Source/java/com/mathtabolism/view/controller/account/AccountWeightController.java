/**
 * 
 */
package com.mathtabolism.view.controller.account;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.account.AccountBO;
import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.model.account.AccountModel;
import com.mathtabolism.view.model.account.AccountWeightModel;

/**
 *
 * @author mlaursen
 */
@Named
@RequestScoped
public class AccountWeightController extends BaseController {
  private static final long serialVersionUID = -8545835346680741543L;

  @Inject
  private AccountBO accountBO;
  
  @Inject
  private AccountController accountController;
  
  private AccountModel accountModel;
  
  @PostConstruct
  public void init() {
    accountModel = accountController.getAccountModel();
  }
  
  private List<AccountWeightModel> allWeights = null;
  
  public List<AccountWeightModel> getAllWeights() {
    if(allWeights == null) {
      allWeights = accountBO.findAccountWeightsInRange(accountModel, accountModel.getActiveSince(), new Date());
    }
    return allWeights;
  }
  
  public void updateWeight(int index) {
    AccountWeightModel model = allWeights.get(index);
    accountBO.createOrUpdateWeight(model, accountModel);
  }
  
}
