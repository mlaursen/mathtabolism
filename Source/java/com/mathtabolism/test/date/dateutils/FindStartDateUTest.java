/**
 * 
 */
package com.mathtabolism.test.date.dateutils;

import static com.mathtabolism.util.date.DateUtils.findStartDate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.mathtabolism.constants.Weekday;

/**
 * 
 * @author mlaursen
 */
public class FindStartDateUTest {
	
	@Test
	public void testSameDayOfWeek() {
		DateTime startDate = new DateTime(2014, 11, 2, 3, 0);
		DateTime expected = new DateTime(2014, 11, 2, 3, 0);
		assertThat(findStartDate(Weekday.SUNDAY.toInt(), startDate), is(expected));
	}
	
	@Test
	public void testOnMondayWithCurrentSunday() {
		DateTime startDate = new DateTime(2014, 11, 2, 3, 0);
		DateTime expected = new DateTime(2014, 10, 27, 3, 0);
		assertThat(findStartDate(Weekday.MONDAY.toInt(), startDate), is(expected));
	}
	
	@Test
	public void testOnWednesdayWithCurrentMonday() {
		DateTime startDate = new DateTime(2014, 11, 3, 3, 0);
		DateTime expected = new DateTime(2014, 10, 29, 3, 0);
		assertThat(findStartDate(Weekday.WEDNESDAY.toInt(), startDate), is(expected));
	}
	
	@Test
	public void testOnWednesdayWithCurrentFriday() {
		DateTime startDate = new DateTime(2014, 10, 31, 3, 0);
		DateTime expected = new DateTime(2014, 10, 29, 3, 0);
		assertThat(findStartDate(Weekday.WEDNESDAY.toInt(), startDate), is(expected));
	}
	
	@Test
	public void testOnFridayWithCurrentSunday() {
		DateTime startDate = new DateTime(2014, 11, 2, 3, 0);
		DateTime expected = new DateTime(2014, 10, 31, 3, 0);
		assertThat(findStartDate(Weekday.FRIDAY.toInt(), startDate), is(expected));
	}
}
