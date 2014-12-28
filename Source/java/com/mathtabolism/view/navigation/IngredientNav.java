/**
 * 
 */
package com.mathtabolism.view.navigation;

/**
 * 
 * @author mlaursen
 */
public enum IngredientNav implements Navigatable {
  VIEW_INGREDIENTS;
  
  @Override
  public String getFolder() {
    return "ingredients";
  }
}
