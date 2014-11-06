/**
 * 
 */
package com.mathtabolism.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * 
 * @author mlaursen
 */
public class DateUtils {
  
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
  
  /**
   * Checks if two dates are considered the same. The dates are considered the same if the <tt>month</tt>, the
   * <tt>day</tt>, and the <tt>year</tt> are equal.
   * 
   * @param d1
   *          the first date
   * @param d2
   *          the second date
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
  
  public static String formatDateAsString(Date date) {
    return DATE_FORMAT.format(date);
  }
  
  public static Date formatStringAsDate(String date) {
    try {
      return DATE_FORMAT.parse(date);
    }
    catch (ParseException e) {
      return null;
    }
  }
  
  /**
   * 
   * @param dayOfWeek
   * @param c
   * @return
   */
  public static DateTime findStartDate(int dayOfWeek, DateTime dt) {
    int currentDayOfWeek = dt.getDayOfWeek();
    int offset = 0;
    if(currentDayOfWeek < dayOfWeek) {
      offset = 8 - dayOfWeek;
    }
    else if(currentDayOfWeek > dayOfWeek) {
      offset = currentDayOfWeek - dayOfWeek;
    }
    return dt.minusDays(offset);
  }
  
  /**
   * 
   * @param dayOfWeek
   * @return
   */
  public static DateTime findStartDate(int dayOfWeek) {
    return findStartDate(dayOfWeek, new DateTime());
  }
}
