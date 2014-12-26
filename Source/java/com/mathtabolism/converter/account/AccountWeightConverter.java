/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.BaseEntityModelConverter;
import com.mathtabolism.emcontract.AccountWeight;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.model.account.AccountWeightModel;

/**
 * @author mlaursen
 *
 */
public class AccountWeightConverter extends BaseEntityModelConverter<AccountWeight, AccountWeightEntity, AccountWeightModel> {
  
  public AccountWeightConverter() {
    super(AccountWeight.class, AccountWeightEntity.class, AccountWeightModel.class);
  }

}
