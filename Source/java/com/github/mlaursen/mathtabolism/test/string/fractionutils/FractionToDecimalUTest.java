/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.string.fractionutils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.string.FractionUtils;

/**
 * 
 * @author laursenm
 */
public class FractionToDecimalUTest {
	
	@Test
	public void testSimpleFraction() {
		assertEquals(0.25, FractionUtils.fractionToDecimal("1/4"), 2);
	}
	
	@Test
	public void testWholeNumber() {
		assertEquals(1.0, FractionUtils.fractionToDecimal("1"), 1);
	}
	
	@Test
	public void testWholeNumberWithFraction() {
		assertEquals(3.75, FractionUtils.fractionToDecimal("3 3/4"), 2);
	}
	
	@Test
	public void testRepeating() {
		assertEquals(0.33, FractionUtils.fractionToDecimal("1/3"), 2);
	}
	
	@Test
	public void testBiggerNumerator() {
		assertEquals(1.66, FractionUtils.fractionToDecimal("5/3"), 3);
	}
	
	@Test
	public void testBiggerNumeratorWithWhole() {
		assertEquals(10.125, FractionUtils.fractionToDecimal("10 20/16"), 3);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testInvalid() {
		FractionUtils.fractionToDecimal("bob");
	}
	
	@Test
	public void testNull() {
		assertEquals(0, FractionUtils.fractionToDecimal(null), 0);
	}
	
	@Test
	public void testEmpty() {
		assertEquals(0, FractionUtils.fractionToDecimal(""), 0);
	}
	
	@Test
	public void testZero() {
		assertEquals(0, FractionUtils.fractionToDecimal("0"), 0);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testWordDivied() {
		FractionUtils.fractionToDecimal("bob/bobb");
	}
	
	@Test
	public void testDoubleOverDouble() {
		assertEquals(0.222, FractionUtils.fractionToDecimal("3.33/15"), 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDivideByZero() {
		FractionUtils.fractionToDecimal("3/0");
	}
	
	@Test
	public void testZeroDivied() {
		assertEquals(0, FractionUtils.fractionToDecimal("0/3"), 0);
	}
}
