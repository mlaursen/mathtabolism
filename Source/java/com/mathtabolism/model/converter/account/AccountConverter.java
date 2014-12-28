/**
 * 
 */
package com.mathtabolism.model.converter.account;

import javax.ejb.Stateless;

import com.mathtabolism.model.converter.BaseEntityModelConverter;
import com.mathtabolism.model.entity.account.Account;
import com.mathtabolism.model.view.account.AccountModel;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountConverter extends BaseEntityModelConverter<com.mathtabolism.model.Account, Account, AccountModel> {

  public AccountConverter() {
    super(com.mathtabolism.model.Account.class, Account.class, AccountModel.class);
  }

}
