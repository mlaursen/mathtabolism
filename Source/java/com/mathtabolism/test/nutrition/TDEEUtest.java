/**
 * 
 */
package com.mathtabolism.test.nutrition;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.nutrition.Calorie;
import com.mathtabolism.nutrition.formula.FormulaCalculation;
import com.mathtabolism.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
public class TDEEUtest {
	
	@Test
	public void testCalculateTDEE() {
		Calorie tdee = FormulaCalculation.calculateTDEE(183, 72, 23, Gender.MALE, UnitSystem.IMPERIAL, TDEEFormula.HARRIS_BENEDICT);
		assertThat(tdee, is(new Calorie(1966.97)));
		tdee = FormulaCalculation.calculateTDEE(83.0074, 182.88, 23, Gender.MALE, UnitSystem.METRIC, TDEEFormula.HARRIS_BENEDICT);
		assertThat(tdee, is(new Calorie(1966.97)));
		
		tdee = FormulaCalculation.calculateTDEE(183, 72, 23, Gender.MALE, UnitSystem.IMPERIAL, TDEEFormula.MIFFLIN_ST_JOER);
		assertThat(tdee, is(new Calorie(1863.07)));
		tdee = FormulaCalculation.calculateTDEE(83.0074, 182.88, 23, Gender.MALE, UnitSystem.METRIC, TDEEFormula.MIFFLIN_ST_JOER);
		assertThat(tdee, is(new Calorie(1863.07)));
	}
	
	@Test
	public void testCalculateBMR() {
		Calorie bmr = FormulaCalculation.calculateBMR(183, 72, 23, Gender.MALE, UnitSystem.IMPERIAL);
		assertThat(bmr, is(new Calorie(1964.09)));
		bmr = FormulaCalculation.calculateBMR(83, 182, 23, Gender.MALE, UnitSystem.METRIC);
		assertThat(bmr, is(new Calorie(1956.69)));
		
		bmr = FormulaCalculation.calculateBMR(120, 62, 23, Gender.FEMALE, UnitSystem.IMPERIAL);
		assertThat(bmr, is(new Calorie(1360.30)));
		bmr = FormulaCalculation.calculateBMR(66.5, 157, 23, Gender.FEMALE, UnitSystem.METRIC);
		assertThat(bmr, is(new Calorie(1467.9)));
	}
}
