/**
 * 
 */
package com.mathtabolism.nutrition;

/**
 * 
 * @author mlaursen
 */
public abstract class MacroNutrient extends BaseNutrient {
	
	private int toCalorieMultiplier;
	
	public MacroNutrient() {}
	protected MacroNutrient(double amount, int toCalorieMultiplier) {
		super(amount);
		this.toCalorieMultiplier = toCalorieMultiplier;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getToCalorieMultiplier() {
		return toCalorieMultiplier;
	}
	
	/**
	 * 
	 * @param toCalorieMultiplier
	 */
	public void setToCalorieMultiplier(int toCalorieMultiplier) {
		this.toCalorieMultiplier = toCalorieMultiplier;
	}
	
}
