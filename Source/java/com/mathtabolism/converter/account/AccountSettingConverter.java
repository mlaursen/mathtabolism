/**
 * 
 */
package com.mathtabolism.converter.account;

import javax.ejb.Stateless;

import com.mathtabolism.converter.BaseEntityModelConverter;
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.model.account.AccountSettingModel;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountSettingConverter extends BaseEntityModelConverter<AccountSetting, AccountSettingEntity, AccountSettingModel> {

  public AccountSettingConverter() {
    super(AccountSetting.class, AccountSettingEntity.class, AccountSettingModel.class);
  }

}
