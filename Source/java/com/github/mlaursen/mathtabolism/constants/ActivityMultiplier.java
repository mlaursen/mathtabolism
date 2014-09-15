/**
 * 
 */
package com.github.mlaursen.mathtabolism.constants;

/**
 * 
 * @author laursenm
 */
public enum ActivityMultiplier {
	SEDENTARY(1.2),
	LIGHTLY_ACTIVE(1.375),
	MODERATELY_ACTIVE(1.55),
	VERY_ACTIVE(1.725),
	EXTREMELY_ACTIVE(1.9);
	
	private double multiplier;
	private ActivityMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	/**
	 * 
	 * @return the multiplier amount
	 */
	public double getMultiplier() {
		return multiplier;
	}
}
