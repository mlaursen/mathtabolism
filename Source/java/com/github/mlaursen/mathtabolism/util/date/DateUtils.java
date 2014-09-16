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
	 * Checks if two dates are approximately the same
	 * @param d1 the first date
	 * @param d2 the second date
	 * @return true if the same date
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		return d1.compareTo(d2) == 0;
	}
	
	public static Date createDate(int month, int day, int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.YEAR, year);
		return c.getTime();
	}
}
