package com.mathtabolism.view.model.account;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.dto.AccountDto;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.emconverter.EMConverter;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 */
@EMConverter(converter = AccountDto.class, convertTo = Account.class)
public class AccountModel extends BaseModel implements AccountDto {

  private String username;
  private String email;
  private String password;
  private AccountRole role;
  
  private Gender gender;
  private Date birthday;
  private Date lastLogin;
  private Date activeSince;
  
  private AccountSettingModel currentSettings;
  private AccountWeightModel currentWeight;
  private AccountWeightModel previousWeight;

  /**
   * Gets the current account's settings
   * @return the {@link AccountSettingModel}
   */
  public AccountSettingModel getCurrentSettings() {
    return currentSettings;
  }

  /**
   * Sets the current account's settings
   * @param currentSettings the current {@link AccountSettingModel}
   */
  public void setCurrentSettings(AccountSettingModel currentSettings) {
    this.currentSettings = currentSettings;
  }

  /**
   * Gets the current account's weight for today
   * @return the current weight for today as {@link AccountWeightModel}
   */
  public AccountWeightModel getCurrentWeight() {
    return currentWeight;
  }

  /**
   * Sets the current account's weight for today
   * @param currentWeight the current weight for today
   */
  public void setCurrentWeight(AccountWeightModel currentWeight) {
    this.currentWeight = currentWeight;
  }
  
  /**
   * Sets the previous weight for the account. This is just used
   * as a suggestion for the update weight modal.
   * @param previousWeight the previous weight
   */
  public void setPreviousWeight(AccountWeightModel previousWeight) {
    this.previousWeight = previousWeight;
  }
  
  /**
   * Sets the previous weight for the account. This is just used
   * as a suggestion for the update weight modal.
   * @return the previous weight
   */
  public AccountWeightModel getPreviousWeight() {
    return previousWeight;
  }
  
  @Override
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public void setEmail(String email) {
    this.email = email;
  }
  
  @Override
  public String getEmail() {
    return email;
  }
  
  @Override
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  @Override
  public Gender getGender() {
    return gender;
  }
  
  @Override
  public void setRole(AccountRole role) {
    this.role = role;
  }

  @Override
  public AccountRole getRole() {
    return role;
  }
  
  @Override
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
  @Override
  public Date getBirthday() {
    return birthday;
  }

  @Override
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  @Override
  public Date getLastLogin() {
    return lastLogin;
  }

  @Override
  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  @Override
  public Date getActiveSince() {
    return activeSince;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id)
        .append("username", username).append("password", password).append("email", email)
        .append("birthday", birthday).append("gender", gender).append("currentSettings", currentSettings)
        .append("currentWeight", currentWeight).append("previousWeight", previousWeight).toString();
  }
}
