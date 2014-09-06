/**
 * 
 */
package com.github.mlaursen.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author mlaursen
 *
 */
@NamedQueries(@NamedQuery(name=Account.Q_findByEmail, query="SELECT a FROM Account a WHERE a.email = :email"))
@Entity
public class Account extends BaseEntity {
  
  public static final String Q_findByEmail = "Account.findByEmail";
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  private String email;
  private String password;
  private String name;
  private String role;
  /**
   * @return the id
   */
  public long getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }
  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Account) {
      Account u = (Account) obj;
      return email.equals(u.email);
    }
    return false;
  }
}
