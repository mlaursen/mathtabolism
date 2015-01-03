/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 * 
 * @author mlaursen
 */
public enum AccountNav implements Navigatable {
  INDEX,
  ACCOUNT_SETTINGS,
  DAILY_INTAKE,
  ACCOUNT_INITIALIZATION,
  STATISTICS,
  LOGOUT;
  private static final String EMPTY = "";
  private static final String FOLDER = "protected";
  
  @Override
  public String getFolder() {
    return INDEX.equals(this) ? EMPTY : FOLDER;
  }
}
