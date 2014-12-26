/**
 * 
 */
package com.mathtabolism.converter.account;

import javax.ejb.Stateless;

import com.mathtabolism.converter.BaseEntityModelConverter;
import com.mathtabolism.emcontract.Account;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.model.account.AccountModel;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountConverter extends BaseEntityModelConverter<Account, AccountEntity, AccountModel> {

  public AccountConverter() {
    super(Account.class, AccountEntity.class, AccountModel.class);
  }

}
