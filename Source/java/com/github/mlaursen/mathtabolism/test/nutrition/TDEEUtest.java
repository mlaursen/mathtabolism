/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.nutrition;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.constants.Gender;
import com.github.mlaursen.mathtabolism.constants.TDEEFormula;
import com.github.mlaursen.mathtabolism.nutrition.Calorie;
import com.github.mlaursen.mathtabolism.nutrition.formula.TotalDailyEnergyExpenditure;
import com.github.mlaursen.mathtabolism.unit.UnitSystem;

/**
 * 
 * @author laursenm
 */
public class TDEEUtest {
	
	@Test
	public void testNewTDEE() {
		TotalDailyEnergyExpenditure tdee = new TotalDailyEnergyExpenditure(183, 72, 23, Gender.MALE, UnitSystem.IMPERIAL, TDEEFormula.HARRIS_BENEDICT);
		assertThat(tdee.getTdee(), is(new Calorie(1966.97)));
		tdee = new TotalDailyEnergyExpenditure(83.0074, 182.88, 23, Gender.MALE, UnitSystem.METRIC, TDEEFormula.HARRIS_BENEDICT);
		assertThat(tdee.getTdee(), is(new Calorie(1966.97)));
		
		tdee = new TotalDailyEnergyExpenditure(183, 72, 23, Gender.MALE, UnitSystem.IMPERIAL, TDEEFormula.MIFFLIN_ST_JOER);
		assertThat(tdee.getTdee(), is(new Calorie(1863.07)));
		tdee = new TotalDailyEnergyExpenditure(83.0074, 182.88, 23, Gender.MALE, UnitSystem.METRIC, TDEEFormula.MIFFLIN_ST_JOER);
		assertThat(tdee.getTdee(), is(new Calorie(1863.07)));
	}
	
}
