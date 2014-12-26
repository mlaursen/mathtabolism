/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.EntityModelConverter;
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.entity.account.AccountSettingEntity;
import com.mathtabolism.model.account.AccountSettingModel;

/**
 * @author mlaursen
 *
 */
public class AccountSettingConverter extends EntityModelConverter<AccountSetting, AccountSettingEntity, AccountSettingModel> {

  private AccountSettingConverter() {
    super(AccountSetting.class, AccountSettingEntity.class, AccountSettingModel.class);
  }

}
