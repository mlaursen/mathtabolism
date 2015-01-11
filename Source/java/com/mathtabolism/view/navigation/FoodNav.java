/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 * 
 * @author mlaursen
 */
public enum FoodNav implements Navigatable {
  FOOD,
  MEALS;
  
  private static final String FOLDER = "food";
  
  @Override
  public String getFolder() {
    return FOLDER;
  }
}
