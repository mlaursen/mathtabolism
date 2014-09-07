/**
 * 
 */
package com.github.mlaursen.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.mlaursen.mathtabolism.constants.AccountRole;
import com.github.mlaursen.mathtabolism.entity.BaseEntity;

/**
 * @author Mikkel Laursen
 *
 */
@NamedQueries(@NamedQuery(name = Account.Q_findByUsername, query = "SELECT a from Account a where a.username=:username"))
@Entity
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
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "active_since")
	private Date activeSince;
	
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
	 * @return the date the account was created
	 */
	public Date getActiveSince() {
		return activeSince;
	}
	
}
