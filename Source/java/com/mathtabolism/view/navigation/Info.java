/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 *
 * @author mlaursen
 */
public enum Info implements Navigatable {
  INFO;
  
  private static final String FOLDER = "";

  @Override
  public String getFolder() {
    return FOLDER;
  }

}
