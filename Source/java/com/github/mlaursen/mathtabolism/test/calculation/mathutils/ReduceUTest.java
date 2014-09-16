/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.calculation.mathutils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.Tuple;

import static com.github.mlaursen.mathtabolism.util.calculation.MathUtils.reduce;

/**
 * 
 * @author laursenm
 */
public class ReduceUTest {
	
	@Test
	public void testSimpleInts() {
		assertEquals(new Tuple<Integer>(1, 3), reduce(1, 3));
	}
	
	@Test
	public void testSimpleTuple() {
		assertEquals(new Tuple<Integer>(1, 3), reduce(new Tuple<Integer>(1, 3)));
	}
	
	@Test
	public void testOneForth() {
		assertEquals(new Tuple<Integer>(3, 4), reduce(75, 100));
	}
	
	@Test
	public void testBiggerDividend() {
		assertEquals(new Tuple<Integer>(4, 3), reduce(100, 75));
	}
	
	@Test
	public void testNegative() {
		assertEquals(new Tuple<Integer>(-4, 3), reduce(-100, 75));
	}
	
	@Test
	public void testRepeatingBig() {
		assertEquals(new Tuple<Integer>(33, 100), reduce(33, 100));
	}
	
	@Test
	public void testRepeatingBigReducable() {
		assertEquals(new Tuple<Integer>(33, 50), reduce(66, 100));
	}
}
