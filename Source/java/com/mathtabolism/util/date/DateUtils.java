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
  public static final String AMERICAN_DATE_FORMAT = "MM/dd/yyyy";
  public static final String EUROPEAN_DATE_FORMAT = "dd/MM/yyyy";
  public static final SimpleDateFormat AMERICAN_DATE_FORMATTER = new SimpleDateFormat(AMERICAN_DATE_FORMAT);
  public static final SimpleDateFormat EUROPEAN_DATE_FORMATTER = new SimpleDateFormat(EUROPEAN_DATE_FORMAT);
  
  /**
   * Checks if two dates are considered the same. The dates are considered the same if the <tt>month</tt>, the
   * <tt>day</tt>, and the <tt>year</tt> are equal.
   * 
   * @param d1 the first date
   * @param d2 the second date
   * @return true if the same date
   */
  public static boolean isSameDate(Date d1, Date d2) {
    if(d1 == null && d2 == null) {
      return true;
    } else if(d1 == null || d2 == null) {
      return false;
    }
    
    Calendar c1 = Calendar.getInstance();
    c1.setTime(d1);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(d2);
    return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
        && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
        && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
  }
  
  /**
   * Creates a new date with the specified month, day, and year
   * 
   * <p>The month starts with <tt>January</tt> being <tt>0</tt>
   * 
   * @param month the month
   * @param day the day of the month
   * @param year the year
   * @return a new date
   */
  public static Date createDate(int month, int day, int year) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, day);
    c.set(Calendar.YEAR, year);
    return c.getTime();
  }
  
  /**
   * Formats a date as an American date
   * @param date the date to format
   * @return a formatted date
   */
  public static String formatDateAsString(Date date) {
    return formatDateAsString(date, false);
  }
  
  /**
   * Formats a String as either a European date using the {@link #EUROPEAN_DATE_FORMATTER} or
   * an American date using the {@link #AMERICAN_DATE_FORMATTER}
   * @param date the date to format
   * @param isEuropean boolean if it is a European date format
   * @return a formatted date
   */
  public static String formatDateAsString(Date date, boolean isEuropean) {
    return (isEuropean ? EUROPEAN_DATE_FORMATTER : AMERICAN_DATE_FORMATTER).format(date);
  }
  
  /**
   * Attempts to format a String as an American date using the {@link #AMERICAN_DATE_FORMATTER}.
   * If the String is null or unable to be parsed as a date, null is returned.
   * @param date possible date
   * @return a date or null
   */
  public static Date formatStringAsDate(String date) {
    return formatStringAsDate(date, false);
  }
  
  /**
   * Attempts to format a String as a date using the {@link #AMERICAN_DATE_FORMATTER} or the
   * {@link #EUROPEAN_DATE_FORMATTER}. If the String is null or unable to be parsed as a date,
   * null is returned.
   * @param date the possible date String
   * @param isEuropean boolean if the date is European formatted
   * @return the date or null
   */
  public static Date formatStringAsDate(String date, boolean isEuropean) {
    try {
      return (isEuropean ? EUROPEAN_DATE_FORMATTER : AMERICAN_DATE_FORMATTER).parse(date);
    } catch(ParseException | NullPointerException e) {
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
  
  public static int calculateAge(Date birthday) {
    Calendar today = Calendar.getInstance();
    Calendar birth = Calendar.getInstance();
    birth.setTime(birthday);
    int todayYear = today.get(Calendar.YEAR);
    int birthYear = birth.get(Calendar.YEAR);
    return todayYear - birthYear;
  }
}
