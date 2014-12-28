/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 * 
 * @author mlaursen
 */
public enum AccountNav implements Navigatable {
  INDEX, ACCOUNT_SETTINGS, DAILY_INTAKE, ACCOUNT_INITIALIZATION, STATISTICS;
  
  @Override
  public String getFolder() {
    return INDEX.equals(this) ? "" : "protected";
  }
}
