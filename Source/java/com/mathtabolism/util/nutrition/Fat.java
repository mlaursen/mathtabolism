/**
 * 
 */
package com.mathtabolism.util.nutrition;

import javax.persistence.Embeddable;

/**
 * 
 * @author mlaursen
 */
@Embeddable
public class Fat extends MacroNutrient {
  
  public Fat() {
    this(0);
  }
  
  /**
   * @param amount
   * @param toCalorieMultiplier
   */
  public Fat(double amount) {
    super(amount, 9);
  }
  
}
