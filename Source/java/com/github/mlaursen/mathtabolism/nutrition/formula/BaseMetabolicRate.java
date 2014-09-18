/**
 * 
 */
package com.github.mlaursen.mathtabolism.nutrition.formula;

import com.github.mlaursen.mathtabolism.constants.Gender;
import com.github.mlaursen.mathtabolism.constants.MeasuringUnit;
import com.github.mlaursen.mathtabolism.nutrition.Calorie;
import com.github.mlaursen.mathtabolism.util.Pair;

/**
 * This is a class for generating the Base Metabolic Rate (BMR) for a Person.
 * The BMR is a formula that uses the variables <tt>height</tt>, <tt>weight</tt>,
 * <tt>age</tt>, and <tt>gender</tt> to calculate the daily calorie needs. This formula
 * will be accurate in all but the very muscular and the very fat.
 * 
 * @author laursenm
 * @see TotalDailyEnergyExpenditure
 */
public class BaseMetabolicRate {
	private static final int FEMALE_PADDING = 655;
	private static final int MALE_PADDING = 66;
	private static final double FEMALE_AGE_MULTIPLIER = 4.7;
	private static final double MALE_AGE_MULTIPLIER = 6.8;
	
	// Imperial then Metric
	private static final Pair<Double> MALE_WEIGHT_MULTIPLIERS = new Pair<>(6.23, 13.7);
	private static final Pair<Double> MALE_HEIGHT_MULTIPLIERS = new Pair<>(12.7, 5.0);
	private static final Pair<Double> FEMALE_WEIGHT_MULTIPLIERS = new Pair<>(4.35, 9.6);
	private static final Pair<Double> FEMALE_HEIGHT_MULTIPLIERS = new Pair<>(1.8, 4.7);
	
	/**
	 * Calculates the BRM
	 * 
	 * @param weight the person's weight
	 * @param height the person's height
	 * @param age the person's age
	 * @param gender the person's {@link Gender}
	 * @param unit the person's {@link MeasuringUnit}
	 * @return the number of {@link Calorie} for a person's BMR
	 */
	public static Calorie calculateBMR(double weight, double height, int age, Gender gender, MeasuringUnit unit) {
		double ageMultiplier;
		int padding;
		Pair<Double> weightMultipliers, heightMultipliers;
		if(Gender.isMale(gender)) {
			padding = MALE_PADDING;
			ageMultiplier = MALE_AGE_MULTIPLIER;
			weightMultipliers = MALE_WEIGHT_MULTIPLIERS;
			heightMultipliers = MALE_HEIGHT_MULTIPLIERS;
		} else {
			padding = FEMALE_PADDING;
			ageMultiplier = FEMALE_AGE_MULTIPLIER;
			weightMultipliers = FEMALE_WEIGHT_MULTIPLIERS;
			heightMultipliers = FEMALE_HEIGHT_MULTIPLIERS;
		}
		
		double weightMultiplier, heightMultiplier;
		if(MeasuringUnit.isMetric(unit)) {
			weightMultiplier = weightMultipliers.first;
			heightMultiplier = heightMultipliers.first;
		} else {
			weightMultiplier = weightMultipliers.second;
			heightMultiplier = heightMultipliers.second;
		}
		double amount = padding + (weightMultiplier * weight) + (heightMultiplier * height) - (ageMultiplier * age);
		return new Calorie(amount);
	}
}
