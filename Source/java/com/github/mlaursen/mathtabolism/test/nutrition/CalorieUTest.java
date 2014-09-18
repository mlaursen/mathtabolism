/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.nutrition;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.nutrition.Calorie;
import com.github.mlaursen.mathtabolism.nutrition.Fat;

/**
 * 
 * @author laursenm
 */
public class CalorieUTest {
	
	@Test
	public void testAddOneToZero() {
		Calorie zeroCalories = new Calorie(0);
		Calorie oneCalorie   = new Calorie(1);
		zeroCalories.add(oneCalorie);
		assertEquals(oneCalorie, zeroCalories);
	}
	
	@Test
	public void testAddZeroToZero() {
		Calorie zeroCalories = new Calorie(0);
		zeroCalories.add(new Calorie(0));
		assertEquals(new Calorie(0), zeroCalories);
	}
	
	@Test
	public void testAddFatToCalorie() {
		Calorie c = new Calorie(20);
		Fat f = new Fat(20);
		c.add(f);
		assertNotEquals(new Calorie(40), c);
		assertEquals(new Calorie(20), c);
	}
	
	@Test
	public void testAddCalories() {
		Calorie twoHundred = new Calorie(200);
		Calorie oneThousand = new Calorie(1000);
		twoHundred.add(oneThousand);
		assertEquals(new Calorie(1200), twoHundred);
	}
	
	@Test
	public void testSubtractOneAndZero() {
		Calorie zero = new Calorie(0);
		Calorie one  = new Calorie(1);
		one.subtract(zero);
		assertEquals(new Calorie(1), one);
	}
	
	@Test
	public void testSubtractZero() {
		Calorie zero1 = new Calorie(0);
		Calorie zero2 = new Calorie(0);
		zero1.subtract(zero2);
		assertEquals(new Calorie(0), zero1);
	}
	
	@Test
	public void testSubtractFat() {
		Calorie c = new Calorie(150);
		Fat f = new Fat(20);
		c.subtract(f);
		assertEquals(new Calorie(150), c);
	}
	
}
