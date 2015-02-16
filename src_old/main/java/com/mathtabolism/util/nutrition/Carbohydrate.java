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
public class Carbohydrate extends MacroNutrient {
  
  public Carbohydrate() {
    this(0);
  }
  
  /**
   * @param amount
   * @param toCalorieMultiplier
   */
  public Carbohydrate(double amount) {
    super(amount, 4);
  }
  
}
