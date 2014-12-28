/**
 * 
 */
package com.mathtabolism.model.converter.account;

import javax.ejb.Stateless;

import com.mathtabolism.model.converter.BaseEntityModelConverter;
import com.mathtabolism.model.entity.account.AccountWeight;
import com.mathtabolism.model.view.account.AccountWeightModel;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountWeightConverter extends BaseEntityModelConverter<com.mathtabolism.model.AccountWeight, AccountWeight, AccountWeightModel> {
  
  public AccountWeightConverter() {
    super(com.mathtabolism.model.AccountWeight.class, AccountWeight.class, AccountWeightModel.class);
  }

}
