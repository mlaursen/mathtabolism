/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.string.fractionutils;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.string.FractionUtils;

/**
 * 
 * @author laursenm
 */
public class FractionToDecimalUTest {
	
	@Test
	public void testSimpleFraction() {
	  assertThat(FractionUtils.fractionToDecimal("1/4"), is(0.25));
	}
	
	@Test
	public void testWholeNumber() {
	  assertThat(FractionUtils.fractionToDecimal("1"), is(1.0));
	}
	
	@Test
	public void testWholeNumberWithFraction() {
	  assertThat(FractionUtils.fractionToDecimal("3 3/4"), is(3.75));
	}
	
	@Test
	public void testRepeating() {
		assertEquals(0.33, FractionUtils.fractionToDecimal("1/3"), 2);
	}
	
	@Test
	public void testBiggerRepeating() {
		assertEquals(1.66, FractionUtils.fractionToDecimal("5/3"), 3);
	}
	
	@Test
	public void testBiggerNumeratorWithWhole() {
	  assertThat(FractionUtils.fractionToDecimal("10 20/16"), is(11.25));
	}
	
	@Test(expected=NumberFormatException.class)
	public void testInvalid() {
		FractionUtils.fractionToDecimal("bob");
	}
	
	@Test(expected=NumberFormatException.class)
	public void testNull() {
		FractionUtils.fractionToDecimal(null);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testEmpty() {
		FractionUtils.fractionToDecimal("");;
	}
	
	@Test
	public void testZero() {
	  assertThat(FractionUtils.fractionToDecimal("0"), is(0.0));
	}
	
	@Test(expected=NumberFormatException.class)
	public void testWordDivied() {
		FractionUtils.fractionToDecimal("bob/bobb");
	}
	
	@Test
	public void testDoubleOverDouble() {
	  assertThat(FractionUtils.fractionToDecimal("3.33/15"), is(0.222));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDivideByZero() {
		FractionUtils.fractionToDecimal("3/0");
	}
	
	@Test
	public void testZeroDivied() {
	  assertThat(FractionUtils.fractionToDecimal("0/3"), is(0.0));
	}
}
