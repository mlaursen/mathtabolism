package com.github.mlaursen.mathtabolism.test.unit;

import static com.github.mlaursen.mathtabolism.unit.UnitConverter.convert;
import static com.github.mlaursen.mathtabolism.unit.UnitConverter.convertToBase;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.unit.Measurement;
import com.github.mlaursen.mathtabolism.unit.UnitConverter;
import com.github.mlaursen.mathtabolism.unit.UnitMeasurement;

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
    assertThat(convertToBase(FOOT_1), is(FOOT_1));
    assertThat(convertToBase(INCH_12), is(FOOT_1));
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
  

  /**
   * Test method for {@link UnitConverter#convert(Measurement, UnitMeasurement)}.
   */
  @Test
  public void testConvertSame() {
    assertThat(convert(GRAMS_100, UnitMeasurement.GRAM), is(GRAMS_100));
    assertThat(convert(KILOGRAMS_1, UnitMeasurement.KILOGRAM), is(KILOGRAMS_1));
  }
  
  @Test
  public void testConvertGramKilogram() {
    assertThat(convert(GRAMS_100, UnitMeasurement.KILOGRAM), is(new Measurement(UnitMeasurement.KILOGRAM, 0.1)));
  }
  
  @Test
  public void testConvertKilogramGram() {
    assertThat(convert(KILOGRAMS_1, UnitMeasurement.GRAM), is(GRAMS_1000));
  }
  
  @Test
  public void testConvertInch() {
    //assertThat(convert(FOOT_1, UnitMeasurement.INCH), is(INCH_12));
    //assertThat(convert(INCH_12, UnitMeasurement.FOOT), is(FOOT_1));
  }
}
