/**
 * 
 */
package com.github.mlaursen.mathtabolism.nutrition.formula;

import com.github.mlaursen.mathtabolism.constants.Gender;
import com.github.mlaursen.mathtabolism.constants.MeasuringUnit;
import com.github.mlaursen.mathtabolism.constants.TDEEFormula;
import com.github.mlaursen.mathtabolism.nutrition.Calorie;

/**
 * 
 * @author laursenm
 */
public class TotalDailyEnergyExpenditure {
	
	private Calorie tdee;
	public TotalDailyEnergyExpenditure(double weight, double height, int age, Gender gender, MeasuringUnit unit, TDEEFormula formula) {
		double weightInKG = 0; // UnitConverter.convert(weight, MeasuringUnit.METRIC)
		double heightInCM = 0; // UnitConverter.convert(height, MeasuringUnit.METRIC)
		this.tdee = formula.calculate(weightInKG, heightInCM, age, gender);
	}
}
