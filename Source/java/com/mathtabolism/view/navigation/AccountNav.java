/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 * 
 * @author mlaursen
 */
public enum AccountNav implements Navigatable {
  SETTINGS_AND_WEIGHT,
  DAILY_INTAKE,
  ACCOUNT_INITIALIZATION,
  STATISTICS;
  private static final String FOLDER = "protected";
  
  @Override
  public String getFolder() {
    return FOLDER;
  }
}
