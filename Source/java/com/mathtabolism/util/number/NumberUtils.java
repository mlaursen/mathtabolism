/**
 * 
 */
package com.mathtabolism.util.number;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @author mlaursen
 */
public class NumberUtils {
  public static final int DEFAULT_PRECISION = 3;
  
  private NumberUtils() {
  }
  
  public static boolean aboutEqual(double d1, double d2) {
    return aboutEqual(d1, d2, DEFAULT_PRECISION);
  }
  
  public static boolean aboutEqual(double d1, double d2, int precision) {
    int scale = (int) Math.pow(10, precision);
    return Math.floor(d1 * scale) == Math.floor(d2 * scale);
  }
  
  public static double format(Double decimal) {
    return format(decimal, DEFAULT_PRECISION);
  }
  
  public static double format(Double decimal, int precision) {
    if(decimal == null) {
      decimal = 0.0;
    }
    
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(precision);
    df.setMinimumFractionDigits(precision);
    return Double.valueOf(df.format(decimal));
  }
  
  public static String formatAsString(double decimal) {
    return formatAsString(decimal, DEFAULT_PRECISION);
  }
  
  public static String formatAsString(double decimal, int precision) {
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(precision);
    df.setMinimumFractionDigits(precision);
    return df.format(decimal);
  }
  
  public static Double stringToDouble(String possibleDouble) {
    return org.apache.commons.lang3.math.NumberUtils.toDouble(possibleDouble);
  }
  
  public static Integer stringToInteger(String possibleInteger) {
    return org.apache.commons.lang3.math.NumberUtils.toInt(possibleInteger);
  }
}
