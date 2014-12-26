/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.EntityModelConverter;
import com.mathtabolism.emcontract.AccountWeight;
import com.mathtabolism.entity.account.AccountWeightEntity;
import com.mathtabolism.model.account.AccountWeightModel;

/**
 * @author mlaursen
 *
 */
public class AccountWeightConverter extends EntityModelConverter<AccountWeight, AccountWeightEntity, AccountWeightModel> {
  
  private AccountWeightConverter() {
    super(AccountWeight.class, AccountWeightEntity.class, AccountWeightModel.class);
  }

}
