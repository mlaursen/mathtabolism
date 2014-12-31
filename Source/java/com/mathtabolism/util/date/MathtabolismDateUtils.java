/**
 * 
 */
package com.mathtabolism.util.date;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * 
 * @author mlaursen
 */
public class MathtabolismDateUtils {
  
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
