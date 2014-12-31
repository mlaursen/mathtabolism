/**
 * 
 */
package com.mathtabolism.view.model.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.emconverter.ModelConverter;

/**
 * 
 * @author mlaursen
 */
@ModelConverter(entities = {Account.class})
public class CreateAccountModel extends BaseAccountModel {

  private String confirmPassword;
  
  /**
   * Sets the confirmation password
   * @param confirmPassword the confirmation password
   */
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  /**
   * Gets the confirmation password
   * @return the confirmation password
   */
  public String getConfirmPassword() {
    return confirmPassword;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", accountId).append("username", username)
        .append("email", email).append("password", password).append("confirmPassword", confirmPassword).append("role", role)
        .append("birthday", birthday).append("gender", gender).append("lastLogin", lastLogin).append("activeSince", activeSince)
        .toString();
  }
}
