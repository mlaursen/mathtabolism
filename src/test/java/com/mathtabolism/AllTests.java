/**
 * 
 */
package com.mathtabolism;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mathtabolism.util.calculation.intakecalculator.IntakeCalculatorUSuite;
import com.mathtabolism.util.calculation.mathutils.MathUtilsUSuite;
import com.mathtabolism.util.date.dateutils.DateUtilsUSuite;
import com.mathtabolism.util.emconverter.EntityModelConverterUSuite;
import com.mathtabolism.util.number.MathtabolismNumberUtilsUSuite;
import com.mathtabolism.util.nutrition.NutritionUSuite;
import com.mathtabolism.util.string.fractionutils.FractionUtilsUSuite;
import com.mathtabolism.util.string.stringutils.StringUtilsUSuite;
import com.mathtabolism.util.unit.UnitPackageUSuite;

/**
 * 
 * @author mlaursen
 */
@RunWith(Suite.class)
@SuiteClasses({
    FractionUtilsUSuite.class, StringUtilsUSuite.class, MathUtilsUSuite.class, DateUtilsUSuite.class,
    NutritionUSuite.class, UnitPackageUSuite.class, IntakeCalculatorUSuite.class, EntityModelConverterUSuite.class,
    MathtabolismNumberUtilsUSuite.class
})
public class AllTests {
  
}
