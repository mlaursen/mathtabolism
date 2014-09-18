/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.calculation.mathutils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.Pair;

import static com.github.mlaursen.mathtabolism.util.calculation.MathUtils.reduce;

/**
 * 
 * @author laursenm
 */
public class ReduceUTest {
	
	@Test
	public void testSimpleInts() {
		assertEquals(new Pair<Integer>(1, 3), reduce(1, 3));
	}
	
	@Test
	public void testSimpleTuple() {
		assertEquals(new Pair<Integer>(1, 3), reduce(new Pair<Integer>(1, 3)));
	}
	
	@Test
	public void testOneForth() {
		assertEquals(new Pair<Integer>(3, 4), reduce(75, 100));
	}
	
	@Test
	public void testBiggerDividend() {
		assertEquals(new Pair<Integer>(4, 3), reduce(100, 75));
	}
	
	@Test
	public void testNegative() {
		assertEquals(new Pair<Integer>(-4, 3), reduce(-100, 75));
	}
	
	@Test
	public void testRepeatingBig() {
		assertEquals(new Pair<Integer>(33, 100), reduce(33, 100));
	}
	
	@Test
	public void testRepeatingBigReducable() {
		assertEquals(new Pair<Integer>(33, 50), reduce(66, 100));
	}
}
