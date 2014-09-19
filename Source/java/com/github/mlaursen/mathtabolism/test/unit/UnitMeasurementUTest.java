package com.github.mlaursen.mathtabolism.test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.unit.UnitMeasurement;

import static com.github.mlaursen.mathtabolism.unit.UnitMeasurement.isSameUnitSystem;

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
    assertFalse(isSameUnitSystem(UnitMeasurement.CUP, UnitMeasurement.GALLON));
  }
  
  @Test
  public void testSameUnitSystemGallonCup() {
    assertFalse(isSameUnitSystem(UnitMeasurement.GALLON, UnitMeasurement.CUP));
  }

}
