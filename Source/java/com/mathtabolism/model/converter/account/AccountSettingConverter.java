/**
 * 
 */
package com.mathtabolism.model.converter.account;

import javax.ejb.Stateless;

import com.mathtabolism.model.converter.BaseEntityModelConverter;
import com.mathtabolism.model.entity.account.AccountSetting;
import com.mathtabolism.model.view.account.AccountSettingModel;

/**
 * @author mlaursen
 *
 */
@Stateless
public class AccountSettingConverter extends BaseEntityModelConverter<com.mathtabolism.model.AccountSetting, AccountSetting, AccountSettingModel> {

  public AccountSettingConverter() {
    super(com.mathtabolism.model.AccountSetting.class, AccountSetting.class, AccountSettingModel.class);
  }

}
