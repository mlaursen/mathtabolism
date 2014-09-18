/**
 * 
 */
package com.github.mlaursen.mathtabolism.constants;

/**
 * 
 * 
 * @author laursenm
 */
public enum Gender {
	MALE, FEMALE;
	
	/**
	 * 
	 * @param gender
	 * @return
	 */
	public static boolean isMale(Gender gender) {
		return MALE.equals(gender);
	}
}
