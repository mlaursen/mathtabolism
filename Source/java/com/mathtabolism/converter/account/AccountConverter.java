/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.EntityModelConverter;
import com.mathtabolism.emcontract.Account;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.model.account.AccountModel;

/**
 * @author mlaursen
 *
 */
public class AccountConverter extends EntityModelConverter<Account, AccountEntity, AccountModel> {

  private AccountConverter() {
    super(Account.class, AccountEntity.class, AccountModel.class);
  }

}
