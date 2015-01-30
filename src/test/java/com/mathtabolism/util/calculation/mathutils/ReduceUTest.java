/**
 * 
 */
package com.mathtabolism.util.calculation.mathutils;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.util.container.Pair;

import static com.mathtabolism.util.calculation.MathUtils.reduce;

/**
 * 
 * @author mlaursen
 */
public class ReduceUTest {
  
  @Test
  public void testSimpleInts() {
    assertThat(reduce(1, 3), is(new Pair<Integer>(1, 3)));
  }
  
  @Test
  public void testSimpleTuple() {
    assertThat(reduce(new Pair<Integer>(1, 3)), is(new Pair<Integer>(1, 3)));
  }
  
  @Test
  public void testOneForth() {
    assertThat(reduce(75, 100), is(new Pair<Integer>(3, 4)));
  }
  
  @Test
  public void testBiggerDividend() {
    assertThat(reduce(100, 75), is(new Pair<Integer>(4, 3)));
  }
  
  @Test
  public void testNegativeFirst() {
    assertThat(reduce(-100, 75), is(new Pair<Integer>(-4, 3)));
  }
  
  @Test
  public void testNegativeSecond() {
    assertThat(reduce(100, -75), is(new Pair<Integer>(-4, 3)));
  }
  
  @Test
  public void testNegativeBoth() {
    assertThat(reduce(-100, -75), is(new Pair<Integer>(4, 3)));
  }
  
  @Test
  public void testRepeatingBig() {
    assertThat(reduce(33, 100), is(new Pair<Integer>(33, 100)));
  }
  
  @Test
  public void testRepeatingBigReducable() {
    assertThat(reduce(66, 100), is(new Pair<Integer>(33, 50)));
  }
}
