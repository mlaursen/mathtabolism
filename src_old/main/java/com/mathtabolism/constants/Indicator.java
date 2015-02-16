package com.mathtabolism.constants;

/**
 * An enum used for handing indicators in the database.
 * 
 * @author mlaursen
 */
public enum Indicator {
  TRUE, FALSE;
  
  /**
   * Checks if a given indicator is true. If the given indicator is null,
   * <tt>false</tt> will be returned.
   * @param indicator the indicator to check
   * @return true if the indicator is true
   */
  public static boolean isTrue(Indicator indicator) {
    return TRUE.equals(indicator);
  }
  
  /**
   * Checks if a given indicator is false. If the given indicator is <tt>null</tt>,
   * <tt>true</tt> will be returned. Null is a false-ish value.
   * @param indicator the indicator to check
   * @return true if the indicator false-ish
   */
  public static boolean isFalse(Indicator indicator) {
    return !isTrue(indicator);
  }
  
  /**
   * Converts a boolean to an indicator value
   * @param bool the boolean
   * @return an Indicator
   */
  public static Indicator fromBoolean(boolean bool) {
    return bool ? TRUE : FALSE;
  }
}
