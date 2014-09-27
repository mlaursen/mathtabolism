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
	
	/**
	 * @param amount
	 * @param toCalorieMultiplier
	 */
	public Carbohydrate(double amount) {
		super(amount, 4);
	}
	
}
