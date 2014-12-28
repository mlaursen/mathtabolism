/**
 * 
 */
package com.mathtabolism.model.entity.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.model.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries(
    @NamedQuery(
        name = Account.Q_findByUsername,
        query = "SELECT a FROM Account a WHERE a.username = :username"
    )
)
public class Account extends BaseGeneratedEntity implements com.mathtabolism.model.Account {
  
  public static final String Q_findByUsername = "Account.findByUsername";
  
  @Column(unique = true)
  private String username;
  private String password;
  
  @Enumerated(EnumType.STRING)
  private AccountRole role;
  
  @Enumerated(EnumType.STRING)
  private Gender gender;
  
  @Temporal(TemporalType.DATE)
  private Date birthday;
  
  @Temporal(TemporalType.DATE)
  private Date lastLogin;
  
  @Temporal(TemporalType.DATE)
  private Date activeSince;
  
  @Enumerated(EnumType.ORDINAL)
  private Indicator useBirthday;
  
  private String email;
  
  public Account() {
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public AccountRole getRole() {
    return role;
  }
  
  @Override
  public void setRole(AccountRole role) {
    this.role = role;
  }
  
  @Override
  public Gender getGender() {
    return gender;
  }
  
  @Override
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  @Override
  public Date getBirthday() {
    return birthday;
  }
  
  @Override
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
  @Override
  public Date getLastLogin() {
    return lastLogin;
  }
  
  @Override
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
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
  public Indicator getUseBirthday() {
    return useBirthday;
  }

  @Override
  public void setUseBirthday(Indicator useBirthday) {
    this.useBirthday = useBirthday;
  }
  
  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id).append("username", username)
        .append("password", password).append("role", role)
        .append("birthday", birthday).append("lastLogin", lastLogin).append("activeSince", activeSince)
        .append("email", email).append("useBirthday", useBirthday).toString();
  }
  
}
