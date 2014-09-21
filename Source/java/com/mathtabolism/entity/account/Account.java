/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.entity.BaseEntity;

/**
 * @author mlaursen
 *
 */
@Entity
@NamedQueries(@NamedQuery(name = Account.Q_findByUsername, query = "SELECT a FROM Account a WHERE a.username = :username"))
public class Account extends BaseEntity {
	
	public static final String Q_findByUsername = "Account.findByUsername";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
	@SequenceGenerator(name = "account_id_gen", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
	private Long id;
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AccountRole role;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Temporal(TemporalType.DATE)
	private Date lastLogin;
	
	@Temporal(TemporalType.DATE)
	private Date activeSince;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.account")
	private List<AccountSetting> accountSettings;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.account")
	private List<AccountWeight> accountWeights;
	
	@Transient
	private String unhashedPassword;
	@Transient
	private AccountSetting currentSettings;
	
	public Account() {
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 *          the Account id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param username
	 *          the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password
	 *          the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return the {@link AccountRole}
	 */
	public AccountRole getRole() {
		return role;
	}
	
	/**
	 * 
	 * @param role
	 *          the new {@link AccountRole}
	 */
	public void setRole(AccountRole role) {
		this.role = role;
	}
	
	/**
	 * 
	 * @return the birthday date
	 */
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * 
	 * @param birthday
	 *          the new birthday date
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * 
	 * @return the last login date
	 */
	public Date getLastLogin() {
		return lastLogin;
	}
	
	/**
	 * 
	 * @param lastLogin
	 *          the new last login date
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	/**
	 * 
	 * @param activeSince
	 */
	public void setActiveSince(Date activeSince) {
		this.activeSince = activeSince;
	}
	
	/**
	 * 
	 * @return the date the account was created
	 */
	public Date getActiveSince() {
		return activeSince;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUnhashedPassword() {
		return unhashedPassword;
	}
	
	/**
	 * 
	 * @param unhashedPassword
	 */
	public void setUnhashedPassword(String unhashedPassword) {
		this.unhashedPassword = unhashedPassword;
	}
	
	/**
	 * 
	 * @param accountSettings
	 */
	public void setAccountSettings(List<AccountSetting> accountSettings) {
		this.accountSettings = accountSettings;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AccountSetting> getAccountSettings() {
		return accountSettings;
	}
	
	/**
	 * 
	 * @return
	 */
	public AccountSetting getCurrentSettings() {
		return currentSettings;
	}
	
	/**
	 * 
	 * @param currentSettings
	 */
	public void setCurrentSettings(AccountSetting currentSettings) {
		this.currentSettings = currentSettings;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AccountWeight> getAccountWeights() {
		return accountWeights;
	}
	
	/**
	 * 
	 * @param accountWeights
	 */
	public void setAccountWeights(List<AccountWeight> accountWeights) {
		this.accountWeights = accountWeights;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id).append("username", username)
				.append("role", role).append("birthday", birthday).append("lastLogin", lastLogin)
				.append("activeSince", activeSince).append("currentSettings", currentSettings).toString();
	}
	
}
