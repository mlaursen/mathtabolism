/**
 * 
 */
package com.github.mlaursen.mathtabolism.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.mlaursen.mathtabolism.test.calculation.mathutils.MathUtilsUSuite;
import com.github.mlaursen.mathtabolism.test.date.dateutils.DateUtilsUSuite;
import com.github.mlaursen.mathtabolism.test.nutrition.NutritionUSuite;
import com.github.mlaursen.mathtabolism.test.string.fractionutils.FractionUtilsUSuite;
import com.github.mlaursen.mathtabolism.test.string.stringutils.StringUtilsUSuite;

/**
 * 
 * @author laursenm
 */
@RunWith(Suite.class)
@SuiteClasses({
	FractionUtilsUSuite.class,
	StringUtilsUSuite.class,
	MathUtilsUSuite.class,
	DateUtilsUSuite.class,
	NutritionUSuite.class
	})
public class AllTests {
	
}
