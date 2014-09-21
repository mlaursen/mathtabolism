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
public class Carbohydrate extends MacroNutrient {
	
	public Carbohydrate() {}
	/**
	 * @param amount
	 * @param toCalorieMultiplier
	 */
	public Carbohydrate(double amount) {
		super(amount, 4);
	}
	
}
