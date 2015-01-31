/**
 * 
 */
package com.mathtabolism.util.number;


/**
 * 
 * @author mlaursen
 */
public class MathtabolismNumberUtils {
  public static final int DEFAULT_PRECISION = 3;
  
  private MathtabolismNumberUtils() {
  }
  
  /**
   * Checks if two doubles are about equal to the default precision {@value #DEFAULT_PRECISION}.
   * If both doubles are null, they are considered equal as well.
   * 
   * @param d1 the first double, allows null
   * @param d2 the second double, allows null
   * @return true if the doubles are equal to the given decimal place
   */
  public static boolean aboutEqual(Double d1, Double d2) {
    return aboutEqual(d1, d2, DEFAULT_PRECISION);
  }
  
  /**
   * Checks if two doubles are about equal to the given decimal place.
   * If both doubles are null, they are considered equal as well.
   * 
   * <code><pre>
   * aboutEqual(0.98432, 0.9844, 2) -> true
   * aboutEqual(0.98432, 0.9844, 4) -> false
   * aboutEqual(0.94524, 0.94424, 2) -> false
   * </pre></code>
   * 
   * @param d1 the first double, allows null
   * @param d2 the second double, allows null
   * @param toDecimalPlace
   * @return if the doubles are equal to the given decimal place
   * @throws NumberFormatException when the <tt>toDecimalPlace</tt> is less than 0
   */
  public static boolean aboutEqual(Double d1, Double d2, int toDecimalPlace) {
    if(d1 == null && d1 == null) {
      return true;
    } else if(d1 == null || d2 == null) {
      return false;
    }
    
    return format(d1, toDecimalPlace).equals(format(d2, toDecimalPlace));
  }
  
  /**
   * Attempts to format a Double to a String to the {@link #DEFAULT_PRECISION}. If the 
   * <tt>decimal</tt> is null, null is returned.
   * @param decimal the decimal, allows null
   * @return the formatted decimal String or null
   */
  public static String format(Double decimal) {
    return format(decimal, DEFAULT_PRECISION);
  }
  
  /**
   * Attempts to format a Double to a String to <tt>toDecimalPlace</tt> decimal places. If the 
   * <tt>decimal</tt> is null, null is returned.
   * @param decimal the decimal, allows null
   * @param toDecimalPlace the expected amount of decimals, greater than or equal to 0
   * @return the formatted decimal String or null
   * @throws NumberFormatException when the <tt>toDecimalPlace</tt> is less than 0
   */
  public static String format(Double decimal, int toDecimalPlace) {
    return format(decimal, toDecimalPlace, null);
  }
  
  /**
   * Attempts to format a Double to a String to <tt>toDecimalPlace</tt> decimal places. If the 
   * <tt>decimal</tt> is null, <tt>returnOnNull</tt> is returned.
   * @param decimal the decimal, allows null
   * @param toDecimalPlace the expected amount of decimals, greater than or equal to 0
   * @param returnOnNull the String value to retunr if the Decimal is null
   * @return the formatted decimal String or null
   * @throws NumberFormatException when the <tt>toDecimalPlace</tt> is less than 0
   */
  public static String format(Double decimal, int toDecimalPlace, Double returnOnNull) {
    if(toDecimalPlace < 0) {
      throw new NumberFormatException();
    }
    
    if(decimal == null) {
      if(returnOnNull == null) {
        return null;
      }
      decimal = returnOnNull;
    }
    
    return String.format("%1$,." + toDecimalPlace + "f", decimal);
  }
}
