/**
 * 
 */
package com.mathtabolism.util.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @author mlaursen
 */
public class NumberUtils {
	
	public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.##");
	public static final int DEFAULT_PRECISION = 3;
	static {
		DEFAULT_DECIMAL_FORMAT.setRoundingMode(RoundingMode.FLOOR);
	}
	
	private NumberUtils() {
	}
	
	public static boolean aboutEqual(double d1, double d2) {
		return aboutEqual(d1, d2, DEFAULT_PRECISION);
	}
	
	public static boolean aboutEqual(double d1, double d2, int precision) {
		int scale = (int) Math.pow(10, precision);
		return Math.floor(d1 * scale) == Math.floor(d2 * scale);
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
	
	public static String formatAsString(double decimal) {
		return formatAsString(decimal, DEFAULT_PRECISION);
	}
	
	public static String formatAsString(double decimal, int precision) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(precision);
		df.setMinimumFractionDigits(precision);
		return df.format(decimal);
	}
}
