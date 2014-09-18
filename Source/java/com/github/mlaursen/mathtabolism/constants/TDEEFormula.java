/**
 * 
 */
package com.github.mlaursen.mathtabolism.constants;

import com.github.mlaursen.mathtabolism.nutrition.Calorie;

/**
 * 
 * @author laursenm
 */
public enum TDEEFormula {
	/**
	 * <p>
	 * An antiquated method which tends to overstate TDEE by 5%. The results tend to be more effective for obese.<br />
	 * Men<br />
	 * 66.5 + (13.75 X weight in kg) + (5.003 X height in cm) – (6.775 X age in years)
	 * </p>
	 * <p>
	 * Women<br />
	 * 655.1 + (9.563 X weight in kg) + (1.85 X height in cm) – (4.676 X age in years)
	 * </p>
	 */
	HARRIS_BENEDICT,
	/**
	 * <p>
	 * The Mifflin-St Jeor formula is touted by that American Dietetic Association as the most accurate method for TDEE
	 * Calculation.
	 * <p>
	 * This formula bases protein needs off of total mass and is best suited for athletes or those with lower body fat
	 * levels. Overweight individuals should not use this formula as it will give them too much protein and strip them of
	 * carbohydrates in the final calcualtions.
	 * <p>
	 * <strong>How to calculate for Men</strong><br />
	 * 10 x weight (kg) + 6.25 x height (cm) – 5 x age (y) + 5<br />
	 * <p>
	 * <strong>How to calculate for Women</strong><br />
	 * 10 x weight (kg) + 6.25 x height (cm) – 5 x age (y) – 161
	 */
	MIFFLIN_ST_JOER;
	
	/**
	 * 
	 * @param weightInKG
	 * @param heightInCM
	 * @param age
	 * @param gender
	 * @return
	 */
	public Calorie calculate(double weightInKG, double heightInCM, int age, Gender gender) {
		boolean isMale = Gender.isMale(gender);
		double padding, weightMultiplier, heightMultiplier, ageMultiplier;
		double amount = 0;
		switch(this) {
			case HARRIS_BENEDICT:
				padding = isMale ? 66.5 : 655.1;
				weightMultiplier = isMale ? 13.75 : 9.563;
				heightMultiplier = isMale ? 5.003 : 1.85;
				ageMultiplier = isMale ? 6.775 : 4.676;
				amount = padding + (weightMultiplier * weightInKG) + (heightMultiplier * heightInCM) - (ageMultiplier * age);
				break;
			case MIFFLIN_ST_JOER:
				padding = isMale ? 5 : -161;
				weightMultiplier = 10;
				heightMultiplier = 6.25;
				ageMultiplier = 5;
				amount = (weightMultiplier * weightInKG) + (heightMultiplier * heightInCM) - (ageMultiplier * age) + padding;
				break;
		}
		return new Calorie(amount);
	}
}
