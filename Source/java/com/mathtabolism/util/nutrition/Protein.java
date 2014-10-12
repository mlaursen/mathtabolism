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
public class Protein extends MacroNutrient {
	
	public Protein() {
	}
	
	/**
	 * @param amount
	 * @param toCalorieMultiplier
	 */
	public Protein(double amount) {
		super(amount, 4);
	}
	
}
