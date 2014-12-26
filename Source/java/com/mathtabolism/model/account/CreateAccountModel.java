/**
 * 
 */
package com.mathtabolism.model.account;

/**
 * @author mlaursen
 *
 */
public class CreateAccountModel extends AccountModel {

  private String confirmPassword;
  
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  public String getConfirmPassword() {
    return confirmPassword;
  }
}
