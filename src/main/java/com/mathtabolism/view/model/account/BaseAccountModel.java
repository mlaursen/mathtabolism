/**
 * 
 */
package com.mathtabolism.view.model.account;

import java.util.Date;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.dto.AccountDto;
import com.mathtabolism.view.model.BaseModel;

/**
 *
 * @author mlaursen
 */
public abstract class BaseAccountModel extends BaseModel implements AccountDto {
  protected Long accountId;
  
  protected String username;
  protected String email;
  protected String password;
  protected AccountRole role;
  protected Gender gender;
  protected Date birthday;
  protected Date lastLogin;
  protected Date activeSince;

  /**
   * Sets the account id for the Model
   * @param accountId the account id
   */
  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }
  
  /**
   * Gets the account id for the Model
   * @return the account id
   */
  public Long getAccountId() {
    return accountId;
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
}
