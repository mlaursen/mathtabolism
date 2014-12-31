/**
 * 
 */
package com.mathtabolism.test.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mathtabolism.test.util.calculation.intakecalculator.IntakeCalculatorUSuite;
import com.mathtabolism.test.util.calculation.mathutils.MathUtilsUSuite;
import com.mathtabolism.test.util.date.dateutils.DateUtilsUSuite;
import com.mathtabolism.test.util.nutrition.NutritionUSuite;
import com.mathtabolism.test.util.string.fractionutils.FractionUtilsUSuite;
import com.mathtabolism.test.util.string.stringutils.StringUtilsUSuite;
import com.mathtabolism.test.util.unit.UnitPackageUSuite;

/**
 * 
 * @author mlaursen
 */
@RunWith(Suite.class)
@SuiteClasses({
    FractionUtilsUSuite.class, StringUtilsUSuite.class, MathUtilsUSuite.class, DateUtilsUSuite.class,
    NutritionUSuite.class, UnitPackageUSuite.class, IntakeCalculatorUSuite.class
})
public class AllTests {
  
}
