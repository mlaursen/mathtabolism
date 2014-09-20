/**
 * 
 */
package com.mathtabolism.util.string;

import java.text.DecimalFormat;

import com.mathtabolism.util.Pair;
import com.mathtabolism.util.calculation.MathUtils;

/**
 * 
 * @author laursenm
 */
public class FractionUtils {
	private FractionUtils() {
	}
	
	public static final int DEFAULT_PRECISION = 2;
	public static final String DIVIDER = "/";
	public static final String MINUS = "-";
	
	/**
	 * Converts a String fraction into a double value. Nulls are allowed. If the given String is null, 0 will be returned.
	 * <p>
	 * An <tt>IllegalArgumentException</tt> will be thrown if the denominator is 0.
	 * <p>
	 * A <tt>NumberFormatException</tt> will be thrown if any part of the String can not be formatted as a number.
	 * 
	 * @param fraction
	 *          the String to convert to a fraction
	 * @return the double value of the fraction, 0 if null, or an exception if not a number
	 */
	public static double fractionToDecimal(String fraction) {
		if(fraction == null || "".equals(fraction)) {
			throw new NumberFormatException("A null or empty String can not be converted to a fraction.");
		}
		
		String[] split = fraction.split("/");
		if(split.length == 1) {
			return Double.valueOf(fraction);
		}
		else if(split.length == 2) {
			String numerator = split[0];
			String denominator = split[1];
			split = numerator.split("\\s");
			double wholeNumber = 0;
			if(split.length == 2) {
				wholeNumber = Double.valueOf(split[0]);
				numerator = split[1];
			}
			double n = Double.valueOf(numerator);
			double d = Double.valueOf(denominator);
			if(d == 0) {
				throw new IllegalArgumentException("Argument 'divisor' is 0");
			}
			double decimal = n / d;
			return wholeNumber + decimal;
		}
		throw new NumberFormatException("The given string can not be converted to a fraction: " + fraction);
	}
	
	/**
	 * Converts a decimal into a fraction String.
	 * 
	 * @param decimal
	 *          a decimal to convert to a fraction String
	 * @return a String Fraction representation of the decimal
	 */
	public static String decimalToFraction(Double decimal) {
		return decimalToFraction(decimal, DEFAULT_PRECISION);
	}
	
	/**
	 * Converts a decimal into a fraction String.
	 * 
	 * @param decimal
	 *          a decimal to convert to a fraction String
	 * @param precision
	 *          the number of decimal places allowed
	 * @return a String Fraction representation of the decimal
	 */
	public static String decimalToFraction(Double decimal, int precision) {
		if(decimal == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(precision);
		df.setMaximumFractionDigits(precision);
		String decimalString = df.format(decimal);
		String[] split = decimalString.split("\\.");
		if(split.length == 2) {
			String sign = split[0].contains(MINUS) ? MINUS : "";
			int wholeNumber = Integer.valueOf(split[0].replace(MINUS, ""));
			String decimalStr = split[1];
			Pair<Integer> fraction = new Pair<>(Integer.valueOf(decimalStr), (int) Math.pow(10, precision));
			Pair<Integer> reduced;
			if(StringUtils.repeatsACharacter(decimalStr)) {
				reduced = MathUtils.approximate(fraction);
			}
			else {
				reduced = MathUtils.reduce(fraction);
			}
			
			return sign + (wholeNumber != 0 ? wholeNumber + " " : "") + reduced.first + DIVIDER + reduced.second;
		}
		return decimalString;
	}
}
