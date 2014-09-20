/**
 * 
 */
package com.github.mlaursen.mathtabolism.util.number;

import java.text.DecimalFormat;

/**
 * 
 * @author mlaursen
 */
public class NumberUtils {
	
	public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.#######");
	
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
	
	
	public static double formatDecimal(double decimal) {
		return Double.valueOf(DEFAULT_DECIMAL_FORMAT.format(decimal));
	}
}
