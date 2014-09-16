/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.date.dateutils;

import static com.github.mlaursen.mathtabolism.util.date.DateUtils.isSameDate;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * @author laursenm
 */
public class IsSameDateUTest {
	
	@Test
	public void testSameDate() {
		assertTrue(isSameDate(new Date(), new Date()));
	}
	
	@Test
	public void testSameDate2() {
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.YEAR, 2000);
		c.set(Calendar.DAY_OF_MONTH, 15);
		
		c2.set(Calendar.MONTH, 1);
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.DAY_OF_MONTH, 15);
		assertTrue(isSameDate(c.getTime(), c2.getTime()));
	}
	
	@Test
	public void testDifferentDates() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.YEAR, 1950);
		assertFalse(isSameDate(new Date(), c.getTime()));
	}
	
}
