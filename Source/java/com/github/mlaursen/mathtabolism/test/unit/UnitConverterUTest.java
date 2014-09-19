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
  
  private static final Measurement MILLIGRAMS_1000 = new Measurement(UnitMeasurement.MILLIGRAM, 1000);
  private static final Measurement GRAMS_1 = new Measurement(UnitMeasurement.GRAM, 1);
  private static final Measurement GRAMS_100 = new Measurement(UnitMeasurement.GRAM, 100);
  private static final Measurement GRAMS_1000 = new Measurement(UnitMeasurement.GRAM, 1000);
  private static final Measurement KILOGRAMS_1 = new Measurement(UnitMeasurement.KILOGRAM, 1);
  private static final Measurement INCH_12 = new Measurement(UnitMeasurement.INCH, 12);
  private static final Measurement FOOT_1 = new Measurement(UnitMeasurement.FOOT, 1);
  

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
  public void testConvertInchFoot() {
    assertThat(convert(INCH_12, UnitMeasurement.FOOT), is(FOOT_1));
  }
  
  @Test
  public void testConvertFootInch() {
    assertThat(convert(FOOT_1, UnitMeasurement.INCH), is(INCH_12));
  }
  
  @Test
  public void testConvertToBaseFoot() {
    assertThat(convertToBase(FOOT_1), is(FOOT_1));
  }
  
  @Test
  public void testConvertToBaseKilogram() {
    assertThat(convertToBase(KILOGRAMS_1), is(GRAMS_1000));
  }
  
  @Test
  public void testConvertToBaseMilligram() {
    assertThat(convertToBase(MILLIGRAMS_1000), is(GRAMS_1));
  }
  
  @Test
  public void testConvertToBaseGram() {
    assertThat(convertToBase(GRAMS_100), is(GRAMS_100));
  }
}
