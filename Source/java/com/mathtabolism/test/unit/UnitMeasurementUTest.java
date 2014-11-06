package com.mathtabolism.test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.util.unit.UnitMeasurement;

import static com.mathtabolism.util.unit.UnitMeasurement.isSameUnitSystem;

/**
 * @author mlaursen
 *
 */
public class UnitMeasurementUTest {
  
  @Test
  public void testSameUnitSystem1Null() {
    assertFalse(isSameUnitSystem(null, UnitMeasurement.CUP));
  }
  
  @Test
  public void testSameUnitSystem2Null() {
    assertFalse(isSameUnitSystem(UnitMeasurement.CUP, null));
  }
  
  @Test
  public void testSameUnitSystemBothNull() {
    assertFalse(isSameUnitSystem(null, null));
  }
  
  @Test
  public void testSameUnitSystemCupGram() {
    assertFalse(isSameUnitSystem(UnitMeasurement.CUP, UnitMeasurement.GRAM));
  }
  
  @Test
  public void testSameUnitSystemCupGallon() {
    assertTrue(isSameUnitSystem(UnitMeasurement.CUP, UnitMeasurement.GALLON));
  }
  
  @Test
  public void testSameUnitSystemGallonCup() {
    assertTrue(isSameUnitSystem(UnitMeasurement.GALLON, UnitMeasurement.CUP));
  }
  
  @Test
  public void testSameUnitSystemGramKilogram() {
    assertTrue(isSameUnitSystem(UnitMeasurement.GRAM, UnitMeasurement.KILOGRAM));
  }
  
  @Test
  public void testSameUnitSystemKilogramGram() {
    assertTrue(isSameUnitSystem(UnitMeasurement.KILOGRAM, UnitMeasurement.GRAM));
  }
  
  @Test
  public void testCanConvertToGramKilogram() {
    assertTrue(UnitMeasurement.GRAM.canConvertTo(UnitMeasurement.KILOGRAM));
  }
  
  @Test
  public void testCanConvertToKilogramGram() {
    assertTrue(UnitMeasurement.KILOGRAM.canConvertTo(UnitMeasurement.GRAM));
  }
  
  @Test
  public void testCanConvertToGramGram() {
    assertTrue(UnitMeasurement.GRAM.canConvertTo(UnitMeasurement.GRAM));
  }
  
  @Test
  public void testCanConvertToOunceGram() {
    assertTrue(UnitMeasurement.OUNCE.canConvertTo(UnitMeasurement.GRAM));
  }
  
  @Test
  public void testCanConvertToGramOunce() {
    assertTrue(UnitMeasurement.GRAM.canConvertTo(UnitMeasurement.OUNCE));
  }
  
  @Test
  public void testCanConvertToOunceGallon() {
    assertFalse(UnitMeasurement.OUNCE.canConvertTo(UnitMeasurement.GALLON));
  }
  
  @Test
  public void testCanConvertToGallonOunce() {
    assertFalse(UnitMeasurement.GALLON.canConvertTo(UnitMeasurement.OUNCE));
  }
  
  @Test
  public void testCanConvertToGallonFluidOunce() {
    assertTrue(UnitMeasurement.GALLON.canConvertTo(UnitMeasurement.FLUID_OUNCE));
  }
  
  @Test
  public void testCanConvertToFluidOunceGallon() {
    assertTrue(UnitMeasurement.FLUID_OUNCE.canConvertTo(UnitMeasurement.GALLON));
  }
  
  @Test
  public void testCanConvertToInchFoot() {
    assertTrue(UnitMeasurement.INCH.canConvertTo(UnitMeasurement.FOOT));
  }
  
  @Test
  public void testCanConvertToInchMeter() {
    assertTrue(UnitMeasurement.INCH.canConvertTo(UnitMeasurement.METER));
  }
  
}
