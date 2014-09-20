/**
 * 
 */
package com.github.mlaursen.mathtabolism.nutrition.formula;

import com.github.mlaursen.mathtabolism.constants.Gender;
import com.github.mlaursen.mathtabolism.constants.TDEEFormula;
import com.github.mlaursen.mathtabolism.nutrition.Calorie;
import com.github.mlaursen.mathtabolism.unit.Measurement;
import com.github.mlaursen.mathtabolism.unit.UnitConverter;
import com.github.mlaursen.mathtabolism.unit.UnitMeasurement;
import com.github.mlaursen.mathtabolism.unit.UnitSystem;
import com.github.mlaursen.mathtabolism.util.Pair;
import com.github.mlaursen.mathtabolism.util.number.NumberUtils;

/**
 * 
 * @author laursenm
 */
public class FormulaCalculation {
	private FormulaCalculation() {}
	
	/**
	 * Calculates the BRM
	 * 
	 * @param weight
	 *          the person's weight
	 * @param height
	 *          the person's height
	 * @param age
	 *          the person's age
	 * @param gender
	 *          the person's {@link Gender}
	 * @param unitSystem
	 *          the person's {@link UnitSystem}
	 * @return the number of {@link Calorie} for a person's BMR
	 */
	public static Calorie calculateBMR(double weight, double height, int age, Gender gender, UnitSystem unitSystem) {
		int padding;
		double weightMultiplier, heightMultiplier, ageMultiplier;
		switch(gender) {
			case MALE:
				padding = 66;
				ageMultiplier = 6.8;
				weightMultiplier = unitSystem.isMetric() ? 13.7 : 6.23;
				heightMultiplier = unitSystem.isMetric() ? 5 : 12.7;
				break;
			case FEMALE:
				padding = 655;
				ageMultiplier = 4.7;
				weightMultiplier = unitSystem.isMetric() ? 9.6 : 4.35;
				heightMultiplier = unitSystem.isMetric() ? 1.8 : 4.7;
				break;
			default:
				return new Calorie(0);
		}
		
		double amount = padding + (weightMultiplier * weight) + (heightMultiplier * height) - (ageMultiplier * age);
		return new Calorie(NumberUtils.format(amount));
	}
	
	/**
	 * 
	 * @param weight
	 * @param height
	 * @param age
	 * @param gender
	 * @param unitSystem
	 * @param formula
	 * @return
	 */
	public static Calorie calculateTDEE(double weight, double height, int age, Gender gender, UnitSystem unitSystem,
			TDEEFormula formula) {
		Measurement weightM = new Measurement(unitSystem.isMetric() ? UnitMeasurement.KILOGRAM : UnitMeasurement.POUND, weight);
		Measurement heightM = new Measurement(unitSystem.isMetric() ? UnitMeasurement.CENTIMETER : UnitMeasurement.INCH, height);
		
		double weightInKG = UnitConverter.convert(weightM, UnitMeasurement.KILOGRAM).getValue();
		double heightInCM = UnitConverter.convert(heightM, UnitMeasurement.CENTIMETER).getValue();
		return formula.calculate(weightInKG, heightInCM, age, gender);
	}
	
}
