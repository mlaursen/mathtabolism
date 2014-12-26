/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.BaseEntityModelConverter;
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.model.account.AccountSettingModel;

/**
 * @author mlaursen
 *
 */
public class AccountSettingConverter extends BaseEntityModelConverter<AccountSetting, AccountSettingEntity, AccountSettingModel> {

  public AccountSettingConverter() {
    super(AccountSetting.class, AccountSettingEntity.class, AccountSettingModel.class);
  }

}
