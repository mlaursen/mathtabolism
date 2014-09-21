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
public class Protein extends MacroNutrient {
	
	/**
	 * @param amount
	 * @param toCalorieMultiplier
	 */
	public Protein(double amount) {
		super(amount, 4);
	}
	
}
