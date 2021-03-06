/**
 * 
 */
package com.mathtabolism.util.calculation.mathutils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.util.container.Pair;

import static com.mathtabolism.util.calculation.MathUtils.gcd;

/**
 * 
 * @author mlaursen
 */
public class GreatestCommonDivisorUTest {
  
  @Test
  public void testGCDZero() {
    assertEquals(5, gcd(0, 5));
  }
  
  @Test
  public void testGCDInts() {
    assertEquals(1, gcd(1, 3));
  }
  
  @Test
  public void testGCDTuple() {
    assertEquals(1, gcd(new Pair<Integer>(1, 3)));
  }
  
  @Test
  public void testGCDDivideByZero() {
    assertEquals(3, gcd(3, 0));
  }
  
  @Test
  public void testGCDSame() {
    assertEquals(5, gcd(5, 5));
  }
}
