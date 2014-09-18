/**
 * 
 */
package com.github.mlaursen.mathtabolism.util.calculation;

import java.math.BigInteger;

import com.github.mlaursen.mathtabolism.util.Pair;

/**
 * 
 * @author laursenm
 */
public class MathUtils {
	private MathUtils() {}
	
	public static final Pair<Integer> ONE_THIRD = new Pair<Integer>(1, 3);
	public static final Pair<Integer> TWO_THIRDS = new Pair<Integer>(2, 3);
	
	/**
	 * Reduces a numerator and a denominator to their lowest value.
	 * 
	 * @param numerator the numerator to reduce
	 * @param denominator the denominator to reduce
	 * @return a {@link Pair} containing the reduced integers
	 */
	public static Pair<Integer> reduce(Integer numerator, Integer denominator) {
		return reduce(new Pair<Integer>(numerator, denominator));
	}
	
	/**
	 * Reduces a Fraction {@link Pair} with the smalles whole numerator and denominator
	 * @param fraction the Tuple fraction to reduce
	 * @return a {@link Pair} containing the reduced integers
	 */
	public static Pair<Integer> reduce(Pair<Integer> fraction) {
		int gcd = gcd(fraction);
		return new Pair<Integer>(fraction.first / gcd, fraction.second / gcd);
	}
	
	/**
	 * Gets the greatest common denominator for a Tuple of Integers
	 * @param tuple the Tuple to find the greatest common denominator for
	 * @return the greatest common denominator for the two Integers
	 */
	public static int gcd(Pair<Integer> tuple) {
		return gcd(tuple.first, tuple.second);
	}
	
	/**
	 * Gets the greatest common denominator for two Integers
	 * @param first the first Integer
	 * @param second the second Integer
	 * @return the greatest common denominator for the two Integers
	 */
	public static int gcd(int first, int second) {
		return BigInteger.valueOf(first).gcd(BigInteger.valueOf(second)).intValue();
	}
	
	/**
	 * Approximates a fraction Tuple as either {@link #ONE_THIRD}, {@link #TWO_THIRDS}, 
	 * or the original <tt>fraction</tt>
	 * @param fraction a fraction that can possibly be reduced to "1/3" or "2/3".
	 * @return {@link #ONE_THIRD}, {@link #TWO_THIRDS}, or the original <tt>fraction</tt>
	 */
	public static Pair<Integer> approximate(Pair<Integer> fraction) {
		if(fraction.first == 33 && fraction.second == 100) {
			return ONE_THIRD;
		}
		else if(fraction.first == 66 && fraction.second == 100) {
			return TWO_THIRDS;
		}
		else {
			return fraction;
		}
	}
}
