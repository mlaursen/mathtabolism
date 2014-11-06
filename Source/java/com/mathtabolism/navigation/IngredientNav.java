/**
 * 
 */
package com.mathtabolism.navigation;

/**
 * 
 * @author mlaursen
 */
public enum IngredientNav implements Navigatable {
  VIEW_INGREDIENTS;
  
  public String getFolder() {
    return "ingredients";
  }
}
