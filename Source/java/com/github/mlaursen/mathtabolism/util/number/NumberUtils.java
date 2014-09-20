/**
 * 
 */
package com.github.mlaursen.mathtabolism.util.number;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @author mlaursen
 */
public class NumberUtils {
	
	public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.##");
	static {
		DEFAULT_DECIMAL_FORMAT.setRoundingMode(RoundingMode.FLOOR);
	}
	
	private NumberUtils() {
	}
	
	public static boolean aboutEqual(double d1, double d2) {
		return DEFAULT_DECIMAL_FORMAT.format(d1).equals(DEFAULT_DECIMAL_FORMAT.format(d2));
	}
	
	public static boolean aboutEqual(double d1, double d2, int precision) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(precision);
		df.setMinimumFractionDigits(precision);
		return df.format(d1).equals(df.format(d2));
	}
	
	
	public static double format(double decimal) {
		return Double.valueOf(DEFAULT_DECIMAL_FORMAT.format(decimal));
	}
	
	public static double format(double decimal, int precision) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(precision);
		df.setMinimumFractionDigits(precision);
		return Double.valueOf(df.format(decimal));
	}
}
