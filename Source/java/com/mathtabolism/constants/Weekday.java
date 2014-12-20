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
  
  public int toInt() {
    return this.equals(SUNDAY) ? 7 : this.ordinal() - 1;
  }
}
