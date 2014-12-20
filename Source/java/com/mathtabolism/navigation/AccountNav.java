/**
 * 
 */
package com.mathtabolism.navigation;

/**
 * 
 * @author mlaursen
 */
public enum AccountNav implements Navigatable {
  LOGIN, ACCOUNT_SETTINGS, DAILY_INTAKE, ACCOUNT_INITIALIZATION;
  
  @Override
  public String getFolder() {
    return LOGIN.equals(this) ? "" : "protected";
  }
}
