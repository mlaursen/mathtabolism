/**
 * 
 */
package com.mathtabolism.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mathtabolism.test.calculation.mathutils.MathUtilsUSuite;
import com.mathtabolism.test.date.dateutils.DateUtilsUSuite;
import com.mathtabolism.test.nutrition.NutritionUSuite;
import com.mathtabolism.test.string.fractionutils.FractionUtilsUSuite;
import com.mathtabolism.test.string.stringutils.StringUtilsUSuite;
import com.mathtabolism.test.unit.UnitPackageUSuite;

/**
 * 
 * @author mlaursen
 */
@RunWith(Suite.class)
@SuiteClasses({ FractionUtilsUSuite.class, StringUtilsUSuite.class, MathUtilsUSuite.class, DateUtilsUSuite.class,
		NutritionUSuite.class, UnitPackageUSuite.class })
public class AllTests {
	
}
