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
public class Calorie extends BaseNutrient {
	
	/**
	 * @param amount
	 */
	public Calorie(double amount) {
		super(amount);
	}
	
}
