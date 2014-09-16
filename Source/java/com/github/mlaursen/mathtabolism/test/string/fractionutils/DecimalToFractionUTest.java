/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.string.fractionutils;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.github.mlaursen.mathtabolism.util.string.FractionUtils.decimalToFraction;

/**
 * 
 * @author laursenm
 */
public class DecimalToFractionUTest {
	
	@Test
	public void testSimpleDecimal() {
		assertEquals("1/4", decimalToFraction(0.25));
	}
	
	@Test
	public void testSimpleNegativeDecimal() {
		assertEquals("-1/4", decimalToFraction(-0.25));
	}
	
	@Test
	public void testMixedNegativeNumberFraction() {
		assertEquals("-2 1/3", decimalToFraction(-2.33));
	}
	
	@Test
	public void testMixedNumberFraction() {
		assertEquals("2 1/3", decimalToFraction(2.33));
	}
}
