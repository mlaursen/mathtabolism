package com.mathtabolism.model;

import java.util.Date;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;

/**
 * An interface with the minimum requirements to be an account
 * 
 * @author mlaursen
 *
 */
public interface Account extends GeneratedIdContract {

  /**
   * Set the username
   * @param username the username
   */
  void setUsername(String username);
  
  /**
   * Get the username
   * @return the username
   */
  String getUsername();
  
  /**
   * Set the password. This will either be hashed or unhashed.
   * 
   * @param password the password
   */
  void setPassword(String password);
  
  /**
   * Get the password. This will either be hashed or unhashed.
   * @return the password
   */
  String getPassword();
  
  /**
   * Set the recovery email.
   * @param email the email address
   */
  void setEmail(String email);
  
  /**
   * Get the recovery email.
   * @return the email address
   */
  String getEmail();
  
  /**
   * Set the gender
   * @param gender the gender
   */
  void setGender(Gender gender);
  
  /**
   * Get the gender
   * @return the gender
   */
  Gender getGender();
  
  /**
   * Set if the account is using a birthday to calculate age
   * @param useBirthday an {@link Indicator}
   */
  void setUseBirthday(Indicator useBirthday);
  
  /**
   * Get if the account is using a birthday to calculate age
   * @return the {@link Indicator}
   */
  Indicator getUseBirthday();
  
  /**
   * Set the birthday date
   * @param birthday the date
   */
  void setBirthday(Date birthday);
  
  /**
   * Get the birthday date
   * @return the date
   */
  Date getBirthday();
  
  /**
   * Sets the last login date
   * @param lastLogin the date
   */
  void setLastLogin(Date lastLogin);
  
  /**
   * Gets the last login date
   * @return the date
   */
  Date getLastLogin();
  
  /**
   * Sets the date since the account has been active. This is mostly used
   * to check if a user is a new user and the obvious.. how long they have been
   * tracking dem gainz son.
   * @param activeSince the date
   */
  void setActiveSince(Date activeSince);
  
  /**
   * Gets the date since the account has been active. This is mostly used
   * to check if a user is a new user and the obvious.. how long they have been
   * tracking dem gainz son.
   * @return the date
   */
  Date getActiveSince();
  
  void setRole(AccountRole role);
  
  AccountRole getRole();
}
