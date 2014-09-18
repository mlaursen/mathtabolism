/**
 * 
 */
package com.github.mlaursen.mathtabolism.util.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author laursenm
 */
public class DateUtils {
	
	/**
	 * Checks if two dates are considered the same.
	 * The dates are considered the same if the <tt>month</tt>,
	 * the <tt>day</tt>, and the <tt>year</tt> are equal.
	 * @param d1 the first date
	 * @param d2 the second date
	 * @return true if the same date
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
				&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}
	
	public static Date createDate(int month, int day, int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.YEAR, year);
		return c.getTime();
	}
}
