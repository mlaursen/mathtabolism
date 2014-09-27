package com.mathtabolism.test.unit;

import static com.mathtabolism.util.unit.UnitConverter.convert;
import static com.mathtabolism.util.unit.UnitConverter.convertToBase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

import com.mathtabolism.util.number.NumberUtils;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;
import com.mathtabolism.util.unit.UnitMeasurement;

/**
 * @author mlaursen
 *
 */
public class UnitConverterUTest {
	private static final Measurement INCH_12 = new Measurement(UnitMeasurement.INCH, 12);
	private static final Measurement FOOT_1 = new Measurement(UnitMeasurement.FOOT, 1);
	private static final Measurement POUND_1 = new Measurement(UnitMeasurement.POUND, 1);
	private static final Measurement OUNCE_16 = new Measurement(UnitMeasurement.OUNCE, 16);
	private static final Measurement FLUID_OUNCE_8 = new Measurement(UnitMeasurement.FLUID_OUNCE, 8);
	private static final Measurement FLUID_OUNCE_16 = new Measurement(UnitMeasurement.FLUID_OUNCE, 16);
	private static final Measurement FLUID_OUNCE_32 = new Measurement(UnitMeasurement.FLUID_OUNCE, 32);
	private static final Measurement FLUID_OUNCE_128 = new Measurement(UnitMeasurement.FLUID_OUNCE, 128);
	private static final Measurement CUP_1 = new Measurement(UnitMeasurement.CUP, 1);
	private static final Measurement PINT_1 = new Measurement(UnitMeasurement.PINT, 1);
	private static final Measurement QUART_1 = new Measurement(UnitMeasurement.QUART, 1);
	private static final Measurement GALLON_1 = new Measurement(UnitMeasurement.GALLON, 1);
	private static final Measurement TEAPSOON_3 = new Measurement(UnitMeasurement.TEASPOON, 3);
	private static final Measurement TABLESPOON_1 = new Measurement(UnitMeasurement.TABLESPOON, 1);
	
	private static final Measurement MILLIGRAMS_1000 = new Measurement(UnitMeasurement.MILLIGRAM, 1000);
	private static final Measurement GRAMS_1 = new Measurement(UnitMeasurement.GRAM, 1);
	private static final Measurement GRAMS_100 = new Measurement(UnitMeasurement.GRAM, 100);
	private static final Measurement GRAMS_1000 = new Measurement(UnitMeasurement.GRAM, 1000);
	private static final Measurement KILOGRAMS_1 = new Measurement(UnitMeasurement.KILOGRAM, 1);
	private static final Measurement MILLIMETERS_1000 = new Measurement(UnitMeasurement.MILLIMETER, 1000);
	private static final Measurement METERS_1 = new Measurement(UnitMeasurement.METER, 1);
	private static final Measurement METERS_100 = new Measurement(UnitMeasurement.METER, 100);
	private static final Measurement METERS_1000 = new Measurement(UnitMeasurement.METER, 1000);
	private static final Measurement KILOMETERS_1 = new Measurement(UnitMeasurement.KILOMETER, 1);
	
	@Test
	public void testConvertToBaseImperial() {
		assertThat(convertToBase(OUNCE_16), is(POUND_1));
		assertThat(convertToBase(POUND_1), is(POUND_1));
		assertThat(convertToBase(FOOT_1), is(INCH_12));
		assertThat(convertToBase(INCH_12), is(INCH_12));
		assertThat(convertToBase(FLUID_OUNCE_8), is(FLUID_OUNCE_8));
		assertThat(convertToBase(CUP_1), is(FLUID_OUNCE_8));
		assertThat(convertToBase(PINT_1), is(FLUID_OUNCE_16));
		assertThat(convertToBase(QUART_1), is(FLUID_OUNCE_32));
		assertThat(convertToBase(GALLON_1), is(FLUID_OUNCE_128));
		assertThat(convertToBase(TEAPSOON_3), is(TABLESPOON_1));
		assertThat(convertToBase(TABLESPOON_1), is(TABLESPOON_1));
	}
	
	@Test
	public void testConvertToBaseMetric() {
		assertThat(convertToBase(MILLIGRAMS_1000), is(GRAMS_1));
		assertThat(convertToBase(GRAMS_100), is(GRAMS_100));
		assertThat(convertToBase(KILOGRAMS_1), is(GRAMS_1000));
		assertThat(convertToBase(MILLIMETERS_1000), is(METERS_1));
		assertThat(convertToBase(METERS_100), is(METERS_100));
		assertThat(convertToBase(KILOMETERS_1), is(METERS_1000));
	}
	
	@Test
	public void testConvertSame() {
		assertThat(convert(MILLIGRAMS_1000, UnitMeasurement.MILLIGRAM), is(MILLIGRAMS_1000));
		assertThat(convert(GRAMS_1, UnitMeasurement.GRAM), is(GRAMS_1));
		assertThat(convert(KILOGRAMS_1, UnitMeasurement.KILOGRAM), is(KILOGRAMS_1));
	}
	
	/**
	 * Test method for {@link UnitConverter#convert(Measurement, UnitMeasurement)}.
	 */
	@Test
	public void testConvertMetricMassAndWeight() {
		assertThat(convert(GRAMS_100, UnitMeasurement.KILOGRAM), is(new Measurement(UnitMeasurement.KILOGRAM, 0.1)));
		assertThat(convert(KILOGRAMS_1, UnitMeasurement.GRAM), is(GRAMS_1000));
		
		Measurement gram = new Measurement(UnitMeasurement.GRAM, 127);
		Measurement kilogram = new Measurement(UnitMeasurement.KILOGRAM, 127 / 1000.0);
		assertThat(convert(gram, UnitMeasurement.KILOGRAM), is(kilogram));
	}
	
	
	@Test
	public void testConvertImperialDistance() {
		assertThat(convert(FOOT_1, UnitMeasurement.INCH), is(INCH_12));
		assertThat(convert(INCH_12, UnitMeasurement.FOOT), is(FOOT_1));
		
		Measurement inch3Point4 = new Measurement(UnitMeasurement.INCH, 3.4);
		Measurement inch0 = new Measurement(UnitMeasurement.INCH, 0);
		Measurement inchNegative1 = new Measurement(UnitMeasurement.INCH, -1);
		Measurement expectedFoot = new Measurement(UnitMeasurement.FOOT, 3.4 / 12);
		assertThat(convert(inch3Point4, UnitMeasurement.FOOT), is(expectedFoot));
		assertThat(convert(expectedFoot, UnitMeasurement.INCH), is(inch3Point4));
		expectedFoot.setValue(0);
		assertThat(convert(inch0, UnitMeasurement.FOOT), is(expectedFoot));
		assertThat(convert(expectedFoot, UnitMeasurement.INCH), is(inch0));
		expectedFoot.setValue(-1 / 12.0);
		assertThat(convert(inchNegative1, UnitMeasurement.FOOT), is(expectedFoot));
		assertThat(convert(expectedFoot, UnitMeasurement.INCH), is(inchNegative1));
	}
	
	@Test
	public void testConvertImperialVolumes() {
		Measurement flOz4 = new Measurement(UnitMeasurement.FLUID_OUNCE, 4);
		Measurement flOz8Point2 = new Measurement(UnitMeasurement.FLUID_OUNCE, 8.2);
		Measurement cup0Point5 = new Measurement(UnitMeasurement.CUP, 0.5);
		Measurement cup1Point025 = new Measurement(UnitMeasurement.CUP, 1.025);
		assertThat(convert(flOz4, UnitMeasurement.CUP), is(cup0Point5));
		assertThat(convert(cup0Point5, UnitMeasurement.FLUID_OUNCE), is(flOz4));
		
		assertThat(convert(flOz8Point2, UnitMeasurement.CUP), is(cup1Point025));
		assertThat(convert(cup1Point025, UnitMeasurement.FLUID_OUNCE), is(flOz8Point2));
		
		Measurement pint = new Measurement(UnitMeasurement.PINT, 4.0 / 8 / 2);
		assertThat(convert(flOz4, UnitMeasurement.PINT), is(pint));
		assertThat(convert(pint, UnitMeasurement.FLUID_OUNCE), is(flOz4));
		assertThat(convert(pint, UnitMeasurement.CUP), is(cup0Point5));
		
		Measurement quart = new Measurement(UnitMeasurement.QUART, 4.0 / 8 / 4);
		assertThat(convert(flOz4, UnitMeasurement.QUART), is(quart));
		assertThat(convert(quart, UnitMeasurement.FLUID_OUNCE), is(flOz4));
		assertThat(convert(quart, UnitMeasurement.PINT), is(pint));
		
		Measurement gallon = new Measurement(UnitMeasurement.GALLON, 4.0 / 8 / 4 / 4);
		assertThat(convert(flOz4, UnitMeasurement.GALLON), is(gallon));
		assertThat(convert(gallon, UnitMeasurement.FLUID_OUNCE), is(flOz4));
		assertThat(convert(gallon, UnitMeasurement.QUART), is(quart));
	}
	
	@Test
	public void testConvertImperialMassAndWeight() {
		assertThat(convert(POUND_1, UnitMeasurement.OUNCE), is(OUNCE_16));
		assertThat(convert(OUNCE_16, UnitMeasurement.POUND), is(POUND_1));
		
		Measurement pound = new Measurement(UnitMeasurement.POUND, 178.37);
		Measurement ounce = new Measurement(UnitMeasurement.OUNCE, 178.37 * 16);
		assertThat(convert(pound, UnitMeasurement.OUNCE), is(ounce));
		assertThat(convert(ounce, UnitMeasurement.POUND), is(pound));
	}
	
	@Test
	public void testConvertMassAndWeightSwitchingUnit() {
		DecimalFormat df = new DecimalFormat();
		df.setRoundingMode(RoundingMode.CEILING);
		df.setMaximumFractionDigits(7);
		Measurement gram = new Measurement(UnitMeasurement.GRAM, 127);
		Measurement lbs  = new Measurement(UnitMeasurement.POUND, NumberUtils.format(127 * 2.2046 / 1000, 7));
		assertThat(convert(gram, UnitMeasurement.POUND), is(lbs));
		assertEquals(convert(lbs, UnitMeasurement.GRAM).getValue(), gram.getValue(), 1);
		
		Measurement lbs2 = new Measurement(UnitMeasurement.POUND, 178.35);
		Measurement kg = new Measurement(UnitMeasurement.KILOGRAM, 178.35 / 2.2046);
		assertThat(convert(kg, UnitMeasurement.POUND), is(lbs2));
		assertEquals(convert(lbs2, UnitMeasurement.KILOGRAM).getValue(), kg.getValue(), 2);
	}
	
	@Test
	public void testConvertDistanceSwitchingUnit() {
		Measurement inch72 = new Measurement(UnitMeasurement.INCH, 72);
		Measurement cm = new Measurement(UnitMeasurement.CENTIMETER, 182.88);
		assertEquals(cm.getValue(), convert(inch72, UnitMeasurement.CENTIMETER).getValue(), 2);
		assertEquals(inch72.getValue(), convert(cm, UnitMeasurement.INCH).getValue(), 2);
	}
}
