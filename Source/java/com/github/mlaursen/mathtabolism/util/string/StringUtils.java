/**
 * 
 */
package com.github.mlaursen.mathtabolism.util.string;


/**
 * 
 * @author laursenm
 */
public class StringUtils {
	
	/**
	 * Checks if a CharSequence is not empty (""), not null and not whitespace only.
	 * <p>Convenience method for
	 * {@link org.apache.commons.lang3.StringUtils#isNotBlank(CharSequence)}
	 * @param cs the CharSequence to check, may be null
	 * @return true if the CharSequence is not empty (""), not null and not whitespace only.
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
	}
	
	/**
	 * Checks if a CharSequence is not empty ("") and not null.
	 * <p>Convenience method for
	 * {@link org.apache.commons.lang3.StringUtils#isNotEmpty(CharSequence)}
	 * @param cs the CharSequence to check, may be null
	 * @return <tt>true</tt> if the CharSequence is not empty ("") and not null.
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
	}
	
	/**
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * <p>Convenience method for
	 * {@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)}
	 * @param cs the CharSequence to check, may be null
	 * @return <tt>true</tt> if the CharSequence is whitespace, empty ("") or null.
	 */
	public static boolean isBlank(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isBlank(cs);
	}
	
	/**
	 * Checks if a CharSequence is empty ("") or null.
	 * <p>Convenience method for
	 * {@link org.apache.commons.lang3.StringUtils#isEmpty(CharSequence)}
	 * @param cs the CharSequence to check, may be null
	 * @return <tt>true</tt> if the CharSequence is empty or null
	 */
	public static boolean isEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}
	
	/**
	 * Formats a database attribute (table name, column, sequence, etc)
	 * from camel case to all lowercase and underscores
	 * 
	 * @param attribute the attribute to format
	 * @return a String formated as <tt>words_with_more_words</tt>
	 */
	public static String toDatabaseFormat(String attribute) {
		String s = "";
		String[] splitOnUppers = attribute.split("(?=\\p{Upper})");
		boolean first = true;
		for(String split : splitOnUppers) {
			if(!first) {
				s += "_";
			}
			s += split.toLowerCase();
			first = false;
		}
		return s;
	}
}
