/**
 * 
 */
package com.mathtabolism.constants;

import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author mlaursen
 */
public enum Weekday {
  DAILY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
  
  public String formatted() {
    return StringUtils.toCamelCase(name(), true);
  }
  
  /**
   * <p>Converts the Weekday into a day of the week number. If the current
   * Weekday is {@link #DAILY} or {@link #SUNDAY}, 7 is returned. Otherwise
   * their ordinal position -1.
   * 
   * <code><pre>
   * MONDAY -> 1
   * TUESDAY -> 2
   * ...
   * SATURDAY -> 6
   * </pre></code>
   * @return a day of week number
   */
  public int toInt() {
    switch(this) {
      case SUNDAY:
      case DAILY:
        return 7;
      default:
        return this.ordinal() - 1;
    }
  }
}
