/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.entity.GeneratedEntity;

/**
 * 
 * 
 * @author mlaursen
 *
 */
@Entity
@XmlRootElement
@NamedQueries(
    @NamedQuery(
        name = Account.Q_findByUsername,
        query = "SELECT a FROM Account a WHERE a.username = :username"
    )
)
public class Account extends GeneratedEntity {
  public static final String Q_findByUsername = "Account.findByUsername";
  
  @Column(unique = true)
  private String username;
  private String password;
  private String email;
  
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
  
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private List<AccountSetting> allSettings;
  
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private List<AccountWeight> allWeights;
  
  @Transient
  private AccountSetting currentSettings;
  
	public Account() {
	}
	
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  @XmlTransient
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public AccountRole getRole() {
    return role;
  }
  
  public void setRole(AccountRole role) {
    this.role = role;
  }
  
  public Gender getGender() {
    return gender;
  }
  
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  public Date getBirthday() {
    return birthday;
  }
  
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
  public Date getLastLogin() {
    return lastLogin;
  }
  
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }
  
  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }
  
  public Date getActiveSince() {
    return activeSince;
  }
	
	@Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id)
        .append("username", username).append("password", password).append("email", email)
        .append("role", role).append("birthday", birthday).append("lastLogin", lastLogin)
        .append("activeSince", activeSince).toString();
  }

}
