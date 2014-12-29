/**
 * 
 */
package com.mathtabolism.view.model.account;

import com.mathtabolism.dto.AccountDto;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.emconverter.EMConverter;

/**
 * 
 * @author mlaursen
 */
@EMConverter(converter = AccountDto.class, convertTo = Account.class)
public class CreateAccountModel extends AccountModel {

  private String confirmPassword;
  
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  public String getConfirmPassword() {
    return confirmPassword;
  }
}
