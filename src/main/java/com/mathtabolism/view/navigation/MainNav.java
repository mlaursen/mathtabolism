/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 *
 * @author mlaursen
 */
public enum MainNav implements Navigatable {
  INDEX;
  
  private static final String FOLDER = "";

  @Override
  public String getFolder() {
    return FOLDER;
  }

}
