/**
 * 
 */
package com.mathtabolism.nutrition;

import javax.persistence.Embeddable;

/**
 * 
 * @author mlaursen
 */
@Embeddable
public class Fat extends MacroNutrient {
	
	/**
	 * @param amount
	 * @param toCalorieMultiplier
	 */
	public Fat(double amount) {
		super(amount, 9);
	}
	
}
