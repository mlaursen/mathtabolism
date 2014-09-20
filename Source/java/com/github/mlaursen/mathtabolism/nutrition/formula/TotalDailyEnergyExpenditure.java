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

/**
 * 
 * @author laursenm
 */
public class TotalDailyEnergyExpenditure {
	
	private Calorie tdee;
	
	public TotalDailyEnergyExpenditure(double weight, double height, int age, Gender gender, UnitSystem unitSystem,
			TDEEFormula formula) {
		Measurement weightM = new Measurement(unitSystem.isMetric() ? UnitMeasurement.KILOGRAM : UnitMeasurement.POUND, weight);
		Measurement heightM = new Measurement(unitSystem.isMetric() ? UnitMeasurement.CENTIMETER : UnitMeasurement.INCH, height);
		
		UnitConverter.convert(weightM, UnitMeasurement.KILOGRAM);
		UnitConverter.convert(heightM, UnitMeasurement.CENTIMETER);
		double weightInKG = 0; // UnitConverter.convert(weight, MeasuringUnit.METRIC)
		double heightInCM = 0; // UnitConverter.convert(height, MeasuringUnit.METRIC)
		this.tdee = formula.calculate(weightInKG, heightInCM, age, gender);
	}
}
